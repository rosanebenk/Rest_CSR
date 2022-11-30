import static java.lang.Thread.sleep;

public class EspaceVente extends Thread{
    public int billets;
    private final int BILLET_MAX = 50;

//    public EspaceVente(int billets) throws Exception {
//        if (billets <= BILLET_MAX)
//        this.billets = billets;
//        else throw new Exception("Le nombre de billet max est 50");
//    }
    public EspaceVente() {

            this.billets = BILLET_MAX;

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
        System.out.println(Thread.currentThread().getName() + " Billet vendu, restant : " + billets);
        try{
            Thread.sleep(50);
            // imprimer des billets
            //billets = BILLET_MAX - billets;
        } catch (InterruptedException e){
                e.printStackTrace();
        }
    }
    public synchronized void libererbillet(){
        billets++;
        notifyAll();
    }
}
