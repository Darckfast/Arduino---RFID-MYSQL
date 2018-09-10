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

#include <SpritzCipher.h>

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

IPAddress server_addr(192, 168, 1, 44); // IP of the MySQL *server* here

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


  Ethernet.begin(mac_addr);
  Serial.println(F("Connecting..."));

  conn_led();
  Serial.println(F("Aguardando Leitura"));
}
void loop() {
  led_off();
  bool acess = false;
 
  char select[75];
  char insert[100];

  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    Serial.print(F("."));
    return;
  }
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    Serial.print(F("."));
    return;
  }

  String hashCARD = hash();
  
  sprintf(select,"SELECT idCARD FROM CARD WHERE hash = '%s';", hashCARD.c_str());
  
  char ac = '0';

  Serial.println();
  
  Serial.println(hashCARD);

  Serial.println(F("\nRunning SELECT and printing results\n"));

  // Initiate the query class instance
  MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
  Serial.println("Conn");
  const char* querySelectDB = "USE arduino";

  cur_mem->execute(querySelectDB);

  Serial.println(select);

  cur_mem->execute(select);

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
      Serial.println(F("Identificação encontrada"));
      if (acess) {
        ac = '1';
        green();
        //digitalWrite(relayPin, HIGH);
        delay(2000);
        led_off();  // OFF green
        //digitalWrite(relayPin, LOW);
      } else {
        red();  // ON red
        Serial.println("Identificação com acesso insuficiente");
        delay(2000);
        led_off();  // OFF red
      }
    }
    count++;
  } while (row != NULL);

  sprintf(insert, "INSERT INTO LOGS (hashCARD, data, granted) VALUES ('%s', NOW(), %c);", hashCARD.c_str(), ac);

  cur_mem->execute(insert);
  delete cur_mem;
  Serial.println(F("Aguardando Leitura"));
}
void setColor(int red, int green, int blue){
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

String hash()
{
  byte data[4];
  
  for (byte i = 0; i < 4; i++) {
    data[i] = mfrc522.uid.uidByte[i];
  }
  
  mfrc522.PICC_HaltA();
  
  byte hashLen = 16;
  byte digest[hashLen];

  unsigned int i;
  
  spritz_hash(digest, hashLen, data, 4);

  String hash;
  
  for ( i = 0; i < sizeof(digest);i++){
    hash.concat(String(digest[i], HEX));
  }
  
  return hash;
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
void purple() {
  setColor(255, 0, 255);
}
void led_off() {
  setColor(0, 0, 0);
}

