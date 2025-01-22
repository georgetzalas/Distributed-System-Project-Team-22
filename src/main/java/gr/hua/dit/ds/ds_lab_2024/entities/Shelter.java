package gr.hua.dit.ds.ds_lab_2024.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shelter extends User{



    @Column
    private String name;

    @Column
    private String address;

    @Column
    private Integer phone;

    @OneToMany(mappedBy = "shelter", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Pet> pets;

    @OneToMany(mappedBy = "shelter", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<MeetingForm> meetingforms;


    public Shelter() {
    }

    public Shelter(String name, String address, Integer phone, List<Pet> pets, List<MeetingForm> meetingforms, List<AdoptionForm> adoptionforms) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pets = pets;
        this.meetingforms = meetingforms;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<MeetingForm> getMeetingforms() {
        return meetingforms;
    }

    public void setMeetingforms(List<MeetingForm> meetingforms) {
        this.meetingforms = meetingforms;
    }


}
