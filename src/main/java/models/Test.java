package models;

/**
 * Created by User on 28/03/2017.
 */

import javax.persistence.*;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    private String text;

    @Column
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