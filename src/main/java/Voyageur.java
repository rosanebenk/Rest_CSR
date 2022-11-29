public class Voyageur extends Thread{

    private int billet;
    public EspaceVente espaceVente;
    public EspaceQuai espaceQuai;
    public Voyageur(int billet, EspaceVente espacevente, EspaceQuai espaceQuai){
        this.billet = billet;
        this.espaceVente = espacevente;
        this.espaceQuai = espaceQuai;
    }

    public void AcheterBillet(){
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
        this.espaceQuai.monter();
        System.out.println("Le Voyageur monte dans le train");
    }

    public void run(){
        //Appel des fonctions
        // acheter billet
        // monter train
    }
}
