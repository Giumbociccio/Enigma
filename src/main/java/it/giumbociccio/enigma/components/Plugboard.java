package it.giumbociccio.enigma.components;

import java.util.*;

public class Plugboard{
  private HashMap<Character, Character> connections;
  
  public Plugboard(HashMap<Character, Character> connections){
    this.setConnections(connections);
  }

public HashMap<Character, Character> getConnections() {
	return connections;
}

public void setConnections(HashMap<Character, Character> connections) {
	this.connections = connections;
}


  public char changeLetter(char letter){
    char toReturn = letter;
//    for(int i = 0; i < this.connections.size(); i++){
//      char[] pair = {this.connections.get(i).getKey(), this.connections.get(i).getValue()};
//      if(pair[0] == letter){
//        toReturn = pair[1];
//      } else if(pair[1] == letter){
//        toReturn = pair[0];
//      }
//    }
    return toReturn;
  }
}