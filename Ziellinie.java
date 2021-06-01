import GLOOP.*;
/**
 * Ziellinie für das Autospiel
 * 
 * @author Luis Abeler
 * @version 1.0
 */
public class Ziellinie
{
    GLQuader weg;
    GLQuader strich;    
    /**
     * Konstruktor für Objekte der Klasse Ziellinie
     */
    public Ziellinie()
    {
         weg = new GLQuader(0,0,-40000,1000000,2,500);
         weg.setzeFarbe(255,255,255);
    }

   
}