package it.giumbociccio.enigma.model;

import java.util.*;
import java.io.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import it.giumbociccio.enigma.config.SettingsManager;
import it.giumbociccio.enigma.logic.Rotor;
import it.giumbociccio.enigma.logic.Rotors;

public class EnigmaSettings {

	// vengono riempiti tramite gson
	private List<String> rotorsOrder;
	private String plugboard;
	private String reflectorType;
	private String ringSettings;
	private String initialPositions;

	// vengono riempiti manualmente da SettingsManager
	private Rotors rotors;
		
	public EnigmaSettings() {

	}

//	public Rotors getRotorsOldVersion() throws FileNotFoundException, IOException {
//		List<Rotor> rotors = new ArrayList<>();
//		try (Reader r = new FileReader("settings/rotors.json");
//				Reader reader = new FileReader("settings/reflector.json")) {
//			Type listType = new TypeToken<List<Rotor>>() {
//			}.getType();
//			rotors = SettingsManager.gson.fromJson(r, listType);
//			HashMap<Character, Character> reflector = new HashMap<Character, Character>();
//
//			// Tipo generico per HashMap<String, Object>
//			Type type = new TypeToken<Map<String, Map<String, String>>>() {
//			}.getType();
//
//			// Conversione
//			Map<String, Map<String, String>> allReflectors = SettingsManager.gson.fromJson(reader, type);
//			Map<String, String> rawReflector = allReflectors.get(this.reflectorType);
//
//			// Converte in HashMap<Character, Character>
//			rawReflector.forEach((k, v) -> reflector.put(k.charAt(0), v.charAt(0)));
//
//			Rotors toReturn = new Rotors(rotors, reflector);
//			return toReturn;
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}

	public List<String> getRotorsOrder() {
		return rotorsOrder;
	}

	public void setRotorsOrder(List<String> rotorsOrder) {
		this.rotorsOrder = rotorsOrder;
	}

	public String getPlugboard() {
		return plugboard;
	}

	public void setPlugboard(String plugboard) {
		this.plugboard = plugboard;
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

	public Rotors getRotors() {
		return rotors;
	}

	public void setRotors(Rotors rotors) {
		this.rotors = rotors;
	}

}
