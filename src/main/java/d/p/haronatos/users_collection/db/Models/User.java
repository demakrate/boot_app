package d.p.haronatos.users_collection.db.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "users", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "id"),
                @UniqueConstraint(columnNames = "mail")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String mail;


    public User(String name, int age, String mail) {
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return (this.mail);
    }

    @Override
    public String toString() {
        return (this.name + ", возраст: " + this.age + ", @mail: " + this.mail);
    }
}
