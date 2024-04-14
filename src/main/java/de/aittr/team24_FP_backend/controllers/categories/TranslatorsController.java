package de.aittr.team24_FP_backend.controllers.categories;


import de.aittr.team24_FP_backend.domain.categories.TranslatorsInfo;
import de.aittr.team24_FP_backend.services.categories.TranslatorsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{city}/translators_info")
public class TranslatorsController {

    private TranslatorsService service;

    public TranslatorsController(TranslatorsService service) {
        this.service = service;
    }

    @Operation(summary = "Save")
    @PostMapping("/admin")
    public TranslatorsInfo save(@RequestBody TranslatorsInfo translators, @PathVariable String city) {
        return service.save(translators, city);
    }

    @Operation(summary = "findById")
    @GetMapping("/admin/{id}")
    public TranslatorsInfo findById(@PathVariable int id) {
        return service.findById(id);
    }

    @Operation(summary = "findAll")
    @GetMapping("/admin/findAll")
    public List<TranslatorsInfo> findAll(@PathVariable String city) {
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
    public List<TranslatorsInfo> findSortAll(@PathVariable String city) {
        return service.findSortAll(city);
    }

    @Operation(summary = "findByTitle")
    @GetMapping("find_by_title/{title}")
    public List<TranslatorsInfo> findByTitle(@PathVariable String title, @PathVariable String city) {
        return service.findByTitle(title, city);
    }

    @Operation(summary = "update")
    @PutMapping("/admin")
    public void update(@RequestBody TranslatorsInfo translators, @PathVariable String city) {
        service.update(translators, city);
    }

}
