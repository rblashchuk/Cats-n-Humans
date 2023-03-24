package ru.blashchuk;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="cats", schema="public")
public class CatDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long Id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(
            name = "relations",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "related_id")
    )
    private List<CatDao> friends;

    public List<CatDao> getFriends() {
        return friends;
    }

    public void setFriends(List<CatDao> friends) {
        this.friends = friends;
    }

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private HumanDao owner;

    public HumanDao getOwner() {
        return owner;
    }

    public void setOwner(HumanDao owner) {
        this.owner = owner;
    }

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "breed")
    @Enumerated(EnumType.STRING)
    private Breed breed;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "name")
    private String name;

    public CatDao(){

    }
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
