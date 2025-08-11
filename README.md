# Emulatore Enigma in Java
Un emulatore funzionante della celebre macchina cifrante tedesca, scritto in Java.


Questo progetto riproduce il comportamento della macchina Enigma, utilizzata durante la Seconda Guerra Mondiale per cifrare messaggi. È stato sviluppato in Java a scopo didattico/storico.


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
   cd enigma-java
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


### 🧪 **Esempi d’uso**
Fornisci esempi di input/output o configurazioni specifiche.


## Esempio

Configurazione:
- Rotori: I - II - III
- Riflettore: B
- Plugboard: A<->M, F<->T

Input:
``HELLO WORLD``  
Output:
``XQBTL RZJGD``


## Architettura

- `Rotor.java`: implementa il comportamento dei rotori
- `Reflector.java`: gestisce il riflettore
- `Plugboard.java`: mappa gli scambi di lettere
- `EnigmaMachine.java`: classe principale che combina i componenti


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

Distribuito sotto licenza MIT. Vedi `LICENSE` per i dettagli.

## Contatti

Realizzato da [Giumbociccio] – [giumbociccio.co@gmail.com]

## Licenza

Distribuito sotto licenza MIT. Vedi il file [LICENSE](LICENSE) per i dettagli.
Fammi sapere se vuoi che ti generi direttamente il contenuto della licenza MIT personalizzato con il tuo nome e l’anno corrente.

---

# Emulatore Enigma in Java

Un emulatore funzionante della macchina cifrante tedesca Enigma, sviluppato in Java. Riproduce fedelmente il comportamento dei rotori, riflettori e plugboard, offrendo una simulazione completa a scopo didattico.

![Macchina Enigma](immagini/enigma.jpg)

---

### ✅ In sintesi:

- `README.md` = **biglietto da visita**
- Wiki = **manuale utente + libro tecnico**
- LICENSE = **tutela legale**

---

# Emulatore Enigma in Java

Un emulatore funzionante della celebre macchina cifrante tedesca **Enigma**, scritto in Java. Il progetto riproduce fedelmente il comportamento meccanico ed elettrico dei rotori, del riflettore e del plugboard. Ideale per scopi didattici, storici o semplicemente per curiosi della crittografia classica.

![Macchina Enigma](immagini/enigma.jpg)

---

📚 Documentazione
Per la storia della macchina Enigma, il funzionamento tecnico dell’emulatore e guide dettagliate all’utilizzo:

👉 Consulta la Wiki del progetto

🤝 Contribuire
Pull request e suggerimenti sono benvenuti!
Se vuoi contribuire, consulta la guida per i collaboratori.

📬 Contatti
Realizzato da [Tuo Nome]
📧 tuo@email.com