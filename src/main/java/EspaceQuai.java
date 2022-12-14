import java.util.ArrayList;

public class EspaceQuai {
    public final int VOIES = 2 ;
    public int nbtrain;
    public int nbvoielibre;
    public ArrayList<Train> trains;

    public EspaceQuai(ArrayList<Train> t) {

        this.nbtrain = t.size();
        this.trains = t;
        this.nbvoielibre=VOIES;
    }
    public synchronized void monter(){
        for (Train train : trains){
            while (!train.arreter || train.estPlein()){
                try {
                    this.wait();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            train.voyageurMonte();
            System.out.println("Place libre train" + train.nbPlaceLibre);
            notifyAll();
        }

  // for train if place libre >0 supprimeplace et return place restant

    }
    public boolean voieLibre(){
        if (nbvoielibre == VOIES) return true;
        else return false;
    }
}
