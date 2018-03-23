
//20 / 03 / 2018
// Coin.java 
 package mario;

import java.applet.*;
/** 11 12 */  public class Coin extends WorldObject {
 /* Permite que todos los objetos de esta misma clase 16 * puedan pasar de una imagen a otra todos sincronizados. 17 */ 18 protected static int indexClass = 0;
  /* Indica que es necesario actualizar el indexClass. */ 21 protected static boolean changeling = false;
  /* Numero de imagenes que representan al sprite. */ 24 public static int LENGHT_IMAGES = 4;
  /* Permite actualizar las imagenes para todos los objetos 27 * de esa clase. */ 28 public static void actClass() f 29
 if (changeling) 1 30 indexClass = (indexClass + 1) % LENGHT_IMAGES;
 changelmg = false;
 /* Cuenta el numero de monedas que se han creado. */ 36 public static int N_COINS = 0;
 /* Cuenta las monedas cogidas. */ 39 public static int COINS_CATCHED = 0;
 public String imgNormal = "coin*_0";
 public String imgAnimation = "coinEfect*_0";
  // Indica si la moneda esta reproduciendo 46 // el efecto que le va a hacer desaparecer. 47 public boolean effect = false; 48 49 public static AudioClip[] audio; 50 public static int indexAudio; 51 public static boolean first = true; 52 53 public Coin(Stage s) { 54 super(s); 55 setPreferredSize(map.tileXSize, map.tileYSize); 56 setlmages(imgNormal, 0, LENGHT_IMAGES); 57 // Rectangulo para las colisiones 58 bounds.add(new java.awt.Rectangle( 59 2, 2, width-4, height-4)); 60 N_COINS++; 61 if (first) { 62 first = false;
 Coin.java
 //2 WH / 2018
  indexAudio = 0;
  audio = new AudioClip[5];
 
 for (int i = 0; i < audio.length; i++) f 66 audio[i] = stage.getSoundsLoader() 67.getAudio("coin.wav", true, true);
 
 }
  public void act() {
    move();
   
   if (effect) {
     int frameFrec = (int)(stage.getFPS() / 20);
    
    if (frameFrec == 0  II stage.getTotalUpdates() % frameFrec == 0) {
     
     if (nextImg()) {
       delete = true;
       COINS_CATCHED++;
      
     }
       
     else f 84 int frameFrec = (int)(stage.getFPS() / 10);
     
     if (frameFrec == 0 86 II stage.getTotalUpdates() % frameFrec == 0) {
       setImage(indexClass);
       changelmg = true;
       public void collision(Sprite s) {
         // prueba la diferencia entre 95 // utilizar un mismo sonido 96 // y utilizar copias de ese sonido 97 if (!effect) f 98 stage.getSoundsLoader().play( 99 "coin.wav", false); 100 audio[indexAudio].play(); 101 indexAudio = (indexAudio+1)%audio.length; 102 setlmages(imgAnimation, 0, 7); 103 effect = true; 104 speed.setY(2); 105 1 106 1 107 108 1 // fin de la clase Coin 109 110 // fin de Coin.java   
        //Page 2