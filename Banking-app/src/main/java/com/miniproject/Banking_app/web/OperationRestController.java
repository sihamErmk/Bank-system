package com.miniproject.Banking_app.web;

import com.miniproject.Banking_app.dto.OperationDto;
import com.miniproject.Banking_app.entity.Operation;
import com.miniproject.Banking_app.service.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class OperationRestController {
    private final OperationService operationService;

    OperationRestController(final OperationService operationService){

        this.operationService = operationService;
    }
    @PostMapping("/operations/versement")
    void effectuerVersement(@RequestBody OperationDto dto) {
        this.operationService.effectuerVersement(dto);
    }



    @PostMapping("/operations/retrait")
    void effectuerRetrait(@RequestBody OperationDto dto){

        this.operationService.effectuerRetrait(dto);
    }
    @PostMapping("/operations/virement")
    void virement(@RequestBody OperationDto dto ){
        this.operationService.effectuerVirement(dto);
    }

    // Reccupere  l'historique d'un compte
    @GetMapping("/operations/client/{accountNumber}")
    List<Operation> findByClientAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return this.operationService.findByClientAccountNumber(accountNumber);
    }


}
