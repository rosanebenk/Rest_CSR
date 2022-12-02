package internals;

import internals.EspaceQuai;

public class Train extends Thread{
    private static int NB_PLACE_LIBRE = 50;
    private static int ARRET_TRAIN = 500;
    private static int VITESSE_TRAIN = 250;

    public boolean arreter;
    public int voyageurs;
    public EspaceQuai espaceQuai;
    public int numVoie;

    public Train(EspaceQuai espaceQuai){
        this.voyageurs = 0;
        this.espaceQuai = espaceQuai;
    }

    public synchronized void arriver(){
//        while (!espaceQuai.voieLibre()){
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        espaceQuai.affecterVoie(this);
//        try {
//            sleep(10000/VITESSE_TRAIN);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        arreter = true;
//        notifyAll();
//        System.out.println(Thread.currentThread().getName() + "Un train est arrivé en gare , nb place prises " +voyageurs);
        this.numVoie=espaceQuai.affecterVoie(this);
        try {
            sleep(10000/VITESSE_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        arreter = true;
        System.out.println(Thread.currentThread().getName() + " Un train est arrivé en gare , nbplace prises " +voyageurs);

    }

    public synchronized void depart(){
//        System.out.println("debut de depart");
//        try {
//            sleep(ARRET_TRAIN);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        espaceQuai.libererVoie(this);
//        arreter = false;
//        notifyAll();
//        System.out.println(Thread.currentThread().getName() +" Un train a quitté la gare , nbvoyageur : "+ (NB_PLACE_LIBRE - voyageurs));
//    }
        System.out.println("debut de depart");
        espaceQuai.libererVoie(this.numVoie);
        arreter = false;
        System.out.println(Thread.currentThread().getName() +" Un train a quitté la gare , nbvoyageur : "+ voyageurs);
    }

    public boolean estPlein(){
        return this.voyageurs == NB_PLACE_LIBRE;
    }

    public void voyageurMonte(){
        System.out.println(currentThread().getName() + " voyageur monte dans train " + this.getName());
        voyageurs ++;
    }

    public void run(){
        System.out.println("train à l'approche");
        this.arriver();
        System.out.println("le train est arrivé");
        try {
            sleep(ARRET_TRAIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.depart();
        System.out.println("train est parti");

    }
}
