package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Observable;

/**
 * Created by User on 28/03/2017.
 */
@Entity
public class ResourceType extends Observable {
    static Logger logger = LoggerFactory.getLogger(Subscription.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;


    public ResourceType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType(String name) {
        this.name = name;
    }

    public void notifySubscribers(String message){
        this.setChanged();
        this.notifyObservers(message);
    }
}

