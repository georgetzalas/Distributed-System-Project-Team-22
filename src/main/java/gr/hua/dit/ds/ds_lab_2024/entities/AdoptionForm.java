package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class AdoptionForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Boolean approved;

    @Column
    private Date date;

    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="pet_id")
    private Pet pet;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="client_id")
    private Client client;

    public AdoptionForm() {
    }

    public AdoptionForm(Integer id, Boolean approved, Date date, String description, Pet pet, Client client) {
        this.id = id;
        this.approved = approved;
        this.date = date;
        this.description = description;
        this.pet = pet;
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


    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }
}
