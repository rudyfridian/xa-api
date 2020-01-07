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

import io.github.xaphira.master.entity.CityEntity;

public class CitySpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<CityEntity> getSelect(Map<String, String> keyword) {
		return new Specification<CityEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<CityEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, String> filter : keyword.entrySet()) {
						String key = filter.getKey();
						String value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "_label" :
								case "cityName" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("cityName")), String.format("%%%s%%", value.toUpperCase())));
									break;
								case "cityCode" :
									predicate.getExpressions().add(builder.equal(root.get(key), value));
									break;
								case "province" :
									predicate = builder.and(predicate, builder.equal(root.join(key).<String>get("provinceCode"), value.toUpperCase()));
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

	public static Specification<CityEntity> getDatatable(Map<String, String> keyword) {
		return new Specification<CityEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<CityEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					predicate = builder.disjunction();
					for(Map.Entry<String, String> filter : keyword.entrySet()) {
						String key = filter.getKey();
						String value = filter.getValue();
						if (value != null) {
							switch (key) {									
								case "cityName" :
								case "cityCode" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get(key)), String.format("%%%s%%", value.toUpperCase())));
									break;
								case "province" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.join(key).<String>get("provinceName")), String.format("%%%s%%", value.toUpperCase())));
									break;
							}	
						}
					}
				}
				return predicate;
			}
		};
	}

}
