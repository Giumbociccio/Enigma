package it.giumbociccio.enigma.console;

import java.util.*;

import it.giumbociccio.enigma.Main;
import it.giumbociccio.enigma.logic.EnigmaMachine;
import it.giumbociccio.enigma.utils.Utility;

public class EnigmaConsoleUI {
	private Scanner s = new Scanner(System.in);
	private String messaggio = "\nCosa vuoi fare?\n1) Criptare un messaggio\n2) Modificare le impostazioni\n3) Termina";
	private String errore = "ERRORE! Inserisci un valore valido";
	private EnigmaMachine enigma;

	public EnigmaConsoleUI(EnigmaMachine machine) {
		this.enigma = machine;
	}

	public void start() {

		boolean play = true;
		while (play) {
			System.out.println(messaggio);

			try {
				switch (s.nextInt()) {
				case 1:
					s.nextLine();
					encrypt();
					break;
				case 2:
					s.nextLine();
					settings();
					break;
				case 3:
					play = false;
					break;
				default:
					System.err.println(errore);
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println(errore);
				s.nextLine();
			}
		}
	}

	public void encrypt() {

		boolean isLetter = false;
		String result = "";
		while (!isLetter) {
			System.out.print("\nInserisci la frase da de/criptare: ");
			String frase = s.nextLine().toUpperCase();
			char[] letters = frase.toCharArray();
			for (char letter : letters) {
				isLetter = Utility.isLetter(letter);
				if (letter == ' ') {
					result += " ";
					continue;
				} else if (!isLetter) {
					System.err.println("ATTENZIONE! '" + letter + "' non è una lettera.");
					break;
				} else {

					result += this.enigma.changeLetter(letter);
				}
			}

			System.out.println(result);
			result = "";

		}

	}

	public void settings() {
		System.out.println("\nModifica le impostazioni:");
		// Plugboard
		System.out.println("Imposta la plugboard");
		String plugboard = plugboardSettings();

		// Rotori
		System.out.println("Imposta i rotori");
		String rotors = rotorsSettings();
		
		// Reflector
		System.out.println("Seleziona il riflettore");
		String reflector = reflectorSettings();
	}

	private String plugboardSettings() {
		char[] plugboard = Utility.alfabeto.clone();

		System.out.println("Inserisci le coppie di lettere che vuoi connettere (max. 10): es. 'AG, KY, SZ'");
		String risposta = s.nextLine().toUpperCase().trim();

		if (!validateConnections(risposta)) {
			return plugboardSettings(); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido, applica le connessioni
		String[] pairs = risposta.split(",\\s*");
		for (String pair : pairs) {
			char firstLetter = pair.charAt(0);
			char secondLetter = pair.charAt(1);
			plugboard[Utility.letterToInt(firstLetter)] = secondLetter;
			plugboard[Utility.letterToInt(secondLetter)] = firstLetter;
		}

		System.out.println(new String(plugboard));
		return new String(plugboard);
	}
	
	private String rotorsSettings() {
		System.out.println("Rotori disponibili: 1, 2, 3, 4, 5");
		System.out.println("Scegli 3 rotori da destra a sinistra (1° = rotore veloce): es. '3, 5, 2'");
		String risposta = s.nextLine();

		String[] rotors = risposta.trim().split(",\\s*");
		
		if (!validateRotors(rotors)) {
			return rotorsSettings(); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido
	
		
		String toReturn = "";
		return toReturn;
	}
	
	private String reflectorSettings() {
		// TODO Auto-generated method stub
		return null;
	}
	
		private boolean validateRotors(String[] rotors) {
		boolean toReturn = true;

		String rotoriDisponibili = "12345";
		
		for(String rotor : rotors) {
			if(rotoriDisponibili.contains(rotor)) {
				rotoriDisponibili.replace(rotor, "");
			} else {
				System.err.println("ERRORE! Rotore '" + rotor + "' non trovato o già inserito\n"); //non disponibile
				toReturn = false;
			}
		}

		if (rotors.length != 3) {
			System.err.println("ERRORE! Trovati " + rotors.length + " rotori su 3\nInserire 3 rotori\n");
			toReturn = false;
		}

		return toReturn;
	}

	boolean validateConnections(String risposta) {
		String[] pairs = risposta.split(",\\s*");
		boolean toReturn = true;

		for(char l : risposta.toCharArray()) {
			if(!Utility.isLetter(l) && l != ',' && l != ' ') {
				System.err.println("ERRORE! '" + l + "' non è una lettera");
				return false;
			}
		}

		String connections = "";
		if (pairs.length > 10) {
			System.err.println("ERRORE! Trovate " + pairs.length + " connessioni su un massimo di 10\n");
			toReturn = false;
		}

		for (String pair : pairs) {
			if (pair.length() != 2) {
				System.err.println("ERRORE! Ogni coppia deve contenere esattamente due lettere. " + pair + " contiene "
						+ pair.length() + " lettere\n");
				return false;
			}

			char first = pair.charAt(0);
			char second = pair.charAt(1);

			if (first == second) {
				System.err.println("ERRORE! Non si può collegare una lettera a se stessa. '" + first + "' <--> '"
						+ second + "'\n");
				toReturn = false;
			}
			for (char letter : pair.toCharArray()) {
				if (connections.contains(letter + "")) {
					int position = connections.indexOf(letter);
					position += position % 2 == 0 ? +1 : -1;
					System.err.println("ERRORE! Lettera già connessa. '" + letter + "' <--> '"
							+ connections.charAt(position) + "'. Impossibile collegare '" + letter + "' <--> '"
							+ (letter == first ? second : first) + "'\n");
					toReturn = false;
				}
			}
			connections += pair;
		}

		return toReturn;
	}

}
