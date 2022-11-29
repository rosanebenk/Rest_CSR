import java.util.ArrayList;

public class EspaceQuai {
    public final int VOIES = 2 ;
    public int nbtrain;
    public ArrayList<Train> trains;

    public EspaceQuai() {
        this.nbtrain = 0;
    }
    public void monter(){
        for (Train train : trains){
            while (!train.arreter || train.estPlein()){
                try {
                    this.wait();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            train.voyaageurMonte();
            notifyAll();
        }

  // for train if place libre >0 supprimeplace et return place restant

    }
    public synchronized boolean voieLibre(){
        if (nbtrain == VOIES) return false;
        else return true;
    }
}
