package gr.hua.dit.ds.ds_lab_2024.service;


import gr.hua.dit.ds.ds_lab_2024.entities.Client;
import gr.hua.dit.ds.ds_lab_2024.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public List<Client> getClients() {

        return clientRepository.findAll();
    }

    @Transactional
    public Client getClient(Integer id) {

        return clientRepository.findById(id).get();
    }

    @Transactional
    public void saveClient(Client client) {

        clientRepository.save(client);
    }

    @Transactional
    public boolean deleteDoctor(Integer doctor_id) {
        Optional<Client> doctor = clientRepository.findById(doctor_id);

        if (doctor.isPresent()) {
            clientRepository.deleteById(doctor_id);
            return true;
        }
        else {
            return false;
        }
    }
}
