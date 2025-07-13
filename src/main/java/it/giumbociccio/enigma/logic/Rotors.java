package it.giumbociccio.enigma.logic;

import it.giumbociccio.enigma.utils.Utility;
import java.util.*;

public class Rotors {
	private List<Rotor> rotors;
	private HashMap<Character, Character> reflector;

	public Rotors(List<Rotor> rotors, HashMap<Character, Character> reflector) {
		super();
		this.rotors = rotors;
		this.reflector = reflector;
	}

	public char changeLetter(char letter) {

		rotateRotors();

		// passa nei 3 rotori
		char updatedLetter = rotorsChangeLetter(letter, true);

		// passa nel reflector
		updatedLetter = reflectorChangeLetter(updatedLetter);

		// passa nei 3 rotori (di nuovo)
		updatedLetter = rotorsChangeLetter(updatedLetter, false);

		return updatedLetter;
	}

	private char reflectorChangeLetter(char letter) {
//		char updatedLetter = this.reflector.charAt(Utility.letterToInt(letter));
		char updatedLetter = this.reflector.get(letter);
		return updatedLetter;
	}

	private char rotorsChangeLetter(char letter, boolean rightToLeft) {
		char currentLetter = letter;
		// 3 rotori
		for (int i = 0; i < this.rotors.size(); i++) {
			int index = rightToLeft ? i : this.rotors.size() - i - 1;
			Rotor r = this.rotors.get(index);
			currentLetter = r.changeLetter(Utility.intToLetter(currentLetter));
		}
		return currentLetter;
	}

	private void rotateRotors() {
		rotateRotor(0);
		for (int i = 1; i < this.rotors.size(); i++) {
			int rotorRotation = this.rotors.get(i - 1).getCurrentPin();
			Rotor r = this.rotors.get(i - 1);
			if (rotorRotation == r.getNotchPin()) {
				rotateRotor(i);
			}
		}
	}

	private void rotateRotor(int index) {
		int currentRotation = this.rotors.get(index).getCurrentPin();
		int finalRotation = (currentRotation + 1) % 26;
		this.rotors.get(index).setCurrentPin(finalRotation);
	}

	@Override
	public String toString() {
		return "Rotors [rotors=" + rotors + ", reflector=" + reflector + "]";
	}

}