package gr.hua.dit.ds.ds_lab_2024.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pets")
public class Pet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column
    private String name;

    @Column
    private String breed;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name ="healthform_id", referencedColumnName = "id")
    private HealthForm healthform;

    @OneToMany(mappedBy = "pet", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<AdoptionForm> adoptionforms;

    public Pet() {
    }

    public Pet(String name, String breed, Shelter shelter, HealthForm healthform, List<AdoptionForm> adoptionforms) {
        this.name = name;
        this.breed = breed;
        this.shelter = shelter;
        this.healthform = healthform;
        this.adoptionforms = adoptionforms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public HealthForm getHealthform() {
        return healthform;
    }

    public void setHealthform(HealthForm healthform) {
        this.healthform = healthform;
    }

    public List<AdoptionForm> getAdoptionforms() {
        return adoptionforms;
    }

    public void setAdoptionforms(List<AdoptionForm> adoptionforms) {
        this.adoptionforms = adoptionforms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
