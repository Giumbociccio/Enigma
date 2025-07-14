package it.giumbociccio.enigma.logic;

import it.giumbociccio.enigma.utils.Utility;

public class Rotor {
	private String connections;
	private int notchPin;
	private int currentPin;
	private int ringSetting;

	public Rotor(String connections, char notchPin, char currentPin, char ringSetting) {
		super();
		this.setConnections(connections);
		this.setNotchPin(Utility.letterToInt(notchPin));
		this.setCurrentPin(Utility.letterToInt(currentPin));
	}

	public Rotor(String connections, int notchPin, int currentPin, int ringSetting) {
		super();
		this.connections = connections;
		this.notchPin = notchPin;
		this.currentPin = currentPin;
		this.ringSetting = ringSetting;
	}

//	public char changeLetter(char letter) {
//		int position = (Utility.letterToInt(letter) + currentPin - ringSetting +26) % 26;
//		char updatedLetter = this.connections.charAt(position);
//		return updatedLetter;
//	}
	public char changeLetter(char letter) {
	    int intLetter = Utility.letterToInt(letter);  
	    // esempio: 'A' → 0

	    int shiftedIndex = (intLetter + currentPin - ringSetting + 26) % 26;  
	    // sposto la posizione della lettera, tenendo conto del rotore

	    char wiredLetter = this.connections.charAt(shiftedIndex);  
	    // leggo la lettera connessa nel cablaggio del rotore

	    int outputIndex = (Utility.letterToInt(wiredLetter) - currentPin + ringSetting + 26) % 26;  
	    // tolgo lo spostamento per tornare alla posizione originale

	    return Utility.intToLetter(outputIndex);  
	    // ritorno la lettera finale
	}
	
	public char inverseChangeLetter(char c) {
	    int intLetter = Utility.letterToInt(c);

	    int shiftedIndex = (intLetter + currentPin - ringSetting + 26) % 26;
	    char shiftedChar = Utility.intToLetter(shiftedIndex);

	    int indexInWiring = this.connections.indexOf(shiftedChar);  
	    // dove si trova la lettera in uscita?

	    int outputIndex = (indexInWiring - currentPin + ringSetting + 26) % 26;

	    return Utility.intToLetter(outputIndex);
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