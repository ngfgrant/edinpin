package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.SmsManager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by niallgrant on 14/04/17.
 */
@Entity
public class User implements Observer {
    static Logger logger = LoggerFactory.getLogger(User.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    public String phoneNumber;

    public User() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(Observable o, Object arg) {

        logger.info("called");

        SmsManager smsManager = new SmsManager();
        smsManager.sendSms(this.getPhoneNumber(), "test text");

        logger.info("number: " + this.getPhoneNumber() + " name: " + this.getName());

    }
}
