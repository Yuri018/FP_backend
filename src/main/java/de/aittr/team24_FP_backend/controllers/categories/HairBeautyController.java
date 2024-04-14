package de.aittr.team24_FP_backend.controllers.categories;

import de.aittr.team24_FP_backend.domain.categories.HairBeautyInfo;
import de.aittr.team24_FP_backend.services.categories.HairBeautyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{city}/hair_beauty_info")
public class HairBeautyController {

    private HairBeautyService service;

    public HairBeautyController(HairBeautyService service) {
        this.service = service;
    }

    @Operation(summary = "Save")
    @PostMapping("/admin")
    public HairBeautyInfo save(@RequestBody HairBeautyInfo hairBeauty, @PathVariable String city) {
        return service.save(hairBeauty, city);
    }

    @Operation(summary = "findById")
    @GetMapping("/admin/{id}")
    public HairBeautyInfo findById(@PathVariable int id) {
        return service.findById(id);
    }

    @Operation(summary = "findAll")
    @GetMapping("/admin/findAll")
    public List<HairBeautyInfo> findAll(@PathVariable String city) {
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
    public List<HairBeautyInfo> findSortAll(@PathVariable String city) {
        return service.findSortAll(city);
    }

    @Operation(summary = "findByTitle")
    @GetMapping("find_by_title/{title}")
    public List<HairBeautyInfo> findByTitle(@PathVariable String title, @PathVariable String city) {
        return service.findByTitle(title, city);
    }

    @Operation(summary = "update")
    @PutMapping("/admin")
    public void update(@RequestBody HairBeautyInfo hairBeauty, @PathVariable String city) {
        service.update(hairBeauty, city);
    }

}
