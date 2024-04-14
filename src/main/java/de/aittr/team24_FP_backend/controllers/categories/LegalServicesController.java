package de.aittr.team24_FP_backend.controllers.categories;

import de.aittr.team24_FP_backend.domain.categories.LegalServicesInfo;
import de.aittr.team24_FP_backend.services.categories.LegalServicesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{city}/legal_services_info")
public class LegalServicesController {

    private LegalServicesService service;

    public LegalServicesController(LegalServicesService service) {
        this.service = service;
    }

    @Operation(summary = "Save")
    @PostMapping("/admin")
    public LegalServicesInfo save(@RequestBody LegalServicesInfo legalServices, @PathVariable String city) {
        return service.save(legalServices, city);
    }

    @Operation(summary = "findById")
    @GetMapping("/admin/{id}")
    public LegalServicesInfo findById(@PathVariable int id) {
        return service.findById(id);
    }

    @Operation(summary = "findAll")
    @GetMapping("/admin/findAll")
    public List<LegalServicesInfo> findAll(@PathVariable String city) {
        return service.findAll(city);
    }

    @Operation(summary = "setStatus")
    @PutMapping("/admin/{id}/{status}")
    public void setStatus(@PathVariable Integer id, @PathVariable Integer status) {
        service.setStatus(id, status);
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/admin/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @Operation(summary = "findSortAll")
    @GetMapping
    public List<LegalServicesInfo> findSortAll(@PathVariable String city) {
        return service.findSortAll(city);
    }

    @Operation(summary = "findByTitle")
    @GetMapping("find_by_title/{title}")
    public List<LegalServicesInfo> findByTitle(@PathVariable String title, @PathVariable String city) {
        return service.findByTitle(title, city);
    }

    @Operation(summary = "update")
    @PutMapping("/admin")
    public void update(@RequestBody LegalServicesInfo legalServices, @PathVariable String city) {
        service.update(legalServices, city);
    }

}
