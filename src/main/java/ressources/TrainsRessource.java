package ressources;

import database.InMemoryDatabase;
import internals.Train;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class TrainsRessource extends ServerResource {
    private InMemoryDatabase db_;
    private Train train_;

    public TrainsRessource() {
        super();
        this.db_ = (InMemoryDatabase) getApplication().getContext().getAttributes()
                .get("database");
    }
    @Get("json")
    public Representation getTrain() throws Exception
    {
        String trainIdString = (String) getRequest().getAttributes().get("trainId");
        int trainIdString = Integer.valueOf(trainIdString);
        train_ = db_.getTrain(trainIdString);

        JSONObject userObject = new JSONObject();
        userObject.put("name", user_.getName());
        userObject.put("age", user_.getAge());
        userObject.put("id", user_.getId());

        return new JsonRepresentation(userObject);
    }

}
