const int led =13;



void setup() {
  // put your setup code here, to run once:
  pinMode(led,OUTPUT);

  Serial.begin(9600);
  Serial.flush();
}

void loop() {
  String input;
  
  // put your main code here, to run repeatedly:
  while (Serial.available() > 0){
   input = Serial.readString();
    delay(5);
  }
  if(input =="on"){
    digitalWrite(led,HIGH);
    Serial.println("success");  
    
  }
  else if(input== "off")
  {
    digitalWrite(led,LOW);
    Serial.println("off"); 
  }
}
