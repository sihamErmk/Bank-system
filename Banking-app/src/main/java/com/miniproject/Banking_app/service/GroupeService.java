package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.GroupeDto;
import com.miniproject.Banking_app.entity.Groupe;

import java.util.List;

public interface GroupeService {


    Groupe createGroupe(GroupeDto groupeDto);
    List<Groupe> getAllGroupes();
    Groupe getGroupeById(Long id);
    void deleteGroupe(Long id);
    Groupe addEmployeToGroupe(Long groupId, Long employeId);
    Groupe deleteEmployeFromGroupe(Long groupId, Long employeId);
}
