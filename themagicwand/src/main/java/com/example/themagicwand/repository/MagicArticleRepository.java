package com.example.themagicwand.repository;

import com.example.themagicwand.entity.MagicArticle;
import com.example.themagicwand.enums.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicArticleRepository extends JpaRepository<MagicArticle, Long>,
        JpaSpecificationExecutor<MagicArticle> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    boolean existsByProviderId(Long providerId);
}
