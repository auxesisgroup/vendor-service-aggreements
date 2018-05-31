package com.auxesis.kpmg.repository;

import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.auxesis.kpmg.entity.User;

@ComponentScan
public interface UsersI extends JpaRepository<User, String> {

	@Query("SELECT t FROM User t where t.username  = :id and password= :password and role = :role")
	Optional<User> login(@Param("id") String id, @Param("password") String password, @Param("role") int role);

	@Query("SELECT t FROM User t where  role = 2")
	Optional<User> vendorList();
}
