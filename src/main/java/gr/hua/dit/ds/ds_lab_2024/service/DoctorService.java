package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public List<Doctor> getDoctors() {

        return doctorRepository.findAll();
    }

    @Transactional
    public Doctor getDoctor(Integer doctor_id) {

        return doctorRepository.findById(doctor_id).get();
    }

    @Transactional
    public void saveDoctor(Doctor doctor) {

        doctorRepository.save(doctor);
    }

    @Transactional
    public boolean deleteDoctor(Integer doctor_id) {
        Optional<Doctor> doctor = doctorRepository.findById(doctor_id);

        if (doctor.isPresent()) {
            doctorRepository.deleteById(doctor_id);
            return true;
        }
        else {
            return false;
        }
    }
}
