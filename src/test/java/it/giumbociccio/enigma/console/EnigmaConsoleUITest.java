package it.giumbociccio.enigma.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnigmaConsoleUITest {

	@Test
	void validateConnectionsTest() {
		var console = new EnigmaConsoleUI(null);
		boolean expected = false;
		boolean actual = console.validateConnections("AB, BA");
		assertFalse(actual);
		assertEquals(expected, actual);
	}
	

}
