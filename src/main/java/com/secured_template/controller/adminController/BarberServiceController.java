package com.secured_template.controller.adminController;

import com.secured_template.dto.BarberServiceDto;
import com.secured_template.dto.ServiceResponseDto;
import com.secured_template.service.BarberServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class BarberServiceController {
    private final BarberServiceService serviceService; // Supondo que você tenha um BarberServiceService que lida com a lógica de negócios

    public BarberServiceController(BarberServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // funciona normal
    @PostMapping("/services")
    public ResponseEntity<String> createService(@RequestBody BarberServiceDto serviceDto) {
          serviceService.createService(serviceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Novo serviço incluído!");
    }

    // nao funciona 403 Forbiden

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ServiceResponseDto> getServiceById(@PathVariable("id") Long id) {
        ServiceResponseDto response = serviceService.getServiceById(id);
        return ResponseEntity.ok(response);
    }

    // nao funciona 403 Forbiden
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<BarberServiceDto> updateService(@PathVariable("id")  Long id, @RequestBody BarberServiceDto serviceDto) {
        BarberServiceDto response = serviceService.updateService(id, serviceDto);
        return ResponseEntity.ok(response);
    }

    // 4. Delete BarberService
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteService(@PathVariable("id") Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok("Serviço desativado com sucesso"); // Retorna um 204 No Content
    }


//    @GetMapping
//    public ResponseEntity<List<ServiceResponseDto>> getAllServices() {
//        List<ServiceResponseDto> services = serviceService.getAllServices();
//        return ResponseEntity.ok(services);
//    }
}