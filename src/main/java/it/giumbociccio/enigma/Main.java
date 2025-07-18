package it.giumbociccio.enigma;

import it.giumbociccio.enigma.config.SettingsManager;
import it.giumbociccio.enigma.console.EnigmaConsoleUI;
import it.giumbociccio.enigma.logic.EnigmaMachine;
import it.giumbociccio.enigma.model.EnigmaSettings;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(firstLinePattern("BENVENUTO NELL'EMULATORE DI ENIGMA"));
		try {
			// 1. Carica le impostazioni da file JSON
			SettingsManager settingsManager = new SettingsManager();
			EnigmaSettings settings = settingsManager.loadSettings();

			// 2. Crea la macchina Enigma con le impostazioni caricate
			EnigmaMachine machine = new EnigmaMachine(settings);

			// 3. Avvia l’interfaccia console
			EnigmaConsoleUI consoleUI = new EnigmaConsoleUI(machine);
			consoleUI.start();

			// 4. Salva le impostazioni aggiornate al termine (opzionale)
			settingsManager.saveSettings(machine.getCurrentSettings());

		} catch (Exception e) {
			System.err.println("Errore durante l'avvio della macchina Enigma:");
			e.printStackTrace();
		}
	}

	private static String firstLinePattern(String frase) {
		String toReturn = " /\\/\\";
		for(int i = 0; i < (frase.length()/4 - 1); i++) {
			toReturn += "/\\/\\";
		}
		toReturn += "/\\/\\" + "\n";
		toReturn += "< " + frase + " >" + "\n";
		toReturn += " \\/\\/";
		for(int i = 0; i < (frase.length()/4 - 1); i++) {
			toReturn += "\\/\\/";
		}
		toReturn += "\\/\\/";
		
		return toReturn;
	} 

}