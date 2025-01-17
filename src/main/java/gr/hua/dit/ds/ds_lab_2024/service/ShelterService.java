package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.entities.Shelter;
import gr.hua.dit.ds.ds_lab_2024.repositories.ShelterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    private ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Transactional
    public List<Shelter> getShelters() {

        return shelterRepository.findAll();
    }

    @Transactional
    public Shelter getShelter(Integer shelter_id) {

        return shelterRepository.findById(shelter_id).get();
    }

    @Transactional
    public void saveShelter(Shelter shelter) {

        shelterRepository.save(shelter);
    }

    @Transactional
    public boolean deleteShelter(Integer shelter_id) {
        Optional<Shelter> shelter = shelterRepository.findById(shelter_id);

        if (shelter.isPresent()) {
            shelterRepository.deleteById(shelter_id);
            return true;
        }
        else {
            return false;
        }
    }
}
