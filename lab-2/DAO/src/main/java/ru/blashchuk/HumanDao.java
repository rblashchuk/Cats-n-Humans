package ru.blashchuk;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "humans", schema = "public")
public class HumanDao {
    public HumanDao(){

    }
    public long getId() {
        return Id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name")
    private String name;

    public void setId(long id) {
        Id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @OneToMany(mappedBy="owner")
    private List<CatDao> cats = new ArrayList<>();

    public void setCats(List<CatDao> cats) {
        this.cats = cats;
    }

    public List<CatDao> getCats() {
        return cats;
    }
}
