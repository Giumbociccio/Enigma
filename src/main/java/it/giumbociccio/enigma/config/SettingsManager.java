package it.giumbociccio.enigma.config;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import it.giumbociccio.enigma.logic.Rotor;
import it.giumbociccio.enigma.logic.Rotors;
import it.giumbociccio.enigma.model.EnigmaSettings;

public class SettingsManager {
	private String settingsPath = "settings/settings.json";
	public static Gson gson = new Gson();

	public SettingsManager(String settingsPath) {
		this.settingsPath = settingsPath;
	}

	public SettingsManager() {
	}

	public EnigmaSettings loadSettings() {
		try (Reader jsonSettings = new FileReader(this.settingsPath);
				Reader jsonOriginalSettings = new FileReader("settings/originalSettings.json")) {

			// convert the JSON data to a Java object 'EnigmaSettings'
			EnigmaSettings settings = gson.fromJson(jsonSettings, EnigmaSettings.class);

			// QUI CONVERTI LE STRINGHE IN HASHMAP, METTI BENE I ROTORI (connections) ETC.

			//rotori
			List<String> rotorsOrder = settings.getRotorsOrder();

			Type listType = new TypeToken<Map<String, Object>>() {
			}.getType();
			Map<String, Object> originalSettings = gson.fromJson(jsonOriginalSettings, listType);
			List<Rotor> rotori = getRotorsSettings(originalSettings, rotorsOrder);
			
			//reflector
			Map<String, Object> reflectors = (Map<String, Object>) originalSettings.get("reflectors");
			String reflector = (String) reflectors.get(settings.getReflectorType());
			
			//Rotors
			Rotors rotors = new Rotors(rotori, reflector);
			settings.setRotors(rotors);

			return settings;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void saveSettings(EnigmaSettings currentSettings) {
		try (Writer writer = new FileWriter(this.settingsPath)) {

			// Convert the Java object `currentSettings` into a JSON data and write to a
			// file
			gson.toJson(currentSettings, writer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private List<Rotor> getRotorsSettings(Map<String, Object> originalSettings, List<String> rotorsOrder) {
	    List<Rotor> toReturn = new ArrayList<>();

	    // Recupera la mappa dei rotori dal JSON
	    Map<String, Object> rotors = (Map<String, Object>) originalSettings.get("rotors");

	    for (String rotorName : rotorsOrder) {
	        // Gson usa LinkedTreeMap per rappresentare oggetti JSON annidati
	        LinkedTreeMap<String, String> rotorData = (LinkedTreeMap<String, String>) rotors.get(rotorName);

	        // Recupera i valori richiesti
	        String connections = rotorData.get("connections");
	        char notchPin = rotorData.get("notchPin").charAt(0);

	        // Crea il rotore
	        Rotor rotor = new Rotor(connections, notchPin, 'A', 'A');
	        toReturn.add(rotor);
	    }

	    return toReturn;
	}

}
