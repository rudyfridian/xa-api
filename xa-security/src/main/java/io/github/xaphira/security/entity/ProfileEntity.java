package io.github.xaphira.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@EqualsAndHashCode(callSuper=false, exclude={"user"})
@ToString(exclude={"user"})
@Entity
@Table(name = "sec_profile", schema = SchemaDatabase.SECURITY)
public class ProfileEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "profile_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "fullname", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = true)
	private String city;

	@Column(name = "province", nullable = true)
	private String province;

	@Column(name = "district", nullable = true)
	private String district;

	@Column(name = "sub_district", nullable = true)
	private String subDistrict;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "mobile_number", nullable = true)
	private String mobileNumber;

	@Column(name = "image", nullable = true)
	private String image;

	@Column(name = "description", nullable = true)
	private String description;

	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_uuid", nullable = false, insertable = false, updatable = false)
	private UserEntity user;

}