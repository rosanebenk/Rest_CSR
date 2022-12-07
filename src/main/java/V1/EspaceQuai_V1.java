package V1;

public class EspaceQuai_V1 {
    public final int VOIES = 2 ;
    public int nbvoielibre;
    //Tableau de train qui ne contiendra que les trains à quai
    public Train_V1[] trainV1s;

    /**
     * Constructeur
     */
    public EspaceQuai_V1() {
       // this.nbtrain = 0;
        this.nbvoielibre = VOIES;
        trainV1s =new Train_V1[VOIES];
    }

    /**
     * Indique aux trains stationnés sur le quai q'un voyageur monte dans l'un d'entre eux
     */
    public void chercherTrain(){
        while(true){
            //Pour chaque train sur le quai
            for (Train_V1 trainV1 : trainV1s){
                //Si le train n'est pas null
                if(trainV1 !=null){
                    //Si le train est bien arreter et qu'il n'est pas plein
                    if (trainV1.arreter && !trainV1.estPlein()){
                        //Le voyageur monte dans le train
                        trainV1.voyageurMonte();
                        System.out.println( Thread.currentThread().getName() +" voyageurs dans le train " + trainV1.voyageurs);
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
    public synchronized int affecterVoie(Train_V1 t){
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
        for(int i = 0; i< trainV1s.length; i++){
            //si le train est null (il est déjà arrivé et parti de la gare)
            if(trainV1s[i]==null){
                // On ajoute le train au tableau de train (cf définition du tableau ligne 9)
                // Le train t se place à l'index i du tableau de train
                trainV1s[i]=t;
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
            trainV1s[numVoie]=null;
            //On augmente de 1 le nombre de voie disponible
            nbvoielibre++;
            //On notifie si on est en attente pour dire qu'une voie s'est libérée
            notify();
            System.out.println(Thread.currentThread().getName() + " liberer voie  , nb voie libre :" +nbvoielibre  );
        }


}
