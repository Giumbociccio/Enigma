import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();
    char letter = input.charAt(0);
    
    Rotor r1 = new Rotor(22, new ArrayList<Pair<Character, Character>>(Arrays.asList(new Pair<Character, Character>('A', 'E'))));
    
    // Rotors rotors = new Rotors(3);
    System.out.println(letter);    
  }
}