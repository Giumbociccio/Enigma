import java.util.*;

public class Rotors{
  private ArrayList<Pair<Rotor, Integer>> rotors;
  private Reflector reflector;

  public Rotors(ArrayList<Pair<Rotor, Integer>> rotors, Reflector reflector){
    this.rotors = rotors;
    this.reflector = reflector;
  }

  public char changeLetter(char letter)
  {
    
    char currentLetter = letter;
    rotateRotors();
    
    //passa nei 3 rotori
    currentLetter = rotorsConversion(currentLetter, true);

    //passa nel reflector
    currentLetter = reflector.changeLetter(currentLetter);

    //passa nei 3 rotori (again)    
    currentLetter = rotorsConversion(currentLetter, false);

    return currentLetter;
  }

  private char rotorsConversion(char letter, boolean rightToLeft)
  {
    char currentLetter = letter;
    // 3 rotori
    for(int i = 0; i < this.rotors.size(); i++)
    {
      int index = rightToLeft? i : this.rotors.size() - i - 1;
      Rotor r = this.rotors.get(index).getKey();
      int currentRotation = this.rotors.get(index).getValue();
      int inputPin = Utility.letterToInt(currentLetter);
      int actualPin = (inputPin + currentRotation) % 26;
      currentLetter = r.changeLetter(Utility.intToLetter(actualPin, true));
    }
    return currentLetter;
  }
  
  private void rotateRotors()
  {
    rotateRotor(0);
    for(int i = 1; i < this.rotors.size(); i++) {
      int rotorRotation = this.rotors.get(i-1).getValue();
      Rotor r = this.rotors.get(i-1).getKey();
      if(rotorRotation == r.getRotationPin()){
        rotateRotor(i);
      }
    }
  }

  private void rotateRotor(int index)
  {
    int currentRotation = this.rotors.get(index).getValue();
    int finalRotation = (currentRotation + 1) % 26;
    this.rotors.get(index).setValue(finalRotation);
  }
  
  private void rotateRotor(Rotor r){
    rotateRotor(rotors.indexOf(r));
  }
  
}

/*
char a = 'A';
for (int i = 0; i < 26; i++){
  if(i + this.rotation < 26){
    this.numbers[i] = i + this.rotation;
  } else {
    this.numbers[i] = i + this.rotation - 26;
  }
  this.alphabet[this.numbers[i]] = a;
  a++;
}
*/