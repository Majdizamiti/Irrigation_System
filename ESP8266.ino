#include <Wire.h>
#include <Adafruit_ADS1X15.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <DHT_U.h>
#include <ESP8266WiFi.h>
#include <ESP8266Firebase.h>

#define DHTPIN 2
#define DHTTYPE    DHT11
#define projectID "smartgrow-da35d"
#define REFERENCE_URL "https://smartgrow-da35d-default-rtdb.firebaseio.com/"

const char* ssid = "Redmi 9T" ;
const char* password = "majd012345" ;

Firebase firebase(REFERENCE_URL);
Adafruit_ADS1015 ads;

DHT dht(DHTPIN, DHTTYPE);

void setup() {

Serial.begin(9600);
  /*sensor_t sensor;
  sensors_event_t event;
  dht.temperature().getSensor(&sensor);
  dht.humidity().getSensor(&sensor);
  dht.temperature().getEvent(&event);
  dht.humidity().getEvent(&event);*/
pinMode(D6, OUTPUT);
/*digitalWrite(D6, LOW);*/
Wire.begin(D2, D1);
ads.begin();
dht.begin();
WiFi.begin(ssid, password);

while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
}
// Affichage du message de confirmation de la connexion au WiFi
Serial.println("Connected to WiFi!");

}
void loop() {
float temperature = dht.readTemperature();
float humidity = dht.readHumidity();
int16_t water_level_1, soil_moisture, light_intensity;
  float volts0, volts1, volts2, volts3;

  water_level_1 = ads.readADC_SingleEnded(0);
  soil_moisture = ads.readADC_SingleEnded(3);
  light_intensity = ads.readADC_SingleEnded(2);

  volts0 = ads.computeVolts(water_level_1);
  volts1 = ads.computeVolts(soil_moisture);
  volts2 = ads.computeVolts( light_intensity);
Serial.print("Temperature: ");
Serial.print(temperature);
Serial.print(" °C, Humidity: ");
Serial.print(humidity);
Serial.println(" %");
firebase.setFloat("/temperature", temperature);
firebase.setFloat("/humidity", humidity);
  
firebase.setFloat("/water_level_1", volts0);
firebase.setFloat("/soil_moisture", volts1);
firebase.setFloat("/light_intensity", volts2);
  
delay(2000);
int vanne1 = firebase.getInt("/vanne1");
 if (vanne1 == 1) {
      digitalWrite(D6, LOW);
      Serial.println("Relais1 activé");
    } else {
      digitalWrite(D6, HIGH);
      Serial.println("Relais1 desactivé");
      
    }
    delay(1000); 
 
// Recevoir des données de Firebase
 firebase.getFloat(firebaseData, "/temperature_auto");
 temperature_auto = firebaseData.floatData();
 firebase.getFloat(firebaseData, "/humidity_auto");
 humidity_auto = firebaseData.floatData();
 firebase.getFloat(firebaseData, "/light_auto");
 light_auto = firebaseData.floatData();
 firebase.getFloat(firebaseData, "/soilmoisture_auto");
 soilmoisture_auto = firebaseData.floatData();
 firebase.getFloat(firebaseData, "/waterlevel1_auto");
 waterlevel1_auto = firebaseData.floatData();
 
 
 //verification de recuperation les valeurs automatiques
 if (firebase.getFloat(firebaseData, "/temperature_auto")) {
  // Récupération réussie
  temperature_auto = firebaseData.floatData();
  // Ecrire la valeur récupérée sur le moniteur série
  Serial.print("temperature auto value: ");
  Serial.println(temperature_auto);
 } else {
  // Échec de la récupération
  Serial.println(echec de la récupération de temperature auto depuis
 Firebase);
 }
 if (firebase.getFloat(firebaseData, "/humidity_auto")) {
  // Récupération réussie
  humidity_auto = firebaseData.floatData();
 
  // Ecrire la valeur récupérée sur le moniteur série
  Serial.print("humidity auto value: ");
  Serial.println(humidity_auto);
 } else {
  // Échec de la récupération
  Serial.println("Échec de la récupération de humidité auto depuis Firebase");
 }
 
if (firebase.getFloat(firebaseData, "/light_auto")) {
  // Récupération réussie
  light_auto = firebaseData.floatData();
  // Ecrire la valeur récupérée sur le moniteur série
  Serial.print("lumiére auto value: ");
  Serial.println(light_auto);
 } else {
  // Échec de la récupération
  Serial.println("Échec de la récupération de lumiére auto depuis Firebase");
 }
 
if (firebase.getFloat(firebaseData, "/soilmoisture_auto")) {
  // Récupération réussie
  soilmoisture_auto = firebaseData.floatData();
  // Ecrire la valeur récupérée sur le moniteur série
  Serial.print("solmoisture_auto value: ");
  Serial.println(soilmoisture_auto);
 } else {
  // Échec de la récupération
  Serial.println(echec de la récupération de humidité du sol auto depuis
 Firebase);
 }
 
if (firebase.getFloat(firebaseData, "/waterlevel1_auto")) {
  // Récupération réussie
  waterlevel1_auto = firebaseData.floatData();
  // Ecrire la valeur récupérée sur le moniteur série
  Serial.print("niveau eau1_auto value: ");
  Serial.println(waterlevel1_auto);
 } else {
  // Échec de la récupération
  Serial.println(echec de la récupération de niveau eau 1 auto depuis
 Firebase);
 }
 if (firebase.getFloat(firebaseData, "/waterlevel2_auto")) {
  // Récupération réussie
  waterlevel2_auto = firebaseData.floatData();
  // Ecrire la valeur récupérée sur le moniteur série
  Serial.print("niveau eau2_auto auto value: ");
  Serial.println(waterlevel2_auto);

} else {
  // Échec de la récupération
  Serial.println(Échec de la récupération de niveau eau 2 auto depuis
 Firebase);
 }
 // lire autoValue de Firebase
 firebase.getInt(firebaseData, "/autoValue");
 int autoValue = firebaseData.intData();
 firebase.getInt(firebaseData, "/vanne1");
 int vanne1 = firebaseData.intData();
 firebase.getInt(firebaseData, "/vanne2");
 int vanne2 = firebaseData.intData();
 //condition pour l'irrigation selon le choix de client
 if (autoValue == 1){
 if ((temperature_auto>temperature)&&(humidity_auto>humidity)&&(light_auto>light_inte
 nsity)&&(soilmoisture_auto>soil_moisture)&&(waterlevel1_auto>water_level_1)&&(wat
 erlevel1_auto>water_level_1)){
  turnRelaysOn() ;
 }else{
  turnRelaysOff() ;
 }
 } else {
 if (vanne1 == 1) {
   digitalWrite(D6, LOW);
   Serial.println("Relais1 activé");
   
  } else {
   digitalWrite(D6, HIGH);
   Serial.println("Relais1 desactivé");
   
  }
  // Contrôler le relais en fonction de la valeur de "vanne2"
  
  if (vanne2 == 1) {
   digitalWrite(D5, LOW);
   Serial.println("Relais2 activé");
   
  } else {
   digitalWrite(D5, HIGH);
   Serial.println("Relais2 desactivé");
   
  }
  delay(1000);
 
}
}
