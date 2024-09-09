import java.util.*;

public class Plugboard{
  private ArrayList<Pair<Character, Character>> connections;
  
  public Plugboard(ArrayList<Pair<Character, Character>> connections){
    this.connections = connections;
  }

  public char changeLetter(char letter){
    char toReturn = letter;
    for(int i = 0; i < this.connections.size(); i++){
      char[] pair = {this.connections.get(i).getKey(), this.connections.get(i).getValue()};
      if(pair[0] == letter){
        toReturn = pair[1];
      } else if(pair[1] == letter){
        toReturn = pair[0];
      }
    }
    return toReturn;
  }
}