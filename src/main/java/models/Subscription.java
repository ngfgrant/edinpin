package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 18/04/2017.
 */
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Long userId;
    public Long resourceTypeId;

    static Logger logger = LoggerFactory.getLogger(Subscription.class);

    public Subscription(User user, ResourceType resourceType) {
        this.userId=user.getId();
        this.resourceTypeId=resourceType.getId();
    }

}
