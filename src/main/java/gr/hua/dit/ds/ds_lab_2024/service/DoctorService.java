package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.entities.HealthForm;
import gr.hua.dit.ds.ds_lab_2024.entities.Role;
import gr.hua.dit.ds.ds_lab_2024.repositories.DoctorRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.HealthFormRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private RoleRepository roleRepository;
    private HealthFormRepository healthFormRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, HealthFormRepository healthFormRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.healthFormRepository = healthFormRepository;
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
    public Integer saveDoctor(Doctor doctor) {
        String passwd= doctor.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);
        doctor.setPassword(encodedPassword);

        Role role = roleRepository.findByName("ROLE_DOCTOR")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        doctor.setRoles(roles);

        doctor = doctorRepository.save(doctor);
        return doctor.getId();
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
    @Transactional
    public Object findByUsername(String username) {
        return  doctorRepository.findByUsername(username);
    }

    @Transactional
    public void assignHealthFormToDoctor(Integer healthform_id,Doctor doctor) {
        HealthForm form = healthFormRepository.findById(healthform_id).get();
        form.setDoctor(doctor);
        healthFormRepository.save(form);
    }


}
