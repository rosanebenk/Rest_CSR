package database;

import internals.EspaceQuai;
import internals.EspaceVente;
import internals.Train;
import internals.Voyageur;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase {

    /** User count (next id to give).*/
    private int voyageurCount_;
    private int trainCount_;

    /** User Hashmap. */
    Map<Integer, Voyageur> voyageurs_;
    Map<Integer, Train> train_;


    public InMemoryDatabase()
    {
        voyageurs_ = new HashMap<Integer, Voyageur>();
        train_ = new HashMap<Integer, Train>();
    }

    /**
     *
     * Synchronized user creation.
     *
     *
     *
     * @return the user created
     */
    public synchronized Voyageur createVoyageur()
    {
        Voyageur user = new Voyageur(new EspaceVente(), new EspaceQuai());
        user.setId(voyageurCount_);
        voyageurs_.put(voyageurCount_, user);
        voyageurCount_++;
        return user;
    }


    public synchronized Train createTrain(Train train)
    {
        //Train train = new Train();
        train.setId(trainCount_);
        train_.put(trainCount_, train);
        voyageurCount_++;
        return train;
    }

    public Collection<Voyageur> getVoyageurs()
    {
        return voyageurs_.values();
    }


    public Collection<Train> getTrains()
    {
        return train_.values();
    }



    public Voyageur getVoyageur(int id)
    {
        return voyageurs_.get(id);
    }

    public synchronized void DeleteVoyageur(int id){
        voyageurs_.remove(id);
    }

    public Train getTrain(int id)
    {
        return train_.get(id);
    }

    public synchronized void DeleteTrain(int id){
        train_.remove(id);
    }

}
