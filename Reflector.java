import java.util.*;

public class Reflector {
  private Plugboard plugboard;
  
  public Reflector(ArrayList<Pair<Character, Character>> _connections) {
    plugboard = new Plugboard(_connections);  
  }
  
  public char changeLetter(char letter){
    return plugboard.changeLetter(letter);
  }
}