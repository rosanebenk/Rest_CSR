public class Voyageur extends Thread{

    private int billet;
    public EspaceVente espaceVente;
    public Voyageur(int billet, EspaceVente espacevente){
        this.billet = billet;
        this.espaceVente = espacevente;
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

    }

    public void run(){
        //Appel des fonctions
        // acheter billet
        // monter train
    }
}
