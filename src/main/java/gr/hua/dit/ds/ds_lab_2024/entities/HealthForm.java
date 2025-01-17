package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.*;

@Entity
public class HealthForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Boolean approved;

    @OneToOne(mappedBy ="healthform", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Pet pet;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="doctor")
    private Doctor doctor;

    public HealthForm() {
    }

    public HealthForm(Integer id, Boolean approved, Pet pet, Doctor doctor) {
        this.id = id;
        this.approved = approved;
        this.pet = pet;
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getHealthform_id() {
        return id;
    }

    public void setHealthform_id(Integer healthform_id) {
        this.id = healthform_id;
    }
}
