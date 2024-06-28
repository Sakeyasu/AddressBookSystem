package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.User;

public interface UserService {
	// 全件取得
	Iterable<User> findAll();
	// 登録
	void insertUser(User user);
	// 1件取得
	Optional<User> findById(Integer id);
	// 更新
	void memberUpdate(User user);
	// 削除
	void deleteById(Integer id);
}
