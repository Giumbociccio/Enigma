package it.giumbociccio.enigma.logic;

import it.giumbociccio.enigma.model.EnigmaSettings;

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
		char updatedLetter = plugboardChangeLetter(letter);
		updatedLetter = rotorsChangeLetter(updatedLetter);
		updatedLetter = plugboardChangeLetter(updatedLetter);
		return updatedLetter;
	}

	private char plugboardChangeLetter(char letter) {
		char updatedLetter = this.settings.getPlugboard().get(letter);
		return updatedLetter;
	}
	
	private char rotorsChangeLetter(char letter) {
		char updatedLetter = this.settings.getRotors().changeLetter(letter);
		return updatedLetter;
	}

}