package V2.main;

import V2.application.gareApplication;
import V2.database.InMemoryDatabase;
import V2.internals.EspaceQuai;
import V2.internals.EspaceVente;
import V2.internals.Train;
import V2.internals.Voyageur;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

import java.util.ArrayList;

public class Gare {
    private static InMemoryDatabase db ;
    static final int BILLET_TRAIN = 100;
    static final int NB_TRAIN = 10;
    static final int NB_VOYAGEURS = 200;
    private ArrayList<Train> trains = new ArrayList<Train>();
    private ArrayList<Voyageur> voyageurs = new ArrayList<Voyageur>();
    private EspaceVente espace1;
    private EspaceQuai quai ;

    public Gare(){
        try {
            //Initialisation des espaces quai et vente
            //espace1 = new EspaceVente();
           // quai = new EspaceQuai();

            this.quai = db.espaceQuai;
            this.espace1 = db.espaceVente;
            //Initialisation des trains et des voyageurs
            for (int i = 0 ;i<NB_TRAIN; i++){
                trains.add(new Train(quai));
            }
            for (int i = 0 ;i<NB_VOYAGEURS; i++){
                   voyageurs.add(new Voyageur(espace1,quai));
            }
            //On execute le run() des classes voyageur et train pour chaque voyageur ou train initialisé
            for (Voyageur v : voyageurs){
                v.start();
            }
            for (Train t: trains){
                t.start();
            }
            //Attente que tout les trains soient parti
            for(Train t : trains){
                t.join();
            }

            System.out.println("la gare est fermée , tous les trains sont partis et ne reviendront pas jusquà nouvel ordre");
           // System.exit(0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public  static void main (String[] args) throws Exception {
        org.restlet.Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 8124);

        // Create an V2.application
        Application application = new gareApplication(context);

        // Add the db into component's context
        db = new InMemoryDatabase ();
        context.getAttributes().put("database", db);
        component.getDefaultHost().attach(application);

        // Start the component
        component.start();
        new Gare();

    }
}