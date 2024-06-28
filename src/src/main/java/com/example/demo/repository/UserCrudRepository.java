package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
	@Modifying
	@Query(value = "INSERT INTO addressbook VALUES(:name, :birth, :tel, :mail, :post_num, :pref, :city_town, :st_num)")
	void InsertData(
			@Param("id") Integer id,
			@Param("name") String name,
			@Param("birth") String birth,
			@Param("tel") String tel,
			@Param("mail") String mail, 
			@Param("post_num") String post_num,
			@Param("pref") String pref,
			@Param("city_town") String city_town,
			@Param("st_num") String st_num
			       );
}
