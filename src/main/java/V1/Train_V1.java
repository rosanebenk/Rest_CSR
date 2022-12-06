package V1;

public class Train_V1 extends Thread{
    private long id;
    private String statut = "A - en route vers la gare";
    private static int NB_PLACE_LIBRE = 50;
    private static int ARRET_TRAIN = 500;
    private static int VITESSE_TRAIN = 250;
    //Boolean pour ssavoir si le train est arreter (true si arreté)
    public boolean arreter;
    //Int qui indique le nombre de voyageurs présent dans le train
    public int voyageurs;
    public EspaceQuai_V1 espaceQuaiV1;
    //Numéro de voie attribué au train
    public int numVoie;

    /**
     * Constructeur
     * @param espaceQuaiV1
     */
    public Train_V1(EspaceQuai_V1 espaceQuaiV1){
        this.voyageurs = 0;
        this.espaceQuaiV1 = espaceQuaiV1;
    }

    /**
     * Fais arriver un train sur une voie de l'espace quai
     */
    public synchronized void arriver(){
        statut = "B - en attente d'une voie libre";
        //On affecte une voie au train
        this.numVoie= espaceQuaiV1.affecterVoie(this);
        try {
            //Le train décélère le temps qu'il arrive en gare
            sleep(10000/VITESSE_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Le train s'arrete à quai
        arreter = true;
        statut = "C - en gare";
        System.out.println(Thread.currentThread().getName() + " Un train est arrivé en gare , nbplace prises " +voyageurs);
        System.out.println("Statut du train " + statut);

    }

    /**
     * Le train part de la gare et libère une voie
     */
    public synchronized void depart(){
        System.out.println("debut de depart");
        //Le train libère la voie qui lui a été attribuée
        espaceQuaiV1.libererVoie(this.numVoie);
        statut = "D - parti";
        //Le train n'est plus arreter
        arreter = false;
        System.out.println("Statut du train " + statut);
        System.out.println(Thread.currentThread().getName() +" Un train a quitté la gare , nbvoyageur : "+ voyageurs);
    }

    /**
     * Permet de savoir si le train est plein et ne peux plus acceuillir de voyageur
     * @return boolean (true si le train est plein et ne peux plus acceuillir de voyageur)
     */
    public boolean estPlein(){
        return this.voyageurs == NB_PLACE_LIBRE;
    }

    /**
     * Augmente le nombre de voyageurs présent dans le train
     */
    public void voyageurMonte(){
        System.out.println(currentThread().getName() + " voyageur monte dans train " + this.getName());
        voyageurs ++;
    }

    /**
     * Run de la classe
     */
    public void run(){
        //System.out.println("train à l'approche");
        System.out.println("Statut du train " + statut);
        //Le train arrive en gare
        this.arriver();

        //Le train reste à quai le temps qui lui a été attribué
        try {
            sleep(ARRET_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Le train part de la gare
        this.depart();
        //System.out.println("train est parti");
        System.out.println("Statut du train " + statut);

    }
}
