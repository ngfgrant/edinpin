package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 28/03/2017.
 */
public class Address {
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long addressId;

    private String address;
    private String city;

    public Long getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    private String postcode;
    private int phone;

    public Address(Long addressId, String address, String city, String postcode, int phone) {
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.phone = phone;
    }
}
