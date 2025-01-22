package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.*;
import gr.hua.dit.ds.ds_lab_2024.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;
    private ShelterRepository shelterRepository;
    private HealthFormRepository healthFormRepository;
    private AdoptionFormRepository adoptionFormRepository;


    public PetService(PetRepository petRepository, ShelterRepository shelterRepository, HealthFormRepository healthFormRepository, AdoptionFormRepository adoptionFormRepository) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
        this.healthFormRepository = healthFormRepository;
        this.adoptionFormRepository = adoptionFormRepository;
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
        HealthForm hf = new HealthForm();
        hf.setApproved(false);
        healthFormRepository.save(hf);
        pet.setHealthform(hf);
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

    @Transactional
    public void assignShelterToPet(Integer shelter_id,Pet pet) {
        Shelter shelter = shelterRepository.findById(shelter_id).get();
        pet.setShelter(shelter);
        petRepository.save(pet);
    }

    @Transactional
    public void assignShelterToPet(Integer shelter_id,Integer pet_id) {
        Shelter shelter = shelterRepository.findById(shelter_id).get();
        Pet pet = petRepository.findById(pet_id).get();
        pet.setShelter(shelter);
        petRepository.save(pet);
    }

    @Transactional
    public void assignHealthFormToPet(Integer healthform_id,Pet pet) {
        HealthForm form = healthFormRepository.findById(healthform_id).get();
        form.setPet(pet);
        healthFormRepository.save(form);
    }

    @Transactional
    public void assignAdoptionFormToPet(Integer adoptionform_id,Pet pet) {
        AdoptionForm form = adoptionFormRepository.findById(adoptionform_id).get();
        form.setPet(pet);
        adoptionFormRepository.save(form);
    }


}
