package it.giumbociccio.enigma.model;

import java.util.*;
import com.google.gson.annotations.Expose;
import it.giumbociccio.enigma.logic.Rotors;

public class EnigmaSettings {

	// vengono riempiti tramite gson
	@Expose
	private List<String> rotorsOrder;
	@Expose
	private String plugboard;
	@Expose
	private String reflectorType;
	@Expose
	private String ringSettings;
	@Expose
	private String initialPositions;

	// vengono riempiti manualmente da SettingsManager
	private Rotors rotors;

	public EnigmaSettings() {

	}

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

	@Override
	public String toString() {
		return "EnigmaSettings [rotorsOrder=" + rotorsOrder + ", plugboard=" + plugboard + ", reflectorType="
				+ reflectorType + ", ringSettings=" + ringSettings + ", initialPositions=" + initialPositions
				+ ", rotors=" + rotors + "]";
	}

}
