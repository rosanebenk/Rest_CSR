package database;

import internals.EspaceQuai;
import internals.EspaceVente;
import internals.Train;
import internals.Voyageur;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase {
    public EspaceVente espaceVente;
    public EspaceQuai espaceQuai;

    /** Conteur (prochain id à donner).*/
    private int voyageurCount_;
    private int trainCount_;

    /** Hashmap de voyageur et de train */
    Map<Integer, Voyageur> voyageurs_;
    Map<Integer, Train> train_;


    /**
     * Constructeur
     */
    public InMemoryDatabase()
    {
        voyageurs_ = new HashMap<Integer, Voyageur>();
        train_ = new HashMap<Integer, Train>();
        this.espaceQuai = new EspaceQuai();
        this.espaceVente = new EspaceVente();
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
        Voyageur user = new Voyageur(espaceVente, espaceQuai);
        user.setId(voyageurCount_);
        voyageurs_.put(voyageurCount_, user);
        voyageurCount_++;
        return user;
    }

    /**
     * Création synchronisée d'un train et l'ajoute à le BDD
     * @return le train ajouté à la BDD
     */
    public synchronized Train createTrain()
    {
        Train train = new Train(espaceQuai);
        train.setId(trainCount_);
        train_.put(trainCount_, train);
        trainCount_++;
        return train;
    }

    /**
     * Get l'ensemble des voyageurs de la BDD
     * @return Collection de Voyageur
     */
    public Collection<Voyageur> getVoyageurs()
    {
        return voyageurs_.values();
    }

    /**
     * Get l'ensemble des trains de la BDD
     * @return Collection de Train
     */
    public Collection<Train> getTrains()
    {
        return train_.values();
    }


    /**
     * Get un voyageur
     * @param id du voyageur à retourner
     * @return
     */
    public Voyageur getVoyageur(int id)
    {
        return voyageurs_.get(id);
    }

    /**
     * Supprime un voyageur de la BDD
     * @param id, l'id du voyageur à supprimer
     */
    public synchronized void DeleteVoyageur(int id){
        voyageurs_.remove(id);
    }

    /**
     * Get un train
     * @param id du train à retourner
     * @return
     */
    public Train getTrain(int id)
    {
        return train_.get(id);
    }

    /**
     * Supprime un train de la BDD
     * @param id, l'id du train à supprimer
     */
    public synchronized void DeleteTrain(int id){
        train_.remove(id);
    }

}
