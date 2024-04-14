package de.aittr.team24_FP_backend.controllers.categories;

import de.aittr.team24_FP_backend.domain.categories.DoctorsInfo;
import de.aittr.team24_FP_backend.services.categories.DoctorsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{city}/doctors_info")
public class DoctorsController {
    
    private DoctorsService service;

    public DoctorsController(DoctorsService service) {
        this.service = service;
    }
    
    @Operation(summary = "Save")
    @PostMapping("/admin/{doctorCategory}")
    public DoctorsInfo save(@RequestBody DoctorsInfo doctor, @PathVariable String city, @PathVariable String doctorCategory) {
        return service.save(doctor, city, doctorCategory);
    }

    @Operation(summary = "findById")
    @GetMapping("/admin/{id}")
    public DoctorsInfo findById(@PathVariable int id) {
        return service.findById(id);
    }

    @Operation(summary = "findAll")
    @GetMapping("/admin/findAll/{doctorCategory}")
    public List<DoctorsInfo> findAll(@PathVariable String city, @PathVariable String doctorCategory) {
        return service.findAll(city, doctorCategory);
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
    @GetMapping("/{doctorCategory}")
    public List<DoctorsInfo> findSortAll(@PathVariable String city, @PathVariable String doctorCategory) {
        return service.findSortAll(city, doctorCategory);
    }

    @Operation(summary = "findByTitle")
    @GetMapping("find_by_title/{title}/{doctorCategory}")
    public List<DoctorsInfo> findByTitle(@PathVariable String title, @PathVariable String city, @PathVariable String doctorCategory) {
        return service.findByTitle(title, city, doctorCategory);
    }

    @Operation(summary = "update")
    @PutMapping("/admin/{doctorCategory}")
    public void update(@RequestBody DoctorsInfo doctor, @PathVariable String city, @PathVariable String doctorCategory) {
        service.update(doctor, city, doctorCategory);
    }
}
