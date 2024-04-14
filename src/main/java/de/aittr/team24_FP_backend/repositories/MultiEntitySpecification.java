package de.aittr.team24_FP_backend.repositories;

import de.aittr.team24_FP_backend.domain.categories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultiEntitySpecification {

    private EntityManager entityManager;

    public MultiEntitySpecification(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object> findByKeyword(String keyword) {
        List<Object> resultList = new ArrayList<>();

        resultList.addAll(findByKeywords(ChildrenInfo.class, keyword));
        resultList.addAll(findByKeywords(DoctorsInfo.class, keyword));
        resultList.addAll(findByKeywords(HairBeautyInfo.class, keyword));
        resultList.addAll(findByKeywords(LegalServicesInfo.class, keyword));
        resultList.addAll(findByKeywords(RestaurantsInfo.class, keyword));
        resultList.addAll(findByKeywords(ShopsInfo.class, keyword));
        resultList.addAll(findByKeywords(TranslatorsInfo.class, keyword));

        return resultList;
    }

    private List<Object> findByKeywords(Class<?> entityClass, String keywords) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = criteriaBuilder.createQuery();
        Root<?> root = query.from(entityClass);

        String[] keywordArray = keywords.split("\\s+");

        List<Predicate> predicates = new ArrayList<>();
        for (String keyword : keywordArray) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("description"), "%" + keyword + "%"),
                            criteriaBuilder.like(root.get("address"), "%" + keyword + "%"),
                            criteriaBuilder.like(root.get("title"), "%" + keyword + "%")
                    )
            );
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        query.select(root).where(finalPredicate);
        return entityManager.createQuery(query).getResultList();
    }


}
