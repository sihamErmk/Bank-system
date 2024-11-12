package com.miniproject.Banking_app.web;

import com.miniproject.Banking_app.dto.GroupeDto;
import com.miniproject.Banking_app.entity.Groupe;
import com.miniproject.Banking_app.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupRestController {

    private final GroupeService groupeService;

    @Autowired
    public GroupRestController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping("/groupes")
    public ResponseEntity<List<Groupe>> getAllGroupes() {
        List<Groupe> groupes = groupeService.getAllGroupes();
        return ResponseEntity.ok(groupes);
    }

    @GetMapping("/groupes/{id}")
    public ResponseEntity<Groupe> getGroupeById(@PathVariable Long id) {
        Groupe groupe = groupeService.getGroupeById(id);
        return ResponseEntity.ok(groupe);
    }

    @PostMapping("/groupes")
    public ResponseEntity<Groupe> createGroupe(@RequestBody GroupeDto groupeDto) {
        Groupe createdGroupe = groupeService.createGroupe(groupeDto);
        return ResponseEntity.ok(createdGroupe);
    }

    @DeleteMapping("/groupes/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable("id") Long id) {
        groupeService.deleteGroupe(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/groupes/{groupId}/employes/{employeId}")
    public ResponseEntity<Groupe> addEmployeToGroupe(@PathVariable("groupId") Long groupId, @PathVariable("employeId") Long employeId) {
        Groupe updatedGroupe = groupeService.addEmployeToGroupe(groupId, employeId);
        return ResponseEntity.ok(updatedGroupe);
    }

    //delete  employee from group
    @DeleteMapping("/groupes/{groupId}/employes/{employeId}")
    public ResponseEntity<Groupe> deleteEmployeFromGroupe(@PathVariable("groupId") Long groupId, @PathVariable("employeId") Long employeId) {
        Groupe updatedGroupe = groupeService.deleteEmployeFromGroupe(groupId, employeId);
        return ResponseEntity.ok(updatedGroupe);
    }
}
