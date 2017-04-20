package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by User on 28/03/2017.
 */


@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String resourceType;
    private String resourceTitle;
    private String companyName;
    private String managerName;
    private String address;
    private String postCode;
    private String date;
    private String description;

    public Resource(String resourceType, String resourceTitle, String companyName, String managerName, String address, String postCode, String date, String description) {
        this.resourceType = resourceType;
        this.resourceTitle = resourceTitle;
        this.companyName = companyName;
        this.managerName = managerName;
        this.address = address;
        this.postCode = postCode;
        this.date = date;
        this.description = description;
    }

    public Resource() {

    }

    public Long getResourceId() {
        return id;
    }

    public void setResourceId(Long resourceId) {
        this.id = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
