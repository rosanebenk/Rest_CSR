public class EspaceQuai {
    public final int VOIES = 2 ;
    public int nbtrain;


    public EspaceQuai() {
        this.nbtrain = 0;
    }
    public void monter(Voyageur v){

    }
    public synchronized boolean voieLibre(){
        if (nbtrain == VOIES) return false;
        else return true;
    }
}
