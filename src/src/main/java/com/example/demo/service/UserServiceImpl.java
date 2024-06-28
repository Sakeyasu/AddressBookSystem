package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserCrudRepository;
@Service
public class UserServiceImpl implements UserService {
	// User用リポジトリの投入
	@Autowired
	UserCrudRepository repository;
	
	@Override
	public Iterable<User> findAll() {
		// User全件取得処理
		return repository.findAll();
	}

	@Override
	public void insertUser(User user) {
		// User 登録処理
		user.setAsNew(true);
		repository.save(user);
	//	repository.InsertData(user.getMail(),
	//			user.getPassword(),
	//			user.getUser_name());
	}

	@Override
	public Optional<User> findById(Integer id) {
		// ユーザー1件取得処理
		return repository.findById(id);
	}

	@Override
	public void memberUpdate(User user) {
		// ユーザー1件更新処理
		repository.save(user);
	}

	@Override
	public void deleteById(Integer id) {
		// ユーザー1件削除処理
		repository.deleteById(id);
	}
}
