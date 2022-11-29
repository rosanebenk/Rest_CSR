import java.util.ArrayList;

public class Gare {
    static final int BILLET_TRAIN = 100;
    static final int NB_TRAIN = 5;
    //static final int ESPACE_VENTE = 2;
    static final int NB_VOYAGEURS = 20;

    private ArrayList<Train> trains = new ArrayList<Train>();
    private ArrayList<Voyageur> voyageurs = new ArrayList<Voyageur>();
    private EspaceVente espace1,espace2;
    private EspaceQuai quai ;

    public Gare(){
        try {
            espace1 = new EspaceVente(BILLET_TRAIN/2);
            espace1.start();
            espace2 = new EspaceVente(BILLET_TRAIN/2);
            espace2.start();
            quai = new EspaceQuai(trains);
            for (int i = 0 ;i<NB_TRAIN; i++){
                trains.add(new Train(quai));
            }
            for (int i = 0 ;i<NB_VOYAGEURS; i++){
                if ( i % 2==0){
                    voyageurs.add(new Voyageur(espace2,quai));
                }else {
                    voyageurs.add(new Voyageur(espace1,quai));
                }

            }
            for (Train t: trains){
                t.start();
            }
            for (Voyageur v : voyageurs){
                v.start();
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public  static void main (String[] args){
        new Gare();
    }
}
