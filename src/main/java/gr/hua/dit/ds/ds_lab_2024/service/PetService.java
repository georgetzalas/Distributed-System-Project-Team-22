package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.entities.Pet;
import gr.hua.dit.ds.ds_lab_2024.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {

        this.petRepository = petRepository;
    }

    @Transactional
    public List<Pet> getPets() {

        return petRepository.findAll();
    }

    @Transactional
    public Pet getPet(Integer pet_id) {

        return petRepository.findById(pet_id).get();
    }

    @Transactional
    public void savePet(Pet pet) {

        petRepository.save(pet);
    }

    @Transactional
    public boolean deletePet(Integer pet_id) {
        Optional<Pet> pet = petRepository.findById(pet_id);

        if (pet.isPresent()) {
            petRepository.deleteById(pet_id);
            return true;
        }
        else {
            return false;
        }
    }
}
