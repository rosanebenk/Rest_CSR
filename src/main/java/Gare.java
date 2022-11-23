public class Gare {
    static final int BILLET_TRAIN = 100;
    static final int NB_TRAIN = 5;
    //static final int ESPACE_VENTE = 2;
    static final int NB_VOYAGEURS = 20;

    private Train[] trains = new Train[NB_TRAIN];
    private Voyageur[] voyageurs = new Voyageur[NB_VOYAGEURS];
    private EspaceVente espace1 = new EspaceVente(BILLET_TRAIN/2);
    private EspaceVente espace2 = new EspaceVente(BILLET_TRAIN/2);


    public Gare(){

    }
}
