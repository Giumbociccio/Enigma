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
	public static Gson gson = new GsonBuilder()
		    .excludeFieldsWithoutExposeAnnotation()
		    .create();

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

			// QUI CONVERTI LE STRINGHE IN MAP, METTI BENE I ROTORI (connections) ETC.

			//rotori
			List<String> rotorsOrder = settings.getRotorsOrder();

			Type listType = new TypeToken<Map<String, Object>>() {
			}.getType();
			Map<String, Object> originalSettings = gson.fromJson(jsonOriginalSettings, listType);
			List<Rotor> rotori = getRotorsSettings(
				    originalSettings,
				    rotorsOrder,
				    settings.getInitialPositions(),
				    settings.getRingSettings()
				);
			
			//reflector
			@SuppressWarnings("unchecked")
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
			System.out.println("Impostazioni salvate con successo");
			
		} catch (IOException e) {
			System.err.println("Errore nel salvataggio: " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	private List<Rotor> getRotorsSettings(Map<String, Object> originalSettings,
            List<String> rotorsOrder,
            String initialPositions,
            String ringSettings){
		List<Rotor> toReturn = new ArrayList<>();
		Map<String, Object> rotors = (Map<String, Object>) originalSettings.get("rotors");

		for (int i = 0; i < rotorsOrder.size(); i++) {
		    String rotorName = rotorsOrder.get(i);
		    LinkedTreeMap<String, String> rotorData = (LinkedTreeMap<String, String>) rotors.get(rotorName);

		    String connections = rotorData.get("connections");
		    char notchPin = rotorData.get("notchPin").charAt(0);

		    char currentPosition = initialPositions.charAt(i); // es. 'A'
		    char ringSetting = ringSettings.charAt(i);         // es. 'A'

		    Rotor rotor = new Rotor(connections, notchPin, currentPosition, ringSetting);
		    toReturn.add(rotor);
		}
		return toReturn;
	}

}
