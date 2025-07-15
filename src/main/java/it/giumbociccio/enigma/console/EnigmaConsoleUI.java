package it.giumbociccio.enigma.console;

import java.util.*;

import it.giumbociccio.enigma.Main;
import it.giumbociccio.enigma.logic.EnigmaMachine;
import it.giumbociccio.enigma.utils.Utility;

public class EnigmaConsoleUI {
	private Scanner s = new Scanner(System.in);
	private String messaggio = "Cosa vuoi fare?\n1) Criptare un messaggio\n2) Modificare le impostazioni\n3) Termina";
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
			System.out.print("Inserisci la frase da de/criptare: ");
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
		// Reflector
		System.out.println("Seleziona il riflettore");
	}

	private String plugboardSettings() {
		char[] plugboard = Utility.alfabeto.clone();

		System.out.println("Inserisci le coppie di lettere che vuoi connettere (max. 10): es. AG, KY, SZ");
		String risposta = s.nextLine().toUpperCase().trim();
		String[] connections = risposta.split(",\\s*");

		if (!validateConnections(connections)) {
			return plugboardSettings(); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido, applica le connessioni
		for (String pair : connections) {
			char firstLetter = pair.charAt(0);
			char secondLetter = pair.charAt(1);
			plugboard[Utility.letterToInt(firstLetter)] = secondLetter;
			plugboard[Utility.letterToInt(secondLetter)] = firstLetter;
		}

		System.out.println(new String(plugboard));
		return new String(plugboard);
	}

	private boolean validateConnections(String[] pairs) {
//		TODO: aggiungere la descrizione del problema
//		es: ("...lettera a se stessa." + first + " <--> " + second + "\n")
		boolean toReturn = true;

		String connections = "";

		String errors = "";
		if (pairs.length > 10) {
			System.err.println("ERRORE! Massimo 10 connessioni. Connessioni attuali: " + pairs.length + "\n");
			toReturn = false;
		}

		for (String pair : pairs) {
			if (pair.length() != 2) {
				System.err.println("ERRORE! Ogni coppia deve contenere esattamente due lettere. " + pair + " contiene "
						+ pair.length() + " lettere\n");
				if(pair.length() < 2) {
					return false;
				}
				toReturn = false;
			}

			char first = pair.charAt(0);
			char second = pair.charAt(1);

			if (first == second) {
				System.err.println("ERRORE! Non si può collegare una lettera a se stessa. '" + first + "' <--> '" + second
						+ "'\n");
				toReturn = false;
			}
			for (char letter : pair.toCharArray()) {
				if (connections.contains(letter + "")) {
					int position = connections.indexOf(letter);
					position += position % 2 == 0 ? +1 : -1;
					System.err.println("ERRORE! Lettera già connessa. '" + letter + "' <--> '" + connections.charAt(position)
							+ "'. Impossibile collegare '" + letter + "' <--> '" + (letter == first ? second : first)
							+ "'\n");
					toReturn = false;
				}
			}
			connections += pair;
		}


		return toReturn;
	}

//	private String plugboardSettings() {
//		char[] plugboard = Utility.alfabeto.clone();
//
//		System.out.println("Inserisci le coppie di lettere che vuoi connettere (max. 10): es. AG, KY, SZ");
//		String risposta = s.nextLine();
//
//		String[] connections = risposta.split(", ");
//		if(connections.length > 10) {
//			System.err.println("ERRORE! Massimo 10 connessioni");
//			return plugboardSettings();
//		}
//		for (String pair : connections) {
//			if (pair.length() != 2) {
//	            System.err.println("ERRORE! Ogni coppia deve contenere esattamente due lettere.");
//	            return plugboardSettings();
//	        }
//			char firstLetter = pair.charAt(0);
//			char secondLetter = pair.charAt(1);
//
//			// controllo se le lettere sono uguali, se la prima è già connessa, se la
//			// seconda è già connessa
//			if (firstLetter == secondLetter) {
//				System.err.println("ERRORE! Non si può collegare una lettera a se stessa");
//				return plugboardSettings();
//			} else if (alreadyConnected(plugboard, firstLetter) || alreadyConnected(plugboard, secondLetter)) {
//				System.err.println("ERRORE! Lettera già connessa");
//				return plugboardSettings();
//			} else {
//				plugboard[Utility.letterToInt(firstLetter)] = secondLetter;
//				plugboard[Utility.letterToInt(secondLetter)] = firstLetter;
//			}
//		}
//		System.out.println(new String(plugboard));
//		return new String(plugboard);
//	}
//	
//	private boolean alreadyConnected(char[] plugboard, char letter) {
//	    plugboard[Utility.letterToInt(letter)] = '\0'; // ← questo è pericoloso!
//	    boolean toReturn = false;
//	    for (char c : plugboard) {
//	        if (c == letter) {
//	            toReturn = true;
//	            break;
//	        }
//	    }
//	    return toReturn;
//	}

}
