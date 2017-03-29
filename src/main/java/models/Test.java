package models;

/**
 * Created by User on 28/03/2017.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Test {
    @NotNull
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;


    private String text;
    private String email;

    public Test() {}

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}