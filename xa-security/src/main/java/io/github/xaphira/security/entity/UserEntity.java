package io.github.xaphira.security.entity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@EqualsAndHashCode(callSuper=false, exclude={"roles", "profile", "settings"})
@ToString(exclude={"roles", "profile", "settings"})
@Entity
@Table(name = "sec_user", schema = SchemaDatabase.SECURITY)
public class UserEntity extends BaseAuditEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "user_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "username", nullable = false, unique = true, length = 25)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "account_enabled", nullable = false)
	private boolean enabled = true;

	@Column(name = "account_non_expired", nullable = false)
	private boolean accountNonExpired = true;

	@Column(name = "account_non_locked", nullable = false)
	private boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired", nullable = false)
	private boolean credentialsNonExpired = true;

	@Column(name = "email", nullable = true, unique = true)
	private String email;

	@Column(name = "verification_code", nullable = true)
	private String verificationCode;

	@Column(name = "raw", nullable = true)
	private String raw;

	@Column(name = "authority_default")
	private String authorityDefault;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sec_r_user_role",
		schema = SchemaDatabase.SECURITY,
		joinColumns = { 
				@JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")}, 
		inverseJoinColumns =
				@JoinColumn(name = "role_uuid", referencedColumnName = "role_uuid"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();

	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
		authorities.addAll(roles);
		return authorities;
	}
	
	@OneToOne(mappedBy = "user", targetEntity = ProfileEntity.class, fetch = FetchType.LAZY)
	private ProfileEntity profile;
	
	@OneToOne(mappedBy = "user", targetEntity = SettingsEntity.class, fetch = FetchType.LAZY)
	private SettingsEntity settings;

}