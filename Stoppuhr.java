import GLOOP.*;
/** Eine simple Stoppuhr, welche bei beenden den Spiels die benötigte Zeit ausgibt **/
public class Stoppuhr {
    double starttime;
    double endtime;
    double ergebnis;
    public Stoppuhr() {
        double starttime;
        double endtime;
        double ergebnis; }        
    public void start() {
        starttime = System.currentTimeMillis(); }
    public void stopp() {
        endtime = System.currentTimeMillis(); } 
    public void zeit() {
        ergebnis = ((endtime - starttime)/1000);
        System.out.println("Deine Zeit ist: "+ergebnis+"s" ); }
    }
        