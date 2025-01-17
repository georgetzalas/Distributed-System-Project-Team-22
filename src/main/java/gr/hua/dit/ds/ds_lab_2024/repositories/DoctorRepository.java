package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
