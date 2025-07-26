Todo:
- migliorare (finire) il cambio di impostazioni da consoleUI:
  - bisogna creare i rotors (solitamente fatto da settingsManager)
  - aggiungere initialPositions e ringSettings (e relativi validatori)
- quando si settano le nuove impostazioni, stampare (attuali --> .....) chiamando this.enigma.getCurrentSettings():
  - imposta la plugboard (connessioni attuali: AX, GT, LP)
  - imposta i rotori (attuali: '4, 2, 3' - initialPositions 'EHR' - ringSettings 'BAA')