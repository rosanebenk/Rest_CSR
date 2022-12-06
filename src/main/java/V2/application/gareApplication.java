package V2.application;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import V2.ressources.TrainsRessource;
import V2.ressources.VoyageursRessource;

public class gareApplication extends Application {
    public gareApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/trains", TrainsRessource.class);
        router.attach("/trains/{trainId}", TrainsRessource.class);
        router.attach("/voyageurs", VoyageursRessource.class);
        router.attach("/voyageurs/{voyageurId}", VoyageursRessource.class);
        return router;
    }
}
