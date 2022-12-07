package V2.internals;

public class EspaceQuai {
    public final int VOIES = 2 ;
    public int nbvoielibre;
    //Tableau de train qui ne contiendra que les trains à quai
    public Train[] trains;

    /**
     * Constructeur
     */
    public EspaceQuai() {
       // this.nbtrain = 0;
        this.nbvoielibre = VOIES;
        trains=new Train[VOIES];
    }

    /**
     * Indique aux trains stationnés sur le quai q'un voyageur monte dans l'un d'entre eux
     */
    public void chercherTrain(){
        while(true){
            //Pour chaque train sur le quai
            for (Train train : trains){
                //Si le train n'est pas null
                if(train!=null){
                    //Si le train est bien arreter et qu'il n'est pas plein
                    if (train.arreter && !train.estPlein()){
                        //Le voyageur monte dans le train
                        train.voyageurMonte();
                        System.out.println( Thread.currentThread().getName() +" voyageurs dans le train " + train.voyageurs);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Indique si une voie du quai est libre
     * @return boolean (vrai si voie libre)
     */
    public synchronized boolean voieLibre(){
        System.out.println(Thread.currentThread().getName() + " verification de voie, nb voie libre :" +nbvoielibre);
        if (0<nbvoielibre  && nbvoielibre<= VOIES) return true;
        else return false;
    }

    /**
     * Affecte une voie à un train
     * @param t un train
     * @return Int, le numéro de voie du train
     */
    public synchronized int affecterVoie(Train t){
        //Tant qu'il n'y a pas de voie libre
        while (nbvoielibre == 0){
            try {
                //On se met en attente
                System.out.println(Thread.currentThread().getName() + " j'attends ...");
                this.wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } // Fin de tant que
        int numVoie = -1;
        //On enlève une voie au nombre de voies libres
        nbvoielibre--;
        //Pour chaque train de la liste
        for(int i = 0; i<trains.length;i++){
            //si le train est null (il est déjà arrivé et parti de la gare)
            if(trains[i]==null){
                // On ajoute le train au tableau de train (cf définition du tableau ligne 9)
                // Le train t se place à l'index i du tableau de train
                trains[i]=t;
                //Le train prendra alors le numéro de voie correspondant à son index dans le tableau
                numVoie=i;
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " affectation , nb voie libre :" +nbvoielibre  );
        //On retourne le numéro de voie que le train prendra
        return numVoie;
    }

    /**
     * Libère une voie du quai lorsqu'un train part
     * @param numVoie, le numéro de voie à libérer
     */
        public synchronized void libererVoie(int numVoie){
            //On "supprime" le train du tableau de train (libérant ainsi son index)
            trains[numVoie]=null;
            //On augmente de 1 le nombre de voie disponible
            nbvoielibre++;
            //On notifie si on est en attente pour dire qu'une voie s'est libérée
            notify();
            System.out.println(Thread.currentThread().getName() + " liberer voie  , nb voie libre :" +nbvoielibre  );
        }


}
