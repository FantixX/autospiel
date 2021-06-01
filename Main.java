import GLOOP.*;
/** Main Klasse des Projekts. Hier wird die zusammengesetzte Szene der Autospielklasse erstellt und die fuehreAus Methode (im Prinzip das Spiel) mit dem Attribut True (Perspektive) gestartet. 
Außerdem wird die Zeit gestoppt.
@author Luis Abeler 
@version 1.0 
 **/
public class Main {
    Autospiel spiel;
    Stoppuhr timer;
    public Main(int anzahlBaume) {
        timer = new Stoppuhr();
        spiel = new Autospiel(anzahlBaume);
        timer.start();
        spiel.fuehreAus(anzahlBaume,true);
        timer.stopp();
        timer.zeit();  
        Sys.warte(5000);
        Sys.beenden();
    }
}