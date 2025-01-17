package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.entities.HealthForm;
import gr.hua.dit.ds.ds_lab_2024.repositories.HealthFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthFormService {

    private HealthFormRepository healthFormRepository;

    public HealthFormService(HealthFormRepository healthFormRepository) {
        this.healthFormRepository = healthFormRepository;
    }

    @Transactional
    public List<HealthForm> getHealthForms() {

        return healthFormRepository.findAll();
    }

    @Transactional
    public HealthForm getHealthForm(Integer healthForm_id) {

        return healthFormRepository.findById(healthForm_id).get();
    }

    @Transactional
    public void saveHealthForm(HealthForm healthForm) {

        healthFormRepository.save(healthForm);
    }

    @Transactional
    public boolean deleteHealthForm(Integer healthForm_id) {
        Optional<HealthForm> healthForm = healthFormRepository.findById(healthForm_id);

        if (healthForm.isPresent()) {
            healthFormRepository.deleteById(healthForm_id);
            return true;
        }
        else {
            return false;
        }
    }
}
