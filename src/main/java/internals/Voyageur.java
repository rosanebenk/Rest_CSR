package internals;

public class Voyageur extends Thread{

    private int id;
    public int billet;
    public EspaceVente espaceVente;
    public EspaceQuai espaceQuai;
    public Voyageur(EspaceVente espacevente, EspaceQuai espaceQuai){
        this.billet = 0;
        this.espaceVente = espacevente;
        this.espaceQuai = espaceQuai;
    }

    public void acheterBillet(){
//        while(espaceVente.billets == 0){
//            try{
//                this.wait();
//            }catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }
        espaceVente.vendre();
        billet++;
        System.out.println(Thread.currentThread().getName() +"Le internals.Voyageur a acheté un billet , nb billet = "+ billet);
    }

    public synchronized void monterTrain(){
        if(billet == 1){
            this.espaceQuai.monter();
            this.billet--;
            this.espaceVente.libererbillet();
            System.out.println("Le internals.Voyageur monte dans le train, ses billets :" +billet);
        }
    }

    public void run(){
        //Appel des fonctions
        // acheter billet
        this.acheterBillet();
//        try {
//            sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        // monter train
        this.monterTrain();
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
