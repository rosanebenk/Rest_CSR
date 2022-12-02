package application;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import ressources.TrainsRessource;
import ressources.VoyageursRessource;

public class gareApplication extends Application {
    public gareApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/Trains/", TrainsRessource.class);
        router.attach("/Trains/{trainId}", TrainsRessource.class);
        router.attach("/Voyageurs/", VoyageursRessource.class);
        router.attach("/Voyageurs/{voyageurId}", VoyageursRessource.class);
        return router;
    }
}
