package de.aittr.team24_FP_backend.parsing.pars_repositories;

import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDeExhibitionsParsObj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BerlinDeExhibitionsParsingRepository extends JpaRepository<BerlinDeExhibitionsParsObj, Integer> {
}
