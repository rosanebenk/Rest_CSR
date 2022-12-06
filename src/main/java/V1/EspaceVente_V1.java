package V1;

public class EspaceVente_V1 extends Thread{
    public int billets;
    private final int BILLET_MAX = 50;

    /**
     * Constructeur
     */
    public EspaceVente_V1() {

            this.billets = BILLET_MAX;

    }

    /**
     * Vend un billet à un voyageur
     */
    public synchronized void  vendre( ){
        //Tant qu'il n'y a pas de billet disponible
        while (billets == 0){
            try{
                //L'espace vente ne vend plus de billet
                this.wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } //Fin de tant que
        //Vend un billet
        billets --;
        System.out.println(Thread.currentThread().getName() + " Billet vendu, restant : " + billets);
        try{
            //On met de l'attente le tant que le voyageur range son ticket et libère la place pour qu'un nouveau voyageur puisse acheter un ticket
            Thread.sleep(50);
        } catch (InterruptedException e){
                e.printStackTrace();
        }
    }

    /**
     * Libère un billet en laissant un billet disponible en plus
     */
    public synchronized void libererbillet(){
        billets++;
        //Notifie pour qu'un voyageur puisse de nouveau acheter un billet
        notifyAll();
    }
}
