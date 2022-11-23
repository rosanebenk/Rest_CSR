import static java.lang.Thread.sleep;

public class EspaceVente extends Thread{
    private int billets;

    public EspaceVente(int billets) {
        this.billets = billets;
    }
    public void vendre(int n, Voyageur v ){
        billets =- n;
        //v
    }
    public void imprimer(int n) throws InterruptedException {
        Thread.sleep(500);
        billets+= n;
    }
    public void run(){

    }
}
