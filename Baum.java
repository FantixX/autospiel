import GLOOP.*;
import java.util.concurrent.ThreadLocalRandom;
    /**Diese Klasse erzeugt einen Baum und 
    gibt dem Benutzer die Möglichkeit, dessen Größe und Position anzupassen
    @author Luis Abeler
    @version V1 **/
public class Baum
{
    GLKegelstumpf stamm;
    GLKugel krone;
    Auto kenntAuto;
    int stammR;
    double stammH;
    double kroneR;
    public Baum(Auto pAuto)
    {
       //Erzeugt Baum mit zufälligen Attributen und macht ihn mit dem Auto bekannt
       kenntAuto = pAuto;
       stammH = ThreadLocalRandom.current().nextInt(900, 1900 + 1);
       stammR = ThreadLocalRandom.current().nextInt(110, 150 + 1);
       stamm = new GLZylinder(ThreadLocalRandom.current().nextInt(-10000, 10000 + 1),0,ThreadLocalRandom.current().nextInt(-35000, -1000 + 1),stammR,stammH);
       stamm.drehe(90,0,0);
       kroneR = ThreadLocalRandom.current().nextInt(440, 600 + 1);
       stamm.setzeTextur("stamm.jpg");
       krone = new GLKugel(stamm.gibX(),stammH/2+kroneR/2,stamm.gibZ(),kroneR);
       krone.setzeTextur("blaetter.jpg");
    }
    public void kollision() {
        //Prüft die Kollision zwischen dem Auto und dem Baum sowie ob das Auto den Spielbereich verlassen hat
        double lDist = Math.sqrt(
                Math.pow(stamm.gibX()-kenntAuto.karosserie.gibX(),2)+
                Math.pow(stamm.gibY()-kenntAuto.karosserie.gibY(),2)+
                Math.pow(stamm.gibZ()-kenntAuto.karosserie.gibZ(),2));        
        if (lDist<175+stammR || kenntAuto.karosserie.gibX() < -10500 || kenntAuto.karosserie.gibX() > 10500 || kenntAuto.karosserie.gibZ() > 2000){
            kenntAuto.crash(); }               
    }
    
}