/**
 * 
 */
package io.github.xaphira.master.dao.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import io.github.xaphira.master.entity.CountryEntity;

public class CountrySpecification {

	public static Specification<CountryEntity> getSelect(Integer offset, Integer limit, String keyword, String order, String sortBy) {
		return new Specification<CountryEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<CountryEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate _predicate = builder.disjunction();
				if(keyword == null)
					_predicate = builder.conjunction();
				else {
					_predicate.getExpressions().add(builder.equal(root.get(sortBy), keyword));
				}
				_predicate = builder.and(_predicate, builder.equal(root.get("active"), 1));
				return _predicate;
			}
		};
	}

}
