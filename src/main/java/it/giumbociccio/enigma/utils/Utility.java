package it.giumbociccio.enigma.utils;

import java.util.HashMap;

public class Utility {

	private static String alf_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static char[] alfabeto = alf_str.toCharArray();

	public static int letterToInt(char letter) { // (0-25) es: letterToInt('B') --> return 1
		return (int) ("" + letter).toUpperCase().charAt(0) - (int) 'A';
	}

	public static char intToLetter(int alphabetPosition) { // (0-25) es: intToLetter(3) --> return D
		return (char) ('A' + alphabetPosition);
	}

	public static boolean isLetter(char input) {
		char letter = Character.toUpperCase(input);
		boolean toReturn = false;
		for (char c : alfabeto) {
			if (letter == c) {
				toReturn = true;
			}
		}
		return toReturn;
	}
	
	public static HashMap<Character, Character> stringToHashMap(String stringa) {
		HashMap<Character, Character> mappa = new HashMap<Character, Character>();
		char[] array = stringa.toCharArray();
		for(int i = 0; i < alfabeto.length; i++) {
			mappa.put(alfabeto[i], array[i]);
		}
		
		return mappa;
	}
	
	public static String hashMapToString(HashMap<Character, Character> mappa) {
		String stringa = "";
		for(char c : mappa.values()) {
			stringa += c;
		}
		return stringa;
	}

}