public class Train extends Thread{
    private static int NB_PLACE_LIBRE = 25;
    private static int ARRET_TRAIN = 100;
    private static int VITESSE_TRAIN = 250;

    public boolean voieLibre;
    public int nbPlaceLibre;

    public Train(){
        this.nbPlaceLibre = NB_PLACE_LIBRE;
    }

    public synchronized void arriver(EspaceQuai espaceQuai){
        while (espaceQuai.voieLibre()){
            try {
                sleep(10000/VITESSE_TRAIN);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        System.out.println("Un train est arrivé en gare");
    }

    public synchronized void depart(EspaceQuai espaceQuai){
        try {
            sleep(ARRET_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        espaceQuai.nbtrain --;
        System.out.println("Un train a quitté la gare");
    }

    public void run(){

    }
}
