package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Client extends User{

    @Column
    private String first_name;

    @Column
    private String last_name;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<MeetingForm> meetingforms;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<AdoptionForm> adoptionforms;

    public Client() {

    }

    public Client(String first_name, String last_name, List<MeetingForm> meetingforms, List<AdoptionForm> adoptionforms) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.meetingforms = meetingforms;
        this.adoptionforms = adoptionforms;
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

    public List<MeetingForm> getMeetingforms() {
        return meetingforms;
    }

    public void setMeetingforms(List<MeetingForm> meetingforms) {
        this.meetingforms = meetingforms;
    }

    public List<AdoptionForm> getAdoptionforms() {
        return adoptionforms;
    }

    public void setAdoptionforms(List<AdoptionForm> adoptionforms) {
        this.adoptionforms = adoptionforms;
    }
}
