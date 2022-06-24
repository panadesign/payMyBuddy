package com.payMyBuddy.dao;

import com.payMyBuddy.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
	Person findByEmail(@Param("email") String email);
}
