package org.skyjava.course2.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;
import org.skyjava.course2.domains.Question;
import org.springframework.data.jpa.domain.Specification;

public class QuestionSpecification implements Specification<Question> {
    @Override
    public Predicate toPredicate(@NotNull Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate = builder.conjunction();
        assert query != null;
        query.orderBy(builder.asc(builder.function("RAND", null)));
        return predicate;
    }
}