package it.giumbociccio.enigma.model;

import java.util.*;
import java.io.*;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.giumbociccio.enigma.components.Rotor;
import it.giumbociccio.enigma.components.Rotors;
import it.giumbociccio.enigma.config.SettingsManager;

public class EnigmaSettings {

	private List<String> rotorsOrder;
	private HashMap<Character, Character> plugboard;
	private String reflectorType;
	private String ringSettings;
	private String initialPositions;

	public EnigmaSettings() {

	}

	public Rotors getRotors() throws FileNotFoundException, IOException {
		List<Rotor> rotors = new ArrayList<>();
		try (Reader r = new FileReader("settings/rotors")) {
			HashMap<Character, Character> reflector = new HashMap<Character, Character>();
			try (Reader reader = new FileReader("settings/reflector.json")) {

				// Tipo generico per HashMap<String, Object>
				Type type = new TypeToken<Map<String, Map<String, String>>>() {
				}.getType();

				// Conversione
				Map<String, Map<String, String>> allReflectors = SettingsManager.gson.fromJson(reader, type);
				Map<String, String> rawReflector = allReflectors.get(this.reflectorType);

				// Converte in HashMap<Character, Character>
				rawReflector.forEach((k, v) -> reflector.put(k.charAt(0), v.charAt(0)));

				Rotors toReturn = new Rotors(rotors, reflector);
				return toReturn;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public List<String> getRotorsOrder() {
		return rotorsOrder;
	}

	public void setRotorsOrder(List<String> rotorsOrder) {
		this.rotorsOrder = rotorsOrder;
	}

	public HashMap<Character, Character> getPlugboard() {
		return plugboard;
	}

	public void setPlugboard(HashMap<Character, Character> plugboardSettings) {
		this.plugboard = plugboardSettings;
	}

	public String getReflectorType() {
		return reflectorType;
	}

	public void setReflectorType(String reflectorType) {
		this.reflectorType = reflectorType;
	}

	public String getRingSettings() {
		return ringSettings;
	}

	public void setRingSettings(String ringSettings) {
		this.ringSettings = ringSettings;
	}

	public String getInitialPositions() {
		return initialPositions;
	}

	public void setInitialPositions(String initialPositions) {
		this.initialPositions = initialPositions;
	}

}
