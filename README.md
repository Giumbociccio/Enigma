# Emulatore Enigma in Java

Un emulatore funzionante della celebre macchina cifrante tedesca _Enigma_, scritto in Java. <br />
Il progetto riproduce fedelmente il comportamento meccanico ed elettrico dei rotori, del riflettore e del plugboard.
<br />
Ideale per scopi didattici, storici o semplicemente per curiosi della crittografia classica.


![Macchina Enigma](immagini/enigma.jpg)

![Console](immagini/console.jpg)

## Funzionalità
- Configurazione dei rotori e del riflettore
- Possibilità di impostare il plugboard (scambi di lettere)
- Inserimento e cifratura di messaggi
- Decifratura automatica se la configurazione è nota
- Interfaccia testuale
- Foto Enigma / Screenshot console


## Come eseguire

Clona il repository:
   ```bash
   git clone https://github.com/Giumbociccio/Enigma.git
   cd Enigma
```
Compila:
```bash
javac Main.java
```
Esegui:

```bash
java Main
```
⚠️ Richiede Java 8+ installato nel sistema.

## Esempio

Configurazione:
- Rotori: 1, 2, 3
- Riflettore: B
- Plugboard: AM, FT

Input:
``HELLO WORLD``  
Output:
``XQBTL RZJGD``

## Architettura

- `EnigmaMachine.java`: classe principale che combina i componenti
- `Rotor.java`: implementa il comportamento dei rotori
- `Reflector.java`: gestisce il riflettore
- `Plugboard.java`: mappa gli scambi di lettere

## Tecnologie

- Java 17
- Gson (per gestire i file Json)
- JUnit 5 (per i test)

## Test

Per eseguire i test:

```bash
javac -cp .:junit-platform-console-standalone-1.9.0.jar *.java
java -jar junit-platform-console-standalone-1.9.0.jar --class-path . --scan-classpath
```
---

## Contribuire

Le pull request sono benvenute! Apri un'issue prima per proporre modifiche.

## Licenza

Distribuito sotto licenza MIT. Vedi il file [LICENSE](LICENSE) per i dettagli.

## Contatti

Realizzato da [Giumbociccio] – [giumbociccio.co@gmail.com]

---

📚 Documentazione
Per la storia della macchina Enigma, il funzionamento tecnico dell’emulatore e guide dettagliate all’utilizzo:

👉 Consulta la Wiki del progetto

🤝 Contribuire
Pull request e suggerimenti sono benvenuti!
Se vuoi contribuire, consulta la guida per i collaboratori.
