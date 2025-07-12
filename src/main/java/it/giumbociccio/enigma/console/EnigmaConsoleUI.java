package it.giumbociccio.enigma.console;

import java.util.*;

import it.giumbociccio.enigma.logic.EnigmaMachine;
import it.giumbociccio.enigma.utils.Utility;

public class EnigmaConsoleUI {
	private Scanner s = new Scanner(System.in);
	private String messaggio = "Cosa vuoi fare?\n1) Criptare un messaggio\n2) Modificare le impostazioni\n3) Termina";
	private EnigmaMachine enigma;
	
	public EnigmaConsoleUI(EnigmaMachine machine) {
		this.enigma = machine;
	}

	public void start() {
		boolean play = true;
		while (play) {
			System.out.println(messaggio);

			int scelta = s.nextInt();
//			prova();
//			createEnigma();
			try {
				switch (scelta) {
				case 1:
					s.nextLine();
					encrypt();
					break;
				case 2:
					settings();
					break;
				case 3:
					s.close();
					play = false;
					break;
				default:
					System.out.println("ERRORE! Inserisci un valore valido");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("ERRORE! Inserisci un valore valido");
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
					result = "ATTENZIONE! '" + letter + "' non è una lettera.";
					break;
				}
				result += this.enigma.changeLetter(letter);
			}
	
			System.out.println(result);
			result = "";
	
		}
	
	}
	
	public static void settings() {
		System.out.println("Modifica le impostazioni:");
		System.out.println("Imposta la plugboard");
		for (char connection : Utility.alfabeto) {
			System.out.println(connection);
		}
//		scrivi();
	}
}
