/**
 * 
 */
package io.github.xaphira.master.dao.specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import io.github.xaphira.master.entity.CountryEntity;

public class CountrySpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<CountryEntity> getSelect(Map<String, String> keyword) {
		return new Specification<CountryEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<CountryEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.disjunction();
				if (keyword == null || keyword.isEmpty())
					predicate = builder.conjunction();
				else {
					for(Map.Entry<String, String> filter : keyword.entrySet()) {
						String key = filter.getKey();
						String value = filter.getValue();
						switch (key) {
							case "_label" :
							case "countryName" :
								predicate.getExpressions().add(builder.like(root.<String>get("countryName"), String.format("%%%s%%", value)));
								break;
							case "capital" :
								predicate.getExpressions().add(builder.like(root.<String>get(key), String.format("%%%s%%", value)));
								break;
							case "countryCode" :
							case "phonePrefix" :
							case "flag" :
								predicate.getExpressions().add(builder.equal(root.get(key), value));
								break;
						}
					}
				}
				predicate = builder.and(predicate, builder.equal(root.get(IS_ACTIVE), true));
				return predicate;
			}
		};
	}

}
