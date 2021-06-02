import GLOOP.*;
/**Diese Klasse baut alle Teile der Szene zusammen und 
erstellt die Schleife zur Tastaturabfrage zur Steuerung und Kollisionsabfrage
@author Luis Abeler
@version V1 **/
public class Autospiel {
    GLKamera kamera;
    GLLicht licht;
    GLBoden boden;
    GLHimmel himmel;
    Ziellinie ziel;
    Baum baum1, baum2, baum3, baum4, baum5, baum6, baum7, baum8, baum9, 
    baum10, baum11, baum12, baum13, baum14, baum15, baum16;
    Auto auto;
    GLTastatur tastatur;
    double geschwindigkeit;
    boolean tps;
    Baum[] einBaum;
    public Autospiel(int baeume) {
        //Grundlegende Technik und Erstellung des Autos sowie Ziellinie
        ziel = new Ziellinie();
        kamera = new GLKamera();
        licht = new GLLicht();
        auto = new Auto(0,132,1000);
        boden = new GLBoden("waldboden.jpg");
        himmel = new GLHimmel("himmel.jpg");
        tastatur = new GLTastatur();
        geschwindigkeit = 0.2;
        //Erstellt baeume Bäume aus der Baum Klasse mit den jeweiligen Zufallsattributen
        einBaum = new Baum[baeume];
        for (int i = 0; i<baeume ; i++) {
            einBaum[i] = new Baum(auto);
        }
        //Tutorialtafel und Countdown
        GLTafel tafel = new GLTafel(0,1300,0,100,25);
        GLTafel tafel2 = new GLTafel(0,1150,0,35,40);
        GLTafel tafel3 = new GLTafel(0,1200,0,50,25);
        GLTafel tafel4 = new GLTafel(0,1250,0,50,25);
        tafel.setzeText("Fahren = Pfeiltasten, Perspektive = T, Geschwindigkeit = 1;2;3;4",25);
        tafel3.setzeText("Verlasse nicht den Spielbereich, sonst verlierst du...",25);
        tafel4.setzeText("Schaffe es bis zur Ziellinie!",25);
        tafel4.setzeFarbe(0,255,0);
        tafel3.setzeFarbe(255,0,0);
        tafel.setzeAutodrehung(true);
        tafel2.setzeAutodrehung(true);
        tafel3.setzeAutodrehung(true);
        tafel4.setzeAutodrehung(true);
        tafel2.setzeTextfarbe(0,255,0);
        kamera.setzePosition(0,1200,500);
        kamera.setzeBlickpunkt(tafel3.gibPosition());
        tafel2.setzeText("Enter drücken um zu starten",40);
        //Wartet auf Enter, um zu starten
        while (!tastatur.enter()) {
            Sys.warte(10); }
        //Countdown
        for (int i = 5; i>0; i = i-1) {
            tafel2.setzeText("Start in " + i,40);
            Sys.warte(1000);
        }
        tafel.loesche();
        tafel2.loesche();
        tafel3.loesche();
        tafel4.loesche();
        //Ausgangsposition
        kamera.setzePosition(200,1000,1500);
        kamera.setzeBlickpunkt(auto.karosserie.gibPosition());    
    }

