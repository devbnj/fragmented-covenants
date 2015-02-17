//
//  Searcher_Mcu
//  For the Book Fragmented Covenants
//
//
//  Author :  Dev B
//  Date   :  Feb 16, 2015
//  Version:  1.00
//  
//  License Open Source - Apache License 2.0
//  This software is distributed under the terms 
//  of the Open Source Apache v2.0 license
//  This program is distributed in the hope that it will be useful
//  The Author Makes No Warranties, Express OR Implied
//
//

#include <WiFi.h>
#include <aJSON.h>
#include <WiFiClient.h>
#define WIFI_SSID "your_ssid"
#define WPA_PASSWORD "your_password"

// #define __CC3200R1M1RGC__
#include "Energia.h"

// Include application, user and local libraries
#include "SPI.h"
#include "LCD_SharpBoosterPack_SPI.h"


// Variables
// WiFiClient client;
LCD_SharpBoosterPack_SPI myScreen;
uint8_t k = 0;
WiFiClient client;

// Add setup code
void setup() {
#if defined(__MSP430__)
  SPI.begin();
  SPI.setClockDivider(SPI_CLOCK_DIV2);
  SPI.setBitOrder(MSBFIRST);
  SPI.setDataMode(SPI_MODE0);
#elif defined(__CC3200R1M1RGC__)
  SPI.begin();
  SPI.setClockDivider(SPI_CLOCK_DIV2); 
  SPI.setBitOrder(MSBFIRST);
  SPI.setDataMode(SPI_MODE0);
#endif

  int wifiStatus = WL_IDLE_STATUS;

  Serial.begin(115200);
  myScreen.begin();

  myScreen.setFont(1);
  myScreen.text(10, 10, "Search");
  Serial.println("Searcher...");

  myScreen.setFont(0);
  if (WiFi.status() == WL_NO_SHIELD) {
    myScreen.text(10, 25, "FAIL");
    Serial.println("WiFi Shield failed...");
    // If there's no WiFi shield, stop here.
    while(true);
  }

  while (wifiStatus != WL_CONNECTED) {
    wifiStatus = WiFi.begin(WIFI_SSID, WPA_PASSWORD);
    if (wifiStatus == WL_CONNECTED) {
      myScreen.text(10, 25, "Wifi: OK");
      Serial.println("Wifi OK");
    } 
    delay(300);
  }

  Serial.println("\nYou're connected to the network");
  Serial.println("Waiting for an ip address");

  while (WiFi.localIP() == INADDR_NONE) {
    // wait
    Serial.print(".");
    delay(300);
  }

  Serial.println("\nIP Address obtained");
  IPAddress ip = WiFi.localIP();
  Serial.print("IP: ");
  Serial.println(ip);

  myScreen.setFont(0);
  char server[] = "192.168.1.xxx"; 
  if (client.connect(server, 8080)) {
    client.println("GET /Searcher/rest/searcher/isgoodtime HTTP/1.1");
    client.println("Host: 192.168.1.xxx");
    client.println("Content-Type: application/json");
    client.println("Connection: close");
    client.println();
    Serial.println(server);
  } 
  else
    Serial.println("Cant connect");

  myScreen.flush();  

  delay(1000);
}


// Add loop code
void loop() {

  String jsonstr = "";

  while (client.available()) {
    char c = client.read();
    jsonstr += String(c);
  }
  
  Serial.print("String : ");
  Serial.print(jsonstr);
  Serial.println("... reading done");
  
  String js = jsonstr.substring(jsonstr.indexOf("{"));
  Serial.print("New string: ");
  Serial.println(js);
  
  char* jstr = strcpy((char*)malloc(js.length()+1), js.c_str());
  
  aJsonObject* root = aJson.parse((char *)jstr);
  aJsonObject* ui = aJson.getObjectItem(root, "unodeInausp");
  aJsonObject* si = aJson.getObjectItem(root, "saturnInausp");
  aJsonObject* di = aJson.getObjectItem(root, "deathBearing");
  aJsonObject* da = aJson.getObjectItem(root, "directionInausp");
  Serial.print("unode : ");
  Serial.println(ui->valuestring);

  myScreen.text(10, 35, "Inauspicious");
  myScreen.text(10, 45, "Rahu");
  myScreen.text(70, 45, ui->valuestring);
  myScreen.text(10, 55, "Saturn");
  myScreen.text(70, 55, si->valuestring);
  myScreen.text(10, 65, "Death");
  myScreen.text(70, 65, di->valuestring);
  myScreen.text(10, 75, "Direction");
  myScreen.text(70, 75, da->valuestring);
  myScreen.flush();

  Serial.println("... reading done");
  
  // if the server's disconnected, stop the client:
  if (!client.connected()) {
    myScreen.text(10, 85, "disconnected");
    Serial.println("Disconnected");
    myScreen.flush();
    client.stop();
    // do nothing forevermore:
    while (true);
  }
}




