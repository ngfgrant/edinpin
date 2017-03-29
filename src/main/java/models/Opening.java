package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 28/03/2017.
 */
public class Opening {
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long openingId;

    
}
