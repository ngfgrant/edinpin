package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 28/03/2017.
 */
public class ResourceType {


    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long resourceTypeCode;

    public Long getResourceTypeCode() {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(Long resourceTypeCode) {
        this.resourceTypeCode = resourceTypeCode;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    private String resourceCategory;

    public ResourceType(Long resourceTypeCode, String resourceCategory) {
        this.resourceTypeCode = resourceTypeCode;
        this.resourceCategory = resourceCategory;
    }
}
