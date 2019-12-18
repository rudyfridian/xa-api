package io.github.xaphira.master.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import io.github.xaphira.common.entity.BaseAuditEntity;
import io.github.xaphira.common.utils.SchemaDatabase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude={"province", "districts"})
@ToString(exclude={"province", "districts"})
@Entity
@Table(name = "mst_city", schema = SchemaDatabase.MASTER)
public class CityEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125190677227153892L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", nullable = false, unique = true)
	private String id;

	@Column(name = "code", unique = true)
	private String cityCode;

	@Column(name = "name", nullable = false)
	private String cityName;
	
	@OneToMany(mappedBy = "city", targetEntity = DistrictEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<DistrictEntity> districts = new HashSet<DistrictEntity>();

	@ManyToOne(targetEntity = ProvinceEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id", nullable = false, insertable = false, updatable = false)
	private ProvinceEntity province;

}