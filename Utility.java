public class Utility {
  public static int letterToInt(char letter){
    return (int)("" + letter).toLowerCase().charAt(0) - (int)'a';
  }
  
  public static char intToLetter(int number, boolean upper){
    return (char)((upper? 'A' : 'a') + number);
  }

  //public static (){}
  
  
}