package de.aittr.team24_FP_backend.parsing.pars_repositories;

import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDeCultEventsParsObj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BerlinDeCultEventsParsingRepository extends JpaRepository<BerlinDeCultEventsParsObj, Integer> {
}