    /** In dieser Schleife wird die Steuerung per Tastatur abgefragt, die Perspektive geändert und die Kollision abgefragt**/
    public void fuehreAus(int baeume,boolean tps) {
        while (!tastatur.esc()) { 
            //Ändert den Boolean für die third-person-perspektive
            if (tastatur.istGedrueckt('t')) {
                tps = !tps;
                Sys.warte(150); }
            //Steuerung per Pfeiltasten    
            if (tastatur.oben()) {
                auto.fahren(10*geschwindigkeit); 
                //Kollisionsabfrage aller 200 Bäume im Array
                for (int i = 0;i < baeume;i++) {
                    einBaum[i].kollision(); }
                //Abfrage der Perspektive    
                if (tps == true) {
                    kamera.setzePosition(auto.karosserie.gibX(),auto.karosserie.gibY()+500,auto.karosserie.gibZ()+2000);
                    kamera.setzeBlickpunkt(auto.karosserie.gibPosition());  }
                Sys.warte(1); }
            //Steuerung per Pfeiltasten     
            if (tastatur.unten()) {
                auto.fahren(-10*geschwindigkeit);
                //Kollisionsabfrage aller 200 Bäume im Array
                for (int i = 0;i < baeume;i++) {
                    einBaum[i].kollision(); }
                //Abfrage der Perspektive    
                if (tps == true) {
                    kamera.setzePosition(auto.karosserie.gibX(),auto.karosserie.gibY()+500,auto.karosserie.gibZ()+2000);
                    kamera.setzeBlickpunkt(auto.karosserie.gibPosition());   }                                     
                Sys.warte(1); }
            //Steuerung per Pfeiltasten     
            if (tastatur.links()) {
                if (tastatur.oben() || tastatur.unten()) {
                    auto.lenken(-10*geschwindigkeit*2/3); }
                //Ausrichtung der Reifen    
                auto.rad3.setzeDrehung(0,-60,0);
                auto.rad4.setzeDrehung(0,-60,0);                                     
                Sys.warte(1); }
            //Steuerung per Pfeiltasten     
            if (tastatur.rechts()) {
                if (tastatur.oben() || tastatur.unten()) {
                    auto.lenken(10*geschwindigkeit*2/3); }
                //Ausrichtung der Reifen
                auto.rad3.setzeDrehung(0,60,0);
                auto.rad4.setzeDrehung(0,60,0);                                                  
                Sys.warte(1); }
            //Änderung der Geschwindigkeit    
            if (tastatur.istGedrueckt('1')) {
                geschwindigkeit = 0.3;
                Sys.warte(1); }  
            if (tastatur.istGedrueckt('2')) {
                geschwindigkeit = 0.9;
                Sys.warte(1); }
            if (tastatur.istGedrueckt('3')) {
                geschwindigkeit = 1.4;
                Sys.warte(1); }
            if (tastatur.istGedrueckt('4')) {
                geschwindigkeit = 2;
                Sys.warte(1); }
            //Erneute Abfrage der Perspektive, dadurch flüssigerer Abflauf     
            if (tps == false) {
                kamera.setzePosition(auto.karosserie.gibX(),auto.karosserie.gibY() + 6000, auto.karosserie.gibZ()+1);
                kamera.setzeBlickpunkt(auto.karosserie.gibPosition()); }
            else { 
                kamera.setzePosition(auto.karosserie.gibX(),auto.karosserie.gibY()+500,auto.karosserie.gibZ()+2000);
                kamera.setzeBlickpunkt(auto.karosserie.gibPosition());  }
            //Sollte weder nach rechts oder links gelenkt werden, wird die Radstellung zurückgesetzt    
            if (!tastatur.rechts() && !tastatur.links()) {
                auto.rad3.setzeDrehung(0,90,0);
                auto.rad4.setzeDrehung(0,90,0); }
            //Sollte die Abfrage nach der Kollision positiv sein, wird das Spiel beendet 
            if (auto.crash == true) {
                Sys.warte(1000);
                break; }
            //Überschreitet das Auto die Ziellinie, ist das Spiel gewonnen
            if (auto.karosserie.gibZ() < -40000) {
                GLTafel sieg = new GLTafel(0,1000,-100,100,30);
                sieg.setzeText("Gewonnen!",30);
                sieg.setzeAutodrehung(true);
                sieg.setzeTextfarbe(0,255,0);  
                break; }
        }
        //Kamera wird auf die Tafel mit dem Spielausgang gerichtet
        kamera.setzeBlickpunkt(0,1000,-100);
        kamera.setzePosition(0,1000,0);
    }
}
