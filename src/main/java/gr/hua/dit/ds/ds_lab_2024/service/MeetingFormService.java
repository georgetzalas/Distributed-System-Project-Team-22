package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.entities.MeetingForm;
import gr.hua.dit.ds.ds_lab_2024.repositories.MeetingFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingFormService {

    private MeetingFormRepository meetingFormRepository;

    public MeetingFormService(MeetingFormRepository meetingFormRepository) {
        this.meetingFormRepository = meetingFormRepository;
    }

    @Transactional
    public List<MeetingForm> getMeetingForms() {

        return meetingFormRepository.findAll();
    }

    @Transactional
    public MeetingForm getMeetingForm(Integer meetingForm_id) {

        return meetingFormRepository.findById(meetingForm_id).get();
    }

    @Transactional
    public void saveMeetingForm(MeetingForm meetingForm) {

        meetingFormRepository.save(meetingForm);
    }

    @Transactional
    public boolean deleteMeetingForm(Integer meetingForm_id) {
        Optional<MeetingForm> meetingForm = meetingFormRepository.findById(meetingForm_id);

        if (meetingForm.isPresent()) {
            meetingFormRepository.deleteById(meetingForm_id);
            return true;
        }
        else {
            return false;
        }
    }
}
