package de.aittr.team24_FP_backend.parsing.pars_repositories;

import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDePoliticActualParsObj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BerlinDePoliticsParsingRepository extends JpaRepository<BerlinDePoliticActualParsObj, Integer> {
}
