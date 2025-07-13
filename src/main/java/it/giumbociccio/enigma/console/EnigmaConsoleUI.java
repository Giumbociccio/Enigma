package it.giumbociccio.enigma.console;

import java.util.*;

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
					s.close();
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
		System.out.println("Modifica le impostazioni:");
		// Plugboard
		System.out.println("Imposta la plugboard");
		for (char connection : Utility.alfabeto) {
			boolean isLetter = false;
			while (!isLetter) {
				System.out.print(connection + ": ");
				char risposta = s.nextLine().charAt(0);
				isLetter = Utility.isLetter(risposta);
				if (!isLetter) {
					System.err.println(errore);
				}
			}
		}
		// Rotori
		System.out.println("Imposta i rotori");
		// Reflector
		System.out.println("Seleziona il riflettore");

//		scrivi();
	}
}
