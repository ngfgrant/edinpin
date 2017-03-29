package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 28/03/2017.
 */

@Entity
public class Opening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long openingId;

    
}
