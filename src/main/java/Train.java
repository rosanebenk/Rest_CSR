public class Train extends Thread{
    private static int NB_PLACE_LIBRE = 25;
    private static int ARRET_TRAIN = 200;
    private static int VITESSE_TRAIN = 250;

    public boolean arreter;
    public int voyageurs;
    public EspaceQuai espaceQuai;

    public Train(EspaceQuai espaceQuai){
        this.voyageurs = 0;
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
        espaceQuai.affecterVoie(this);
        try {
            sleep(10000/VITESSE_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        arreter = true;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "Un train est arrivé en gare , nb place " +voyageurs);
    }

    public synchronized void depart(){
        try {
            sleep(ARRET_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        espaceQuai.libererVoie(this);
        arreter = false;
        notifyAll();
        System.out.println(Thread.currentThread().getName() +" Un train a quitté la gare , nbvoyageur : "+ (NB_PLACE_LIBRE - voyageurs));
    }

    public boolean estPlein(){
        return this.voyageurs == NB_PLACE_LIBRE;
    }

    public void voyageurMonte(){
        voyageurs ++;
    }

    public void run(){
        System.out.println("train à l'approche");
        this.arriver();
        System.out.println("le train est arrivé");
        this.depart();
        System.out.println("train est parti");

    }
}
