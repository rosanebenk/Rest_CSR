import java.util.ArrayList;

public class EspaceQuai {
    public final int VOIES = 2 ;
    //public int nbtrain;
    public int nbvoielibre;
    //public ArrayList<Train> trains = new ArrayList<>();
    public Train[] trains;
    public EspaceQuai() {
       // this.nbtrain = 0;
        this.nbvoielibre = VOIES;
        trains=new Train[VOIES];
    }
    public void monter(){
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
//        while(true){
//            for (Train train : trains){
//                if (train.arreter && !train.estPlein()){
//                    train.voyageurMonte();
//                    System.out.println( Thread.currentThread().getName() +" voyageurs dans le train " + train.voyageurs);
//                }
//            }
//        }
        while(true){
            for (Train train : trains){
                if(train!=null){
                    if (train.arreter && !train.estPlein()){
                        train.voyageurMonte();
                        System.out.println( Thread.currentThread().getName() +" voyageurs dans le train " + train.voyageurs);
                        return;
                    }
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
    public synchronized int affecterVoie(Train t){
        while (nbvoielibre == 0){
            try {
                System.out.println(Thread.currentThread().getName() + " j'attends ...");
                this.wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        int numVoie = -1;
        nbvoielibre--;
        for(int i = 0; i<trains.length;i++){
            if(trains[i]==null){
                trains[i]=t;
                numVoie=i;
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " affectation , nb voie libre :" +nbvoielibre  );
        return numVoie;
    }
//    public synchronized void libererVoie(Train t){
//        trains.remove(t);
//        nbvoielibre++;
//        //nbtrain--;
//        notifyAll();
//        System.out.println(Thread.currentThread().getName() + " liberer voie  , nb voie libre :" +nbvoielibre  );
//    }
        public synchronized void libererVoie(int numVoie){
            trains[numVoie]=null;
            nbvoielibre++;
            //nbtrain--;
            notify();
            System.out.println(Thread.currentThread().getName() + " liberer voie  , nb voie libre :" +nbvoielibre  );
        }


}
