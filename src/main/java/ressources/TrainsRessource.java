package ressources;

import database.InMemoryDatabase;
import internals.EspaceQuai;
import internals.Train;
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

public class TrainsRessource extends ServerResource {
    private InMemoryDatabase db_;
    private Train train_;

    /**
     * Constructeur
     */
    public TrainsRessource() {
        super();
        this.db_ = (InMemoryDatabase) getApplication().getContext().getAttributes()
                .get("database");
    }

    /**
     *
     * Retourne la liste de tout les trains
     *
     * @return  JSON representation des trains
     */
    @Get("json")
    public Representation getTrain() throws Exception
    {
        Collection<Train> trains = db_.getTrains();
        Collection<JSONObject> jsonTrains = new ArrayList<JSONObject>();

        for (Train train : trains)
        {
            JSONObject current = new JSONObject();
            current.put("id", train.getId());
            current.put("url", getReference() + "/" + train.getId());
            jsonTrains.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonTrains);
        return new JsonRepresentation(jsonArray);
    }

    /**
     *
     * Ajoute un voyageur à la BDD
     *
     * @return  result
     */
    @Post("json")
    public Representation createTrain(JsonRepresentation representation)
            throws Exception
    {
        JSONObject object = representation.getJsonObject();

        // Save the train
        Train train = db_.createTrain();
        train.start();

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("id", train.getId());

        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}
