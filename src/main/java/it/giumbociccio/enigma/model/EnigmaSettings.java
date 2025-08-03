package it.giumbociccio.enigma.model;

import java.util.*;
import com.google.gson.annotations.Expose;
import it.giumbociccio.enigma.logic.Rotors;
import it.giumbociccio.enigma.utils.Utility;

public class EnigmaSettings {

	// vengono riempiti tramite gson
	@Expose
	private String plugboard;
	@Expose
	private List<String> rotorsOrder;
	@Expose
	private String initialPositions;
	@Expose
	private String ringSettings;
	@Expose
	private String reflectorType;

	// vengono riempiti manualmente da SettingsManager
	private Rotors rotors;

	public EnigmaSettings(String plugboard, List<String> rotorsOrder, String initialPositions, String ringSettings,
			String reflector) {
		this.setPlugboard(plugboard);
		this.setRotorsOrder(rotorsOrder);
		this.setInitialPositions(initialPositions);
		this.setRingSettings(ringSettings);
		this.setReflectorType(reflector);
	}

	public List<String> getRotorsOrder() {
		return this.rotorsOrder;
	}

	public void setRotorsOrder(List<String> rotorsOrder) {
		this.rotorsOrder = rotorsOrder;
	}

	public String getPlugboard() {
		return this.plugboard;
	}
	
	//TODO: capire bene la struttura di questa funzione
	public String getPlugboardConnections() {
		String TODO = "capire bene la struttura di questa funzione";
		
		Set<String> pairs = new LinkedHashSet<>();

	    for (int i = 0; i < 26; i++) {
	        char original = Utility.alfabeto[i];
	        char mapped = this.plugboard.charAt(i);

	        if (original != mapped) {
	            String pair = original < mapped ? "" + original + mapped : "" + mapped + original;
	            pairs.add(pair);
	        }
	    }

	    return pairs.isEmpty() ? "Plugboard vuoto" : String.join(", ", pairs);
	}

	public void setPlugboard(String plugboard) {
		this.plugboard = plugboard;
	}

	public String getReflectorType() {
		return this.reflectorType;
	}

	public void setReflectorType(String reflectorType) {
		this.reflectorType = reflectorType;
	}

	public String getRingSettings() {
		return this.ringSettings;
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

	@Override
	public String toString() {
		return "EnigmaSettings [rotorsOrder=" + rotorsOrder + ", plugboard=" + plugboard + ", reflectorType="
				+ reflectorType + ", ringSettings=" + ringSettings + ", initialPositions=" + initialPositions
				+ ", rotors=" + rotors + "]";
	}

}
