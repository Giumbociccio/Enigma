import java.util.*;

public class Rotor{
  private ArrayList<Pair<Character, Character>> pairs;
  private int rotationPin;
  
  public Rotor(int rotationPin, ArrayList<Pair<Character, Character>> pairs){
    this.rotationPin = rotationPin;
    this.pairs = pairs;
  }
  
  public char changeLetter(char letter){
    for(int i = 0; i < this.pairs.size(); i++) {
      if(this.pairs.get(i).getKey() == letter){
        return this.pairs.get(i).getValue();
      }
    }
    return '-';
   }

  public int getRotationPin()
  {
    return this.rotationPin;
  }
}