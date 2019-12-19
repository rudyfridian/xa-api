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

import io.github.xaphira.master.entity.SubDistrictEntity;

public class SubDistrictSpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<SubDistrictEntity> getSelect(Map<String, String> keyword) {
		return new Specification<SubDistrictEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<SubDistrictEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.disjunction();
				if (keyword == null || keyword.isEmpty())
					predicate = builder.conjunction();
				else {
					for(Map.Entry<String, String> filter : keyword.entrySet()) {
						String key = filter.getKey();
						String value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "_label" :
								case "subDistrictName" :
									predicate.getExpressions().add(builder.like(root.<String>get("subDistrictName"), String.format("%%%s%%", value)));
									break;
								case "subDistrictCode" :
									predicate.getExpressions().add(builder.equal(root.get(key), value));
									break;
								case "district" :
									predicate = builder.and(predicate, builder.equal(root.join(key).<String>get("districtCode"), value));
									break;
							}	
						}
					}
				}
				predicate = builder.and(predicate, builder.equal(root.get(IS_ACTIVE), true));
				return predicate;
			}
		};
	}

}
