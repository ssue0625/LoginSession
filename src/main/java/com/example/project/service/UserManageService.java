package com.example.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.domain.User;
import com.example.project.repository.UserManageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManageService {

	private final UserManageRepository userManageRepository;

	@Transactional
	public User findByLoginIdAndPassword(User user) {
		User findUser = userManageRepository.findByLoginIdAndPassword(user.getLoginId(), user.getPassword());
		return findUser;
	}

	@Transactional
	public Long save(User user) {
		userManageRepository.save(user);
		return user.getId();
	}

}