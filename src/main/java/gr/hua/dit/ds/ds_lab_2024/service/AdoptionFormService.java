package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
import gr.hua.dit.ds.ds_lab_2024.repositories.AdoptionFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdoptionFormService {

    private AdoptionFormRepository adoptionFormRepository;

    public AdoptionFormService(AdoptionFormRepository adoptionFormRepository) {
        this.adoptionFormRepository = adoptionFormRepository;
    }

    @Transactional
    public List<AdoptionForm> getAdoptionForms() {

        return adoptionFormRepository.findAll();
    }

    @Transactional
    public AdoptionForm getAdoptionForm(Integer adoptionForm_id) {

        return adoptionFormRepository.findById(adoptionForm_id).get();
    }

    @Transactional
    public void saveAdoptionForm(AdoptionForm adoptionForm) {

        adoptionFormRepository.save(adoptionForm);
    }

    @Transactional
    public boolean deleteAdoptionForm(Integer adoptionForm_id) {
        Optional<AdoptionForm> adoptionForm = adoptionFormRepository.findById(adoptionForm_id);

        if (adoptionForm.isPresent()) {
            adoptionFormRepository.deleteById(adoptionForm_id);
            return true;
        }
        else {
            return false;
        }
    }

}
