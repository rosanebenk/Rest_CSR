import java.util.ArrayList;

public class Gare {
    static final int BILLET_TRAIN = 100;
    static final int NB_TRAIN = 5;
    static final int NB_VOYAGEURS = 20;
    private ArrayList<Train> trains = new ArrayList<Train>();
    private ArrayList<Voyageur> voyageurs = new ArrayList<Voyageur>();
    private EspaceVente espace1;
    private EspaceQuai quai ;

    public Gare(){
        try {
            espace1 = new EspaceVente();
            espace1.start();

            quai = new EspaceQuai();

            for (int i = 0 ;i<NB_TRAIN; i++){
                trains.add(new Train(quai));
            }
            for (int i = 0 ;i<NB_VOYAGEURS; i++){
                   voyageurs.add(new Voyageur(espace1,quai));
            }

            for (Voyageur v : voyageurs){
                v.start();
            }
            for (Train t: trains){
                t.start();
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public  static void main (String[] args){
        new Gare();
    }
}
