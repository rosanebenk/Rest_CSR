import java.util.ArrayList;

public class EspaceQuai {
    public final int VOIES = 2 ;
    //public int nbtrain;
    public int nbvoielibre;
    public ArrayList<Train> trains = new ArrayList<>();
    public EspaceQuai() {
       // this.nbtrain = 0;
        this.nbvoielibre = VOIES;
    }
    public synchronized void monter(){
//        for (Train train : trains){
//            while (train.arreter || train.estPlein()){
//                try {
//                    this.wait();
//                }catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//            train.voyageurMonte();
//            System.out.println( Thread.currentThread().getName() +" Place libre train " + train.nbPlaceLibre);
//            notifyAll();
//        }
        while(true){
            for (Train train : trains){
                if (train.arreter && !train.estPlein()){
                    train.voyageurMonte();
                    System.out.println( Thread.currentThread().getName() +" Place libre train " + train.voyageurs);
                }
            }
        }

  // for train if place libre >0 supprimeplace et return place restant

    }
    public synchronized boolean voieLibre(){
        System.out.println(Thread.currentThread().getName() + " verification de voie, nb voie libre :" +nbvoielibre);
        if (0<nbvoielibre  && nbvoielibre<= VOIES) return true;
        else return false;
    }
    public synchronized void affecterVoie(Train t){
        while (nbvoielibre == 0){
            try {
                this.wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        nbvoielibre--;
        trains.add(t);
        System.out.println(Thread.currentThread().getName() + " affectation , nb voie libre :" +nbvoielibre  );
    }
    public synchronized void libererVoie(Train t){
        trains.remove(t);
        nbvoielibre++;
        //nbtrain--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " liberer voie  , nb voie libre :" +nbvoielibre  );
    }


}
