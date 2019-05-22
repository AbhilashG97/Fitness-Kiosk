#include "Nextion.h"
// Declare your Nextion objects - Example (page id = 0, component id = 1, component name = "b0") 

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
  hr.setValue(77) ; //reading heart rate value
  ht.setValue(175);//reading height
  wt.setValue(72) ; //reading weight
  bmi.setValue(20); //reading bmi
}

/*
 * Button bOff component pop callback function. 
 * When the OFF button is released, the LED turns off and the state text changes. 
 
void bOffPopCallback(void *ptr) {
  tState.setText("State: off");
  digitalWrite(led1, LOW);
}*/ 


void setup(void) {    
  Serial.begin(9600);
    
  // You might need to change NexConfig.h file in your ITEADLIB_Arduino_Nextion folder
  // Set the baudrate which is for debug and communicate with Nextion screen
  nexInit();

  // Register the pop event callback function of the components
  measure.attachPop(start, &measure);
    
}

void loop(void) {   
  /*
   * When a pop or push event occured every time,
   * the corresponding component[right page id and component id] in touch event list will be asked.
   */
  nexLoop(nex_listen_list);
}
