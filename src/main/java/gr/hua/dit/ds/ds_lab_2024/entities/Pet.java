package gr.hua.dit.ds.ds_lab_2024.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pet extends User{


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

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name ="adoptionform_id", referencedColumnName = "id")
    private AdoptionForm adoptionform;

    public Pet() {
    }

    public Pet(String name, String breed, Shelter shelter, HealthForm healthform, AdoptionForm adoptionform) {
        this.name = name;
        this.breed = breed;
        this.shelter = shelter;
        this.healthform = healthform;
        this.adoptionform = adoptionform;
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

    public AdoptionForm getAdoptionform() {
        return adoptionform;
    }

    public void setAdoptionform(AdoptionForm adoptionform) {
        this.adoptionform = adoptionform;
    }
}
