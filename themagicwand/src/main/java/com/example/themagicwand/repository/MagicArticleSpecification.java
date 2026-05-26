package com.example.themagicwand.repository;

import com.example.themagicwand.entity.MagicArticle;
import com.example.themagicwand.enums.ArticleType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MagicArticleSpecification {

    public static Specification<MagicArticle> withFilters(ArticleType category, Long providerId, BigDecimal maxPrice) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("tipo"), category));
            }

            if (providerId != null) {
                predicates.add(cb.equal(root.get("provedor").get("id"), providerId));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("precio"), maxPrice));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
