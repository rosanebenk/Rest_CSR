package V1;

import java.util.ArrayList;

public class Gare_V1 {
    static final int BILLET_TRAIN = 100;
    static final int NB_TRAIN = 10;
    static final int NB_VOYAGEURS = 200;
    private ArrayList<Train_V1> trains = new ArrayList<Train_V1>();
    private ArrayList<Voyageur_V1> voyageurs = new ArrayList<Voyageur_V1>();
    private EspaceVente_V1 espace1;
    private EspaceQuai_V1 quai ;

    public Gare_V1(){
        try {
            //Initialisation des espaces quai et vente
            espace1 = new EspaceVente_V1();
            quai = new EspaceQuai_V1();

            //Initialisation des trains et des voyageurs
            for (int i = 0 ;i<NB_TRAIN; i++){
                trains.add(new Train_V1(quai));
            }
            for (int i = 0 ;i<NB_VOYAGEURS; i++){
                voyageurs.add(new Voyageur_V1(espace1,quai));
            }
            //On execute le run() des classes voyageur et train pour chaque voyageur ou train initialisé
            for (Voyageur_V1 v : voyageurs){
                v.start();
            }
            for (Train_V1 t: trains){
                t.start();
            }
            //Attente que tout les trains soient parti
            for(Train_V1 t : trains){
                t.join();
            }

            System.out.println("la gare est fermée , tous les trains sont partis.");
            System.exit(0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public  static void main (String[] args){
        new Gare_V1();
    }
}