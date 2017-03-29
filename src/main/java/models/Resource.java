package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 28/03/2017.
 */
public class Resource {
    
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long resourceId;

    private String resourceName;
    private String resourceTypeCode;
    private int addressID;
    private int openingID;
    private String resourceInfo;

    public Resource(String resourceName, String resourceTypeCode, int addressID, int openingID, String resourceInfo) {
        this.resourceName = resourceName;
        this.resourceTypeCode = resourceTypeCode;
        this.addressID = addressID;
        this.openingID = openingID;
        this.resourceInfo = resourceInfo;
    }

    public Long getResourceId() {
        return resourceId;
    }


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceTypeCode() {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode) {
        this.resourceTypeCode = resourceTypeCode;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getOpeningID() {
        return openingID;
    }

    public void setOpeningID(int openingID) {
        this.openingID = openingID;
    }

    public String getResourceInfo() {
        return resourceInfo;
    }

    public void setResourceInfo(String resourceInfo) {
        this.resourceInfo = resourceInfo;
    }
}
