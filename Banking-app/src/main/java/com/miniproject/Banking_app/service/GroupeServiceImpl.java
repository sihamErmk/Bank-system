package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.GroupeDto;
import com.miniproject.Banking_app.entity.Employe;
import com.miniproject.Banking_app.entity.Groupe;
import com.miniproject.Banking_app.repository.EmployeRepository;
import com.miniproject.Banking_app.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService {

    private final GroupeRepository groupeRepository;
    private final EmployeRepository employeRepository;

    // Constructeur unique pour injecter les deux dépendances
    @Autowired
    public GroupeServiceImpl(GroupeRepository groupeRepository, EmployeRepository employeRepository) {
        this.groupeRepository = groupeRepository;
        this.employeRepository = employeRepository;
    }

    @Override
    public Groupe createGroupe(GroupeDto groupeDto) {
        Groupe groupe = new Groupe();
        groupe.setNom(groupeDto.getNom());
        return groupeRepository.save(groupe);
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGroupe(Long id) {
        groupeRepository.deleteById(id);
    }

    @Override
    public Groupe addEmployeToGroupe(Long groupId, Long employeId) {
        Groupe groupe = groupeRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Groupe not found"));
        Employe employe = employeRepository.findById(employeId).orElseThrow(() -> new RuntimeException("Employe not found"));

        // Ajouter l'employé au groupe
        groupe.getEmployes().add(employe);

        // Si la relation est bidirectionnelle, ajoutez également le groupe à l'employé
        employe.getGroupes().add(groupe);

        // Sauvegarder les changements dans la base de données
        groupeRepository.save(groupe);
        employeRepository.save(employe);

        return groupe;
    }



    @Override
    public Groupe deleteEmployeFromGroupe(Long groupId, Long employeId) {
        Groupe groupe = groupeRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Groupe not found"));
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employe not found"));

        // Remove the employee from the group
        groupe.getEmployes().remove(employe);
        return groupeRepository.save(groupe);
    }
}
