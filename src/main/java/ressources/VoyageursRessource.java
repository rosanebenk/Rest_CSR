package ressources;

import database.InMemoryDatabase;
import internals.Voyageur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

public class VoyageursRessource extends ServerResource {

    /** Database. */
    private InMemoryDatabase db_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public VoyageursRessource()
    {
        super();
        db_ = (InMemoryDatabase) getApplication().getContext().getAttributes()
                .get("database");
    }

    /**
     *
     * Retourne la liste de tout les voyageurs
     *
     * @return  JSON representation des voyageurs
     */
    @Get("json")
    public Representation getVoyageurs() throws JSONException
    {
        Collection<Voyageur> voyageurs = db_.getVoyageurs();
        Collection<JSONObject> jsonVoyageurs = new ArrayList<JSONObject>();

        for (Voyageur voyageur : voyageurs)
        {
            JSONObject current = new JSONObject();
            current.put("id", voyageur.getId());
            current.put("url", getReference() + "/" + voyageur.getId());
            jsonVoyageurs.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonVoyageurs);
        return new JsonRepresentation(jsonArray);
    }

    /**
     *
     * Ajoute un voyageur à la BDD
     *
     * @return  result
     */
    @Post("json")
    public Representation createVoyageur(JsonRepresentation representation)
            throws Exception
    {
        JSONObject object = representation.getJsonObject();

        // Save the voyageur
        Voyageur voyageur = db_.createVoyageur();
        voyageur.start();

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("id", voyageur.getId());

        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }
}
