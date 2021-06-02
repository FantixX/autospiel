import GLOOP.*;
    /**Diese Klasse erzeugt das Auto und 
    gibt ihm die Möglichkeit, zu fahren und zu lenken
    @author Luis Abeler
    @version V1 **/
public class Auto {
    GLQuader karosserie;
    GLZylinder rad1,rad2,rad3,rad4;
    int geschw;
    GLQuader karosserie2;
    GLQuader karosserie4;
    GLQuader karosserie3;
    boolean crash;
    /** Konstruktor der Klasse Auto **/
    public Auto(int posX, int posY, int posZ) {
        //Erzeugt alle Teile des Autos und setzt diese in die richtige Position
        karosserie = new GLQuader(posX, posY, posZ, 500/2,300/2,1200/2);
        karosserie2 = new GLQuader(posX, posY+250/2, posZ+200/2, 500/2,200/2,600/2);
        karosserie3 = new GLQuader(posX, posY+100/2, posZ-210/2,450/2,200/2,400/2);
        karosserie3.drehe(-30,0,0);
        karosserie4 = new GLQuader(posX, posY+200/2, posZ+250,450/2,200/2,30);
        karosserie4.drehe(-20,0,0);
        karosserie.setzeTextur("blau2.jpg");
        karosserie2.setzeTextur("blau2.jpg");
        karosserie3.setzeTextur("chrom.jpg");
        karosserie4.setzeTextur("chrom.jpg"); 
        rad1 = new GLZylinder(posX+260/2, posY-150/2, posZ+400/2, 125/2,100/2);
        rad1.setzeTextur("reifen.jpg");
        rad1.drehe(0,90,0);
        rad2 = new GLZylinder(posX-260/2, posY-150/2, posZ+400/2, 125/2,100/2);
        rad2.drehe(0,90,0);
        rad2.setzeTextur("reifen.jpg");
        rad3 = new GLZylinder(posX+260/2, posY-150/2, posZ-400/2, 125/2,100/2);
        rad3.drehe(0,90,0);
        rad3.setzeTextur("reifen.jpg");
        rad4 = new GLZylinder(posX-260/2, posY-150/2, posZ-400/2, 125/2,100/2);
        rad4.drehe(0,90,0);
        rad4.setzeTextur("reifen.jpg");
    }
    /** Vorwärtsbewegung des Autos**/
    public void fahren(double geschw) {
        //Vorwärtsbewegung und Drehung der Reifen
        karosserie.verschiebe(0,0,-geschw);
        karosserie2.verschiebe(0,0,-geschw);
        karosserie3.verschiebe(0,0,-geschw);
        karosserie4.verschiebe(0,0,-geschw);            
        rad1.verschiebe(0,0,-geschw);
        rad1.drehe(-geschw/1.8,0,0,rad1.gibPosition());
        rad2.verschiebe(0,0,-geschw);
        rad2.drehe(-geschw/1.8,0,0,rad2.gibPosition());
        rad3.verschiebe(0,0,-geschw);
        rad4.verschiebe(0,0,-geschw);     
        Sys.warte(1);
        }
    /** Lenkung des Autos**/    
    public void lenken(double lenken) {
        karosserie.verschiebe(lenken,0,0);
        karosserie2.verschiebe(lenken,0,0);
        karosserie3.verschiebe(lenken,0,0);
        karosserie4.verschiebe(lenken,0,0);            
        rad1.verschiebe(lenken,0,0);
        rad2.verschiebe(lenken,0,0);
        rad3.verschiebe(lenken,0,0);
        rad4.verschiebe(lenken,0,0);
        Sys.warte(1);
        }
    /** Crash des Autos**/    
    public void crash() {
        GLTafel tafel = new GLTafel(0,1000,-100,100,30);
        tafel.setzeText("Game Over",30);
        tafel.setzeAutodrehung(true);
        tafel.setzeTextfarbe(255,0,0);
        crash = true;
    }
}
