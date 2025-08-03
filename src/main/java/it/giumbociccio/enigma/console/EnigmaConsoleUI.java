package it.giumbociccio.enigma.console;

import java.util.*;

import it.giumbociccio.enigma.logic.EnigmaMachine;
import it.giumbociccio.enigma.model.EnigmaSettings;
import it.giumbociccio.enigma.utils.Utility;

public class EnigmaConsoleUI {
	private Scanner s = new Scanner(System.in);
	private String messaggio = "\nCosa vuoi fare?\n1) Criptare un messaggio\n2) Modificare le impostazioni\n3) Termina (Salva impostazioni attuali)";
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
		while (true) {
			System.out.print("\nInserisci la frase da de/criptare: ");
			String frase = s.nextLine().toUpperCase();

			if (!validateInput(frase)) {
				continue;
			}

			StringBuilder result = new StringBuilder();
			for (char letter : frase.toCharArray()) {
				if (letter == ' ') {
					result.append(" ");
				} else {
					result.append(this.enigma.changeLetter(letter));
				}
			}

			System.out.println(result.toString());
			break;
		}

	}

	private boolean validateInput(String input) {
		if (input == null || input.isEmpty()) {
		    System.err.println("ERRORE! Il campo è vuoto.");
		    return false;
		}
		
		boolean toReturn = true;
		for (char letter : input.toCharArray()) {
			if (!Utility.isLetter(letter) && letter != ' ') {
				System.err.println("ATTENZIONE! '" + letter + "' non è una lettera.");
				toReturn = false;
			}
		}
		return toReturn;
	}

	public void settings() {
		EnigmaSettings current = this.enigma.getCurrentSettings();
		System.out.println("\nModifica le impostazioni:");
		// Plugboard
		String currentPlugboard = current.getPlugboardConnections();
		System.out.println("Imposta la plugboard (default: " + currentPlugboard + ")");
		String plugboard = configurePlugboard(current.getPlugboard());

		// Rotori
		List<String> currentRotors = current.getRotorsOrder();
		System.out.println("Imposta i rotori (default: " + currentRotors + ")");
		List<String> rotorsOrder = configureRotors(currentRotors);

		// initialPositions
		String currentInitialPositions = current.getInitialPositions();
		System.out.println("Imposta le initialPositions (default: " + currentInitialPositions + ")");
		String initialPositions = configureInitialPositions(currentInitialPositions);

		// ringSettings
		String currentRingSettings = current.getRingSettings();
		System.out.println("Imposta i ringSettings (default: " + currentRingSettings + ")");
		String ringSettings = configureRingSettings(currentRingSettings);

		// Reflector
		String currentReflector = current.getReflectorType();
		System.out.println("Seleziona il riflettore (default: " + currentReflector + ")");
		String reflector = configureReflector(currentReflector);

		// Modifica le impostazioni
		EnigmaSettings settings = new EnigmaSettings(plugboard, rotorsOrder, initialPositions, ringSettings, reflector);
		this.enigma.setSettings(settings);
	}

	private String configurePlugboard(String currentPlugboard) {
		char[] plugboard = Utility.alfabeto.clone();

		System.out.println("Inserisci le coppie di lettere che vuoi connettere (max. 10): es. 'AG, KY, SZ'");
		String risposta = s.nextLine().toUpperCase().trim();
		
		if (risposta == null || risposta.isEmpty()) {
			System.out.println("**impostati valori di default**\n");
			return currentPlugboard;
		}

		if (!validatePlugboard(risposta)) {
			return configurePlugboard(risposta); // richiama solo se qualcosa è andato storto
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

	private List<String> configureRotors(List<String> currentRotors) {
		System.out.println("Rotori disponibili 1, 2, 3, 4, 5");
		System.out.println("Scegli 3 rotori da destra a sinistra (1° = rotore veloce): es. '3, 5, 2'");
		String risposta = s.nextLine().trim();
		String[] rotors = risposta.split(",\\s*");
		
		if (risposta == null || risposta.isEmpty()) {
			System.out.println("**impostati valori di default**\n");
			return currentRotors;
		}
		
		if (!validateRotors(rotors)) {
			return configureRotors(currentRotors); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido
		return Arrays.asList(rotors);
	}

	private String configureInitialPositions(String currentInitialPositions) {
		System.out.println("Scegli le posizioni iniziali di ogni rotore: es. 'AFW'");
		String initialPositions = s.nextLine().toUpperCase().trim();

		if (initialPositions == null || initialPositions.isEmpty()) {
			System.out.println("**impostati valori di default**\n");
			return currentInitialPositions;
		}

		if (!validateInitialPositions(initialPositions)) {
			return configureInitialPositions(initialPositions); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido
		return initialPositions;
	}

	private String configureRingSettings(String currentRingSettings) {
		System.out.println("Scegli le impostazioni dell'anello di ogni rotore: es. 'BKJ'");
		String ringSettings = s.nextLine().toUpperCase().trim();

		if (ringSettings == null || ringSettings.isEmpty()) {
			System.out.println("**impostati valori di default**\n");
			return currentRingSettings;
		}

		if (!validateRingSettings(ringSettings)) {
			return configureRingSettings(ringSettings); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido
		return ringSettings;
	}

	private String configureReflector(String currentReflector) {
		System.out.println("Scegli un reflector");
		System.out.println("Reflectors disponibili: A, B, C");
		String reflector = s.nextLine().toUpperCase();

		if (reflector == null || reflector.isEmpty()) {
			System.out.println("**impostati valori di default**\n");
			return currentReflector;
		}

		if (!validateReflector(reflector)) {
			return configureReflector(reflector); // richiama solo se qualcosa è andato storto
		}

		// Se tutto è valido
		return reflector;
	}

	boolean validatePlugboard(String risposta) {
		String[] pairs = risposta.split(",\\s*");
		boolean toReturn = true;

		for (char l : risposta.toCharArray()) {
			if (!Utility.isLetter(l) && l != ',' && l != ' ') {
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

	private boolean validateRotors(String[] rotors) {
		boolean toReturn = true;

		String rotoriDisponibili = "12345";

		for (String rotor : rotors) {
			if (rotoriDisponibili.contains(rotor) && rotor.length() == 1) {
				rotoriDisponibili.replace(rotor, "");
			} else {
				System.err.println("ERRORE! Rotore '" + rotor + "' non trovato o già inserito\n"); // non disponibile
				toReturn = false;
			}
		}

		if (rotors.length != 3) {
			System.err.println("ERRORE! Trovati " + rotors.length + " rotori su 3\nInserire 3 rotori\n");
			toReturn = false;
		}

		return toReturn;
	}

	private boolean validateInitialPositions(String initialPositions) {
		return validateThreeLetters(initialPositions);
	}

	private boolean validateRingSettings(String ringSettings) {
		return validateThreeLetters(ringSettings);
	}

	private boolean validateThreeLetters(String input) {
		boolean toReturn = true;

		for (char l : input.toCharArray()) {
			if (!Utility.isLetter(l)) {
				System.err.println("ERRORE! '" + l + "' non è una lettera");
				toReturn = false;
			}
		}

		if (input.length() != 3) {
			System.err.println("ERRORE! Inserire esattamente 3 lettere\n");
			toReturn = false;
		}

		return toReturn;
	}

	private boolean validateReflector(String reflector) {
		boolean toReturn = true;
		String reflectorsDisponibili = "ABC";
		if (reflector.length() != 1) {
			System.err.println("ERRORE! Inserire 1 reflector\n");
			toReturn = false;
		}

		if (!reflectorsDisponibili.contains(reflector)) {
			System.err.println("ERRORE! Reflector '" + reflector + "' non trovato\n");
			toReturn = false;
		}

		return toReturn;
	}
}
