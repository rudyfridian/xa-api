package io.github.xaphira.security.service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("userService")
public class UserImplService implements UserDetailsService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.loadByUsername(username.toLowerCase());
		if (user == null) throw new UsernameNotFoundException("User '" + username + "' not found.");
		return user;
	}

	public UserEntity doResetPassword(UserEntity user) {
		UserEntity result = userRepo.findByUsername(user.getUsername());
		if (result != null) {
			result.setPassword(passwordEncoder.encode("000000"));
			result.setModifiedBy(user.getUsername());
			result.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			result.setVerificationCode("");
			userRepo.save(result);
		}
		return result;
	}

	public UserEntity loadUserByUserId(final String id) throws UsernameNotFoundException {
		UserEntity user = userRepo.findById(id).get();
		return user;
	}

	public UserEntity findByUsername(String username) throws UsernameNotFoundException {
		LOGGER.debug("Find User " + username + " from database");
		return userRepo.findByUsername(username.toLowerCase());
	}

	@SuppressWarnings("unused")
	public void lockUser(UserEntity user, final Long lockDuration) throws Exception {
		UserEntity fromDb = userRepo.findById(user.getId()).get();
		user.setAccountNonLocked(false);

		fromDb.setAccountNonLocked(false);
		Date lockUntil = DateUtils.addMinutes(new Date(), lockDuration.intValue());
		// fromDb.setCustomProp(WebGuiConstant.PARAMETER_USER_CURRENT_LOCK_UNTIL,
		// ""+lockUntil.getTime());
		userRepo.save(fromDb);
	}

	public void unlockUser(UserEntity user) {
		UserEntity fromDb = userRepo.findById(user.getId()).get();
		fromDb.setAccountNonLocked(true);
		userRepo.save(fromDb);
	}

	@SuppressWarnings("unused")
	public UserEntity createUser(UserEntity user) {
		user.setCreatedDate(new Date());
		if (user.getPassword() != null && !user.getPassword().equalsIgnoreCase("")) {
			user.setPassword(new String(Base64.getDecoder().decode(user.getPassword())));
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setActive(true);

		// do search user by username / email address
		UserEntity temp0 = userRepo.findByUsername(user.getUsername());
		UserEntity temp1 = userRepo.findByEmailIgnoreCase(user.getEmail());

		user = userRepo.save(user);

		return user;
	}

	public UserEntity updateUser(UserEntity result) {
		UserEntity fromDb = userRepo.findById(result.getId()).get();
		if (result.getPassword() != null && !result.getPassword().equalsIgnoreCase("")) {
			if (!fromDb.getPassword().equals(result.getPassword()))
				fromDb.setPassword(passwordEncoder.encode(result.getPassword()));
		}
		fromDb.setEmail(result.getEmail());
		fromDb.setName(result.getName());
		fromDb.setAddress(result.getAddress());
		fromDb.setCity(result.getCity());
		fromDb.setProvince(result.getProvince());
		fromDb.setPhoneNumber(result.getPhoneNumber());
		fromDb.setMobileNumber(result.getMobileNumber());
		fromDb.setRoles(result.getRoles());
		fromDb.setRaw(result.getRaw());
		
		userRepo.save(fromDb);
		return fromDb;
	}

	public UserEntity updateProfile(UserEntity user) {
		UserEntity result = userRepo.findByUsername(user.getUsername());
		if (result != null) {
			if (user.getPassword() != null && !user.getPassword().equalsIgnoreCase("")) {
				if (!result.getPassword().equals(user.getPassword()))
					result.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			result.setName(user.getName());
			result.setAddress(user.getAddress());
			result.setCity(user.getCity());
			result.setProvince(user.getProvince());
			result.setPhoneNumber(user.getPhoneNumber());
			result.setMobileNumber(user.getMobileNumber());
			result.setModifiedBy(result.getUsername());
			result.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			userRepo.save(result);
		}
		return result;
	}

	public List<UserEntity> getAllLockedUser() {
		List<UserEntity> lockedUserList = userRepo.findByAccountNonLocked(false);
		return lockedUserList;
	}

	public List<UserEntity> getAllUser() {
		return userRepo.findAll();
	}

	public Integer failedAttempt(UserEntity user) {
		return userRepo.failedLoginAttempt(user.getId(), user.getRaw(), user.isAccountNonLocked());
	}

	public UserEntity loadUserByUsernameFromUpload(final String username) throws UsernameNotFoundException {
		UserEntity user = new UserEntity();
		user = userRepo.findByUsername(username.toLowerCase());

		if (user != null) {
			user.setRoles(null);
		}
		return user;
	}

	public UserEntity createUserFromUpload(UserEntity user) {
		user.setUsername(user.getUsername().toLowerCase());
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);

		user = userRepo.save(user);

		return user;
	}

	public UserEntity updateUserFromUpload(UserEntity user) {
		if (user.getPassword() != null && !user.getPassword().equalsIgnoreCase("")) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		userRepo.save(user);
		return user;
	}

	public void resetPassword(UserEntity user) {
		UserEntity result = userRepo.findByUsername(user.getUsername());
		if (result != null) {
			result.setPassword(passwordEncoder.encode("000000"));
			result.setModifiedBy(user.getUsername());
			result.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			result.setVerificationCode("");
			userRepo.save(result);
		}
	}

	public void enableUser(UserEntity user) {
		UserEntity fromDb = userRepo.findById(user.getId()).get();
		fromDb.setEnabled(true);
		userRepo.save(fromDb);
	}

	public void disableUser(UserEntity user) {
		UserEntity fromDb = userRepo.findById(user.getId()).get();
		fromDb.setEnabled(false);
		userRepo.save(fromDb);
	}

	public void deleteUser(UserEntity user) {
		try {
			userRepo.delete(user);
		} catch (Exception e) {
			LOGGER.error("ERROR DELETING USER WITH USERNAME : " + user.getUsername() + ", CAUSE : " + e.getMessage());
		}
	}

	public void softDeleteUser(List<UserEntity> userEntities) throws Exception {

		if (userEntities != null) {
			userRepo.saveAll(userEntities);
		} else {
			throw new SystemErrorException(ErrorCode.ERR_SYS0001);
		}

	}

	@SuppressWarnings("unused")
	public void addMobileUser(String username, String imei, String state, String officeCode) {
		LOGGER.debug("Find User " + username + " from database");
		UserEntity user = userRepo.findByUsername(username.toLowerCase());
		if (user == null) {
			UserEntity toAdd = new UserEntity();
			toAdd.setAccountNonExpired(true);
			toAdd.setAccountNonLocked(true);
			toAdd.setCredentialsNonExpired(true);
			toAdd.setEnabled(true);
			toAdd.setUsername(username);
			toAdd.setPassword(username);
			toAdd.setName(username);
			toAdd.setCity(state);
			toAdd.setEmail(username);
			toAdd.setAddress("IMEI=" + imei);
			try {
				toAdd.setUsername(toAdd.getUsername().toLowerCase());
				toAdd.setCreatedDate(new Date());
				if (toAdd.getPassword() != null && !toAdd.getPassword().equalsIgnoreCase(""))
					toAdd.setPassword(passwordEncoder.encode(toAdd.getPassword()));

				// do search user by username / email address
				UserEntity temp0 = userRepo.findByUsername(toAdd.getUsername());
				UserEntity temp1 = userRepo.findByEmailIgnoreCase(toAdd.getEmail());

				toAdd = userRepo.save(toAdd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
