package it.giumbociccio.enigma.logic;

import it.giumbociccio.enigma.utils.Utility;

public class Rotor {
	private String connections;
	private int notchPin;
	private int currentPin;
	private int ringSetting;

	public Rotor(String connections, char notchPin, char currentPin) {
		super();
		this.setConnections(connections);
		this.setNotchPin(Utility.letterToInt(notchPin));
		this.setCurrentPin(Utility.letterToInt(currentPin));
	}

	public char changeLetter(char letter) {
		int position = Utility.letterToInt(letter) + currentPin - ringSetting % 26;
		char updatedLetter = this.connections.charAt(position);
		return updatedLetter;
	}

	public String updateRotors() {
		String str = "rotorConnections"; // getConnections()
		String newConnections = "" + str.charAt(str.length() - 1) + str.substring(0, str.length() - 2);
		return newConnections;
	}

	public String getConnections() {
		return connections;
	}

	public void setConnections(String connections) {
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

//	public int getRingSetting() {
//		return ringSetting;
//	}
//
//	public void setRingSetting(int ringSetting) {
//		this.ringSetting = ringSetting;
//	}

	public void increaseCurrentPin() {
		setCurrentPin((this.currentPin + 1) % 26);
	}

	@Override
	public String toString() {
		return "Rotor [connections=" + connections + ", notchPin=" + notchPin + ", currentPin=" + currentPin + "]";
	}

}