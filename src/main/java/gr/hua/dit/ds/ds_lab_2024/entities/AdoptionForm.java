package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AdoptionForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Boolean approved;

    @Column
    private Date date;

    @OneToOne(mappedBy ="adoptionform", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Pet pet;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="client_id")
    private Client client;

    public AdoptionForm() {
    }

    public AdoptionForm(Integer id, Boolean approved, Date date, Pet pet, Shelter shelter, Client client) {
        this.id = id;
        this.approved = approved;
        this.date = date;
        this.pet = pet;
        this.shelter = shelter;
        this.client = client;
    }

    public Integer getAdoptionform_id() {
        return id;
    }

    public void setAdoptionform_id(Integer adoptionform_id) {
        this.id = adoptionform_id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Client getUser() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
