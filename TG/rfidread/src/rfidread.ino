#include <SPI.h>
#include <MFRC522.h>

#include <SpritzCipher.h>

constexpr uint8_t RST_PIN = 9;          // Configurable, see typical pin layout above
constexpr uint8_t SS_PIN = 10;         // Configurable, see typical pin layout above
byte readCard[4];

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

byte mac_addr[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

void setup() {
  Serial.begin(115200);
  while (!Serial); // wait for serial port to connect

  SPI.begin();      // Init SPI bus
  mfrc522.PCD_Init();   // Init MFRC522
}

void loop() {
  if ( ! mfrc522.PICC_IsNewCardPresent() || ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }
  Serial.println(hash());
}

String hash(){
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
