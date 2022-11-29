public class Voyageur extends Thread{

    private int billet;
    public EspaceVente espaceVente;
    public EspaceQuai espaceQuai;
    public Voyageur(EspaceVente espacevente, EspaceQuai espaceQuai){
        this.billet = 0;
        this.espaceVente = espacevente;
        this.espaceQuai = espaceQuai;
    }

    public void acheterBillet(){
        while(espaceVente.billets == 0){
            try{
                this.wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        billet++;
        espaceVente.vendre();
        notifyAll();
        System.out.println("Le Voyageur a acheté un billet");
    }

    public void monterTrain(){
        if(billet == 1){
            this.espaceQuai.monter();
            System.out.println("Le Voyageur monte dans le train");
        }
    }

    public void run(){
        //Appel des fonctions
        // acheter billet
        this.acheterBillet();
        // monter train
        this.monterTrain();
    }
}
