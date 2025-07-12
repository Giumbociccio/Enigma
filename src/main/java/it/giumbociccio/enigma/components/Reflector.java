package it.giumbociccio.enigma.components;

import java.util.*;

public class Reflector {
	private Plugboard reflector;

	public Reflector(HashMap<Character, Character> connections) {
		this.setReflector(new Plugboard(connections));
	}

	public Plugboard getReflector() {
		return reflector;
	}

	public void setReflector(Plugboard reflector) {
		this.reflector = reflector;
	}

	public char changeLetter(char currentLetter) {
		// TODO Auto-generated method stub
		return 0;
	}
}