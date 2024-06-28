package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addressbook")
public class User implements Persistable<Integer>{
	@Id
	private Integer id;
	private String name;
	private String birth;
	private String tel;
	private String mail;
	private String post_num;
	private String pref;
	private String city_town;
	private String st_num;
	
	@Transient
	private boolean flgnew;

	@Override
	public Integer getId() {
		// TODO 自動生成されたメソッド・スタブ
		return id;
	}
	
	public User setAsNew(boolean flg){
	    this.flgnew = flg;
	    return this;
	}

	@Override
	public boolean isNew() {
		// TODO 自動生成されたメソッド・スタブ
		return this.flgnew || id == null;
	}
	
}
