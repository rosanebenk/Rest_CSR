package V1;


public class Voyageur_V1 extends Thread{

    private int id;
    private String status = "A - en route vers la gare";
    //Retourne le nombre de billets du voyageur
    public int billet;
    public EspaceVente_V1 espaceVenteV1;
    public EspaceQuai_V1 espaceQuaiV1;

    /**
     * Constructeur
     * @param espacevente
     * @param espaceQuaiV1
     */
    public Voyageur_V1(EspaceVente_V1 espacevente, EspaceQuai_V1 espaceQuaiV1){
        this.billet = 0;
        this.espaceVenteV1 = espacevente;
        this.espaceQuaiV1 = espaceQuaiV1;
    }

    /**
     * Permet à un voyageur d'acheter un billet
     */
    public void acheterBillet(){
        //L'espace vente vend un billet au voyageur
        espaceVenteV1.vendre();
        status = "B - muni d'un billet";
        //Son nombre de billet augmente
        billet++;
        System.out.println(Thread.currentThread().getName() +"Le V2.internals.Voyageur a acheté un billet , nb billet = "+ billet);
        System.out.println("Statut du voyageur" + status);
    }

    /**
     * Permet au voyageur de monter dans un train
     */
    public synchronized void monterTrain(){
        //Si son nombre de billet est suffisant
        //le nombre de billet ne pourra jamais être supérieur à 1 car la fonction acheterBillet() dans le run() n'est appelée qu'une fois
        if(billet == 1){
            //Le voyageur "monte" dans l'espace quai, ce qui lui permet de monter dans un train s'il y en a un de disponible
            this.espaceQuaiV1.monter();
            status = "C - monté dans un train";
            //On retire le billet du voyageur
            this.billet--;
            //On libère un billet à l'espace quai
            this.espaceVenteV1.libererbillet();
            System.out.println("Le V2.internals.Voyageur monte dans le train, ses billets :" +billet);
            System.out.println("Statut du voyageur" + status);
        }
    }

    /**
     * Run de la classe
     */
    public void run(){
        System.out.println("Statut du voyageur" + status);
        //Le voyageur achète un billet
        this.acheterBillet();
        //Le voyageur va dans l'espace quai pour monter dans un train
        this.monterTrain();
    }
}
