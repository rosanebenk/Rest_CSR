public class Train extends Thread{
    private static int NB_PLACE_LIBRE = 25;
    private static int ARRET_TRAIN = 100;
    private static int VITESSE_TRAIN = 250;

    public boolean arreter;
    public int nbPlaceLibre;
    public EspaceQuai espaceQuai;

    public Train(EspaceQuai espaceQuai){
        this.nbPlaceLibre = NB_PLACE_LIBRE;
        this.espaceQuai = espaceQuai;
    }

    public synchronized void arriver(){
        while (!espaceQuai.voieLibre()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            sleep(10000/VITESSE_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        arreter = true;
        espaceQuai.nbvoielibre--;
        notifyAll();
        System.out.println("Un train est arrivé en gare");
    }

    public synchronized void depart(){
        try {
            sleep(ARRET_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        espaceQuai.nbtrain --;
        arreter = false;
        notifyAll();
        System.out.println("Un train a quitté la gare , nbvoyageur : "+ (NB_PLACE_LIBRE-nbPlaceLibre));
    }

    public boolean estPlein(){
        return this.nbPlaceLibre == 0;
    }

    public void voyageurMonte(){
        nbPlaceLibre --;
    }

    public void run(){
        System.out.println("train à l'approche");
        this.arriver();
        System.out.println("le train est arrivé");
        this.depart();

    }
}
