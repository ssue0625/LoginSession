package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.domain.User;

public interface UserManageRepository extends JpaRepository<User, Long> {

	User findByLoginIdAndPassword(String loginId, String password);

}
