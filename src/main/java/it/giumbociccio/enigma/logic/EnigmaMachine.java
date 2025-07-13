package it.giumbociccio.enigma.logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.giumbociccio.enigma.model.EnigmaSettings;
import it.giumbociccio.enigma.utils.Utility;

public class EnigmaMachine {
	private EnigmaSettings settings;

	public EnigmaMachine(EnigmaSettings settings) {
		this.settings = settings;
	}

	public EnigmaSettings getCurrentSettings() {
		return this.settings;
	}

//----------------------------------------------------------- change letter
	public char changeLetter(char letter) {
		// plugboard
		char updatedLetter = plugboardChangeLetter(letter);
		// rotori + reflector
		updatedLetter = rotorsChangeLetter(updatedLetter);
		// plugboard
		updatedLetter = plugboardChangeLetter(updatedLetter);
		return updatedLetter;
	}

	private char plugboardChangeLetter(char letter) {
		String plugboard = this.settings.getPlugboard();
		char updatedLetter = plugboard.charAt(Utility.letterToInt(letter));
		return updatedLetter;
	}

	private char rotorsChangeLetter(char letter) {
		char updatedLetter = letter;
		try {
			Rotors rotors = this.settings.getRotors();
			updatedLetter = rotors.changeLetter(letter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updatedLetter;
	}

}