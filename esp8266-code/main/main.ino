#include <ESP8266WiFi.h>

//needed for library
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <ESP8266HTTPClient.h>
#include "Nextion.h"
#include "HX711.h"
#include <math.h>
#include "UbidotsMicroESP8266.h"

#define TOKEN  "BBFF-aeXNLj859GY7djs7dgWTrr0UCVVtg6"  // Ubidots TOKEN
#define WIFISSID "Watermelon"                         // Wi-Fi SSID
#define PASSWORD "watermelon1234"                     // Wi-Fi password

Ubidots client(TOKEN);                                // Ubidots Client
int counter = 0;

// HMI [Nexiton]
//page 0
NexButton measure = NexButton(0, 1, "b0");

//page 1
NexText BMI = NexText(1, 1, "t0"); 
NexText HR = NexText(1, 2, "t1"); 
NexButton h1 = NexButton(1, 3, "b0");

//page 2
NexButton h2 = NexButton(2, 1, "b0");
NexButton b1 = NexButton(2, 2, "b1");
NexNumber ht = NexNumber(2, 4, "n0");
NexNumber wt = NexNumber(2, 6, "n1");
NexNumber bmi = NexNumber(2, 8, "n2");


//page 3
NexButton h3 = NexButton(3, 1, "b0");
NexButton b2 = NexButton(3, 2, "b1");
NexNumber hr = NexNumber(3, 4, "n0");

// Register a button object to the touch event list.  
NexTouch *nex_listen_list[] = {
  &measure,
  &BMI,
  &HR,
  &h1,
  &h2,
  &b1,
  &h3,
  &b2,
  NULL
};

void start(void *ptr) {
  hr.setValue(get_heart_beat()) ; //reading heart rate value
  ht.setValue(get_height());      //reading height
  wt.setValue(get_weight());      //reading weight
  bmi.setValue(get_BMI());        //reading bmi
}

void setup() {
  
  Serial.begin(115200);

  client.wifiConnection(WIFISSID, PASSWORD);
  client.setDataSourceLabel("esp8266");
  Serial.println("Connected to WiFi");

  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, HIGH);
  
  //nexInit();
  //measure.attachPop(start, &measure);
}

void loop ()
{
  counter++;
  client.add("height", 172);
  client.add("weight", 89);
  client.add("bmi", 25);
  client.add("heart_beat", 79);
  client.sendAll(true);
  Serial.println(counter);
  
  //nexLoop(nex_listen_list);
  
  delay(1000);  // 1 second interval between each new round
}

// Get all user data

//Get heart beat 
int get_heart_beat() 
{
  Serial.println("Heart beat sensor");
  int c, a = 0;
  boolean f;
  int t = millis();
  while (millis()-t<15000) 
  {
    yield();
    a = digitalRead(D2);
    if(a==HIGH && f==false) 
    {
        c =c+1;
        f = true;
    }
    if(a==LOW) 
    {
     yield();
     f = false;
    }
    yield();
  }  
  Serial.print("BPM:");
  yield();
  c = c * 4;
  Serial.println(c);
  return c;
}

// Get height - returns the height of the user
float get_height() 
{
  Serial.println("Height calculation");
  double distance;
  long duration;
  
  // Clears the trigPin
  digitalWrite(D4, LOW);
  delayMicroseconds(2);
  // Sets the trigPin on HIGH state for 10 micro seconds
  digitalWrite(D4, HIGH);
  delayMicroseconds(10);
  digitalWrite(D4, LOW);
  
  // Reads the echoPin, returns the sound wave travel time in microseconds
  duration = pulseIn(D3, HIGH);
  
  distance =  duration*0.034/2;
  
  Serial.print("Height: ");
  distance = 197 - distance;
  Serial.println(distance);
  delay(2000);
  return distance;
}

// Get Weigth - returns the weight of the usre
float get_weight() 
{
  Serial.println("Weight Calculation");
  HX711 scale(D8, D7);
  float reading;
  if(scale.is_ready()) 
  {
    reading = scale.read();
    reading = (reading * (-0.035274))/1580;
    Serial.print("HX711 reading: ");
    Serial.println(reading);
  } 
  else 
  {
    reading = 0.0;
    Serial.println("HX711 not found");
  }
  return reading;     
} 

// Get BMI - returns the BMI of the person standing on the Kisosk
float get_BMI() 
{
    Serial.println("BMI Calculation");
    float BMI = get_weight()/pow(get_height(), 2);
    return BMI;
}
