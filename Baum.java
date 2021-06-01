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
    public Baum(Auto pAuto)
    {
       //Erzeugt Baum mit zufälligen Attributen und macht ihn mit dem Auto bekannt
       kenntAuto = pAuto;
       stammR = ThreadLocalRandom.current().nextInt(100, 125 + 1);
       stamm = new GLZylinder(ThreadLocalRandom.current().nextInt(-10000, 10000 + 1),50,ThreadLocalRandom.current().nextInt(-35000, -1000 + 1),stammR,ThreadLocalRandom.current().nextInt(600, 800 + 1));
       stamm.drehe(90,0,0);
       stamm.setzeTextur("stamm.jpg");
       krone = new GLKugel(stamm.gibX(),600,stamm.gibZ(),ThreadLocalRandom.current().nextInt(275, 425 + 1));
       krone.setzeTextur("blaetter.jpg");
    }
    public void kollision() {
        //Prüft die Kollision zwischen dem Auto und dem Baum sowie ob das Auto den Spielbereich verlassen hat
        double lDist = Math.sqrt(
                Math.pow(stamm.gibX()-kenntAuto.karosserie.gibX(),2)+
                Math.pow(stamm.gibY()-kenntAuto.karosserie.gibY(),2)+
                Math.pow(stamm.gibZ()-kenntAuto.karosserie.gibZ(),2));        
        if (lDist<150+stammR || kenntAuto.karosserie.gibX() < -10500 || kenntAuto.karosserie.gibX() > 10500 || kenntAuto.karosserie.gibZ() > 2000){
            kenntAuto.crash(); }               
    }
    
}