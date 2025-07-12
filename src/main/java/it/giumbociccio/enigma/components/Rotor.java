package it.giumbociccio.enigma.components;

import java.util.*;

import it.giumbociccio.enigma.utils.Utility;

public class Rotor {
	private HashMap<Character, Character> connections;
	private int notchPin;
	private int currentPin;

	public Rotor(HashMap<Character, Character> connections, char notchPin, char currentPin) {
		super();
		this.setConnections(connections);
		this.setNotchPin(Utility.letterToInt(notchPin));
		this.setCurrentPin(Utility.letterToInt(currentPin));
	}

	public char changeLetter(char letter) {
//		char updatedLetter = this.connections.get(letter);
		String connections = "QAZWSXEDCRFV";
		int position = Utility.letterToInt(letter);
		char updatedLetter = connections.charAt(position);
		return updatedLetter;
	}

	public String updateRotors() {
		String str = "rotorConnections"; // getConnections()
		String newConnections = "" + str.charAt(str.length() - 1) + str.substring(0, str.length() - 2);
		return newConnections;
	}

	public HashMap<Character, Character> getConnections() {
		return connections;
	}

	public void setConnections(HashMap<Character, Character> connections) {
		this.connections = connections;
	}

	public int getNotchPin() {
		return notchPin;
	}

	public void setNotchPin(int notchPin) {
		this.notchPin = notchPin;
	}

	public int getCurrentPin() {
		return currentPin;
	}

	public void setCurrentPin(int currentPin) {
		this.currentPin = currentPin;
	}

	public void increaseCurrentPin() {
		setCurrentPin((this.currentPin + 1) % 26);
	}

	@Override
	public String toString() {
		return "Rotor [connections=" + connections + ", notchPin=" + notchPin + ", currentPin=" + currentPin + "]";
	}

}