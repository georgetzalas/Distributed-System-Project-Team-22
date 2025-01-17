package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MeetingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Boolean approved;

    @Column
    private Date date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="client_id")
    private Client client;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;

    public MeetingForm() {
    }

    public MeetingForm(Integer id, Boolean approved, Date date, Client client, Shelter shelter) {
        this.id = id;
        this.approved = approved;
        this.date = date;
        this.client = client;
        this.shelter = shelter;
    }

    public Integer getMeetingform_id() {
        return id;
    }

    public void setMeetingform_id(Integer meetingform_id) {
        this.id = meetingform_id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }


}
