public class Gare {
    static final int BILLET_TRAIN = 100;
    static final int NB_TRAIN = 5;
    //static final int ESPACE_VENTE = 2;
    static final int NB_VOYAGEURS = 20;

    private Train[] trains = new Train[NB_TRAIN];
    private Voyageur[] voyageurs = new Voyageur[NB_VOYAGEURS];
    private EspaceVente espace1,espace2;
    private EspaceQuai quai ;
    private Train train1,train2,train3;

    {
        try {
            espace1 = new EspaceVente(BILLET_TRAIN/2);
            espace2 = new EspaceVente(BILLET_TRAIN/2);
            quai = new EspaceQuai();
            train1 = new Train(EspaceQuai);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public Gare(){

    }
}
