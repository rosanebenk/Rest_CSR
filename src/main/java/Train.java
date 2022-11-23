public class Train extends Thread{
    private static int NB_PLACE_LIBRE = 25;
    private static int ARRET_TRAIN = 100;
    private static int VITESSE_TRAIN = 250;

    public boolean voieLibre;
    public int nbPlaceLibre;

    public Train(int nbPlaceLibre){
        this.nbPlaceLibre = NB_PLACE_LIBRE;
    }

    public void arriver(EspaceQuai espaceQuai){
        while (espaceQuai.voieLibre()){
            try {
                sleep(10000/VITESSE_TRAIN);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void depart(EspaceQuai espaceQuai){
        espaceQuai.nbtrain --;
    }

    public void run(){

    }
}
