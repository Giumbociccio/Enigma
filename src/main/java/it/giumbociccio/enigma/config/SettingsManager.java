package it.giumbociccio.enigma.config;

import java.io.*;
import com.google.gson.*;

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
		try (Reader reader = new FileReader(this.settingsPath);
				Reader reader2 = new FileReader("settings/originalSettings.json")) {

			// convert the JSON data to a Java object 'EnigmaSettings'
			EnigmaSettings settings = gson.fromJson(reader, EnigmaSettings.class);

			// QUI CONVERTI LE STRINGHE IN HASHMAP, METTI BENE I ROTORI (connections) ETC.
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

}
