package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String model;

    @Column
    private int series;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public Car() {}
    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String toString() {
        return model + " " + series;
    }
    public User getUser() {return user;}
    public void setUser(User user) {
        this.user = user;
    }
}
