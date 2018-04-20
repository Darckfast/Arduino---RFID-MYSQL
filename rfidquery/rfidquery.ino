/*
  MySQL Connector/Arduino Example : query results

  This example demonstrates how to issue a SELECT query and how to read columns
  and rows from the result set. Study this example until you are familiar with how to
  do this before writing your own sketch to read and consume query results.

  NOTICE: You must download and install the World sample database to run
          this sketch unaltered. See http://dev.mysql.com/doc/index-other.html.

  INSTRUCTIONS FOR USE

  1) Change the address of the server to the IP address of the MySQL server
  2) Change the user and password to a valid MySQL user and password
  3) Connect a USB cable to your Arduino
  4) Select the correct board and port
  5) Compile and upload the sketch to your Arduino
  6) Once uploaded, open Serial Monitor (use 115200 speed) and observe

  Note: The MAC address can be anything so long as it is unique on your network.

  Created by: Dr. Charles A. Bell
*/

/*
  Esquema de cores do LED
  BRANCO -> Iniciando o arduino
  AZUL -> Conexão feita com sucesso
  AMARELO -> Conexão falhou
  VERDE -> Acesso garantido
  VERMELHO -> Acesso negado
*/

#include <Ethernet.h>

#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>

#include <SPI.h>
#include <MFRC522.h>

#define COMMON_ANODE
#define redPin 5
#define greenPin 6
#define bluePin 3
#define relayPin 4

constexpr uint8_t RST_PIN = 8;          // Configurable, see typical pin layout above
constexpr uint8_t SS_PIN = 9;         // Configurable, see typical pin layout above
byte readCard[4];

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

byte mac_addr[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

IPAddress server_addr(192, 168, 1, 45); // IP of the MySQL *server* here
//char user[] = "admin";              // MySQL user login username
//char password[] = "1234";        // MySQL user login password

// query para selecionar o banco
//char querySelectDB[] = "USE arduino";

EthernetClient client;
MySQL_Connection conn((Client *)&client);

void setup() {

  pinMode(relayPin, OUTPUT);

  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(bluePin, OUTPUT);

  Serial.begin(115200);
  while (!Serial); // wait for serial port to connect

  SPI.begin();      // Init SPI bus
  mfrc522.PCD_Init();   // Init MFRC522
  mfrc522.PCD_DumpVersionToSerial();  // Show details of PCD - MFRC522 Card Reader details
  Serial.println(F("Scan PICC to see UID, SAK, type, and data blocks..."));

  //byte mac_addr[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

  Ethernet.begin(mac_addr);
  Serial.println(F("Connecting..."));

  //Chama a função que vai conectar com o database MySQL
  conn_led();

}
void loop() {
  led_off();
  bool acess = false;

  //Query que vai buscar o cartão no banco de dados
  String q = "SELECT * FROM ID WHERE id = '?'";
  q.reserve(40);
  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }

  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }

  //Substitui '?' pela ID do cartão
  q.replace("?", getID());

  //Serial.println(q);
  Serial.println(F("\nRunning SELECT and printing results\n"));

  // Initiate the query class instance
  MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);

  const char* querySelectDB = "USE arduino";

  cur_mem->execute(querySelectDB);

  //Converte string para char*
  const char *query = q.c_str();
  Serial.println(query);

  //Executa a query
  cur_mem->execute(query);

  //Pega as colunas
  column_names *cols = cur_mem->get_columns();
  for (int f = 0; f < cols->num_fields; f++) {
    Serial.print(cols->fields[f]->name);
    if (f < cols->num_fields - 1) {
      Serial.print(F(", "));
    }
  }
  Serial.println();
  // Read the rows and print them
  row_values *row = NULL;
  int count = 0;
  do {
    row = cur_mem->get_next_row();
    //Verifica se teve algum valor retornado da query
    if (row != NULL) {
      for (int f = 0; f < cols->num_fields; f++) {
        Serial.print(row->values[f]);

        if (f < cols->num_fields - 1) {
          Serial.print(F(", "));
        }
        if (f != 0) {
          int ac = atoi(row->values[f]);
          if (ac != 2) {
            Serial.println(F("\nAcesso INSUFICIENTE"));
          } else {
            acess = true;
          }
        }
      }
      Serial.println();
    } else if (count == 0) {
      red();  // ON red
      Serial.println(F("Identificação não encontrada"));
      delay(2000);
      led_off();  // OFF red

    } else {
      //setColor(0, 255, 0);  // ON green
      Serial.println(F("Identificação encontrada"));
      /*if(acess){
        green();
        digitalWrite(relayPin,HIGH);
        delay(2000);
        led_off();  // OFF green
        digitalWrite(relayPin,LOW);
        }else{
        red();  // ON red
        Serial.println("Identificação sem acesso");
        delay(2000);
        led_off();  // OFF red
        }*/
      switch (acess) {
        case true:
          green();
          //COM-NC
          digitalWrite(relayPin, HIGH);
          delay(2000);
          led_off();  // OFF green
          digitalWrite(relayPin, LOW);
        default:
          red();  // ON red
          Serial.println(F("Identificação sem acesso"));
          delay(2000);
          led_off();  // OFF red
      }
    }
    count++;
  } while (row != NULL);

  // Deleting the cursor also frees up memory used
  delete cur_mem;
}
String getID() {

  String tagID = "";

  for (uint8_t i = 0; i < 4; i++) {
    readCard[i] = mfrc522.uid.uidByte[i];
    tagID.concat(String(mfrc522.uid.uidByte[i], HEX));
  }

  tagID.toUpperCase();
  mfrc522.PICC_HaltA();

  return tagID;
}
void setColor(int red, int green, int blue)
{
#ifdef COMMON_ANODE
  red = 255 - red;
  green = 255 - green;
  blue = 255 - blue;
#endif
  analogWrite(redPin, red);
  analogWrite(greenPin, green);
  analogWrite(bluePin, blue);
}
//Função tenta conectar e pisca o led conforme o resultado da tentativa de conexão
void conn_led() {
  char user[] = "admin";              // MySQL user login username
  char password[] = "1234";        // MySQL user login password

  while (!(conn.connect(server_addr, 3306, user, password))) {

    Serial.println(F("Connection failed."));

    //Pisca Amarelo -> A conexão falhou
    yellow();
    delay(1000);
    led_off();
  }
  //Pisca azul -> Connectou ao servidor MySQL
  blue();
  delay(1000);
  led_off();
}
void red () {
  setColor(255, 0, 0);
}
void green() {
  setColor(0, 255, 0);
}
void blue() {
  setColor(0, 0, 255);
}
void yellow() {
  setColor(255, 255, 0);
}
void white() {
  setColor(255, 255, 255);
}
void led_off() {
  setColor(0, 0, 0);
}
