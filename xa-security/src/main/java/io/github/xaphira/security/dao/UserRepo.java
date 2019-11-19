package io.github.xaphira.security.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.xaphira.security.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

	Optional<UserEntity> findById(String id);

	/**
	 * Count user by username / email address
	 * 
	 * @param keyword
	 * @return number of user
	 */
	int countByUsernameLikeOrEmailLikeOrNameLikeAllIgnoreCase(String username, String email, String name);

	/**
	 * Get user by username / email address with given role
	 * 
	 * @param keyword
	 * @param authority
	 * @return number of user
	 */
	@Query("SELECT COUNT(DISTINCT u) FROM UserEntity u INNER JOIN u.roles r WHERE (LOWER(u.username) LIKE :keyword OR LOWER(u.email) LIKE :keyword OR LOWER(u.name) LIKE :keyword) AND r.authority = :authority")
	int countUser(@Param("keyword") String keyword, @Param("authority") String authority);

	/**
	 * Get single user by email
	 * 
	 * @param email
	 * @return single User object
	 */
	UserEntity findByEmailIgnoreCase(String email);

	/**
	 * Get single user by username
	 * 
	 * @param username
	 * @return single User object
	 */
	@Query("SELECT u FROM UserEntity u JOIN FETCH u.roles r WHERE LOWER(u.username) = :username OR LOWER(u.email) = :username")
	UserEntity loadByUsername(@Param("username") String username);
	
	UserEntity findByUsername(String username);

	/**
	 * Get user by username / email address from offset as much limit defined in
	 * pageable param
	 * 
	 * @param keyword
	 * @param pageable
	 * @return list of User object
	 */
	Page<UserEntity> findByUsernameLikeOrEmailLikeOrNameLikeAllIgnoreCaseOrderByUsernameAsc(String username,
			String email, String name, Pageable pageable);

	/**
	 * Get user by username / email address with given role from offset as much
	 * limit defined in pageable param
	 * 
	 * @param keyword
	 * @param authority
	 * @param pageable
	 * @return list of User object
	 */
	// @Query("SELECT DISTINCT u FROM User u INNER JOIN u.roles r WHERE
	// (LOWER(u.username) = :keyword OR LOWER(u.email) LIKE :keyword) AND
	// r.authority = :authorityC")
	@Query("SELECT DISTINCT u FROM UserEntity u INNER JOIN u.roles r WHERE (LOWER(u.username) LIKE :keyword OR LOWER(u.email) LIKE :keyword OR LOWER(u.name) LIKE :keyword) AND r.authority = :authority ORDER BY u.username ASC, u.email ASC")
	Page<UserEntity> getUsers(@Param("keyword") String keyword, @Param("authority") String authority,
			Pageable pageable);

	@Query("SELECT DISTINCT u FROM UserEntity u INNER JOIN u.roles r WHERE LOWER(u.username) LIKE :username AND LOWER(u.email) LIKE :email AND u.phoneNumber LIKE :phoneNumber AND u.mobileNumber LIKE :mobileNumber AND LOWER(u.name) LIKE :fullName AND r.authority LIKE :authority ORDER BY u.username ASC, u.email ASC")
	Page<UserEntity> getUsers(@Param("username") String username, @Param("authority") String authority,
			@Param("email") String email, @Param("phoneNumber") String phoneNumber,
			@Param("mobileNumber") String mobileNumber, @Param("fullName") String fullName, Pageable pageable);

	List<UserEntity> findByAccountNonLocked(Boolean accountNonLocked);

	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u set u.accountNonLocked = :accountNonLocked , u.raw = :raw WHERE u.id = :id")
	Integer failedLoginAttempt(@Param("id") String id, @Param("raw") String raw,
			@Param("accountNonLocked") Boolean accountNonLocked);

	Page<UserEntity> findAll(Pageable pageable);
	
	@Query("SELECT u FROM UserEntity u WHERE u.active = '1' AND u.id NOT IN (:ids)")
	Page<UserEntity> getAllUser(@Param("ids") List<String> ids, Pageable pageable);

	int countByActive(boolean active);


}