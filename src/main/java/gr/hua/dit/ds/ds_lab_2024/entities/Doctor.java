package gr.hua.dit.ds.ds_lab_2024.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor extends User{


    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private Integer phone;


    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<HealthForm> healthforms;


    public Doctor() {

    }

    public Doctor(String first_name, String last_name, Integer phone, List<HealthForm> healthforms) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.healthforms = healthforms;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public List<HealthForm> getHealthforms() {
        return healthforms;
    }

    public void setHealthforms(List<HealthForm> healthforms) {
        this.healthforms = healthforms;
    }
}
