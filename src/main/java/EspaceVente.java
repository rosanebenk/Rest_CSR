import static java.lang.Thread.sleep;

public class EspaceVente extends Thread{
    public int billets;
    private final int BILLET_MAX = 50;

    public EspaceVente(int billets) throws Exception {
        if (billets <= BILLET_MAX)
        this.billets = billets;
        else throw new Exception("Le nombre de billet max est 50");
    }
    public synchronized void  vendre( ){
        while (billets == 0){
            try{
                this.wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        billets --;
        notifyAll();
        System.out.println("Billet vendu, restant : " + billets);
        try{
            Thread.sleep(50);
        } catch (InterruptedException e){
                e.printStackTrace();
        }
    }
//    public void imprimer() throws InterruptedException {
//        Thread.sleep(500);
//        billets+= BILLET_MAX - billets;
//    }
//    public void run() {
//        while (true){
//            try {
//                vendre();
//                imprimer();
//            }catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
