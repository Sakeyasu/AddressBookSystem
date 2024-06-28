package com.example.demo.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserForm {
//	1
	private Integer id;
//	2
	@NotEmpty
	private String name;
//	3
//	@NotEmpty (@Patternがあるので必要なし)
//	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//	@DateTimeFormat(pattern="yyyy/MM/dd")
//	@Pattern(regexp="([1-2][09][0-9][0-9]-[01][0-9]-[0-9][0-9])")
	@Pattern(regexp="(19[0-9][0-9]-[01][0-9]-[0-3][0-9]|20[0-9][0-9]-[01][0-9]-[0-3][0-9])")
	private String birth;
//	4
//	@NotEmpty (@Patternがあるので必要なし)
	@Pattern(regexp="^(0[5789]0-[0-9]{4}-[0-9]{4}|0([0-9]-[0-9]{4}|[0-9]{2}-[0-9]{3}|[0-9]{3}-[0-9]{2}|[0-9]{4}-[0-9])-[0-9]{4})$" , message="日本国内の携帯電話または固定電話の番号を半角で入力してください")
//	@Pattern(regexp="^(0[5789]0-[0-9]{4}-[0-9]{4}|0[1-9]\\d{1,4}-\\d{1,4}-\\d{4})*$" , message="日本国内の携帯電話または固定電話の番号を半角で入力してください")
	private String tel;
//	5
	@NotEmpty //(@Patternがあっても必要！！)
	@Email
	private String mail;
//	6
//	@NotEmpty (@Patternがあるので必要なし)
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}" , message="{0}はハイフンを含み、半角8文字で入力してください。")
	private String post_num;
//	7
	@NotEmpty
	private String pref;
//	8
	@NotEmpty
	private String city_town;
//	9
	@NotEmpty
	private String st_num;
	
//	@NotEmpty
//	@Length(min=3,max=10)
//	@Pattern(regexp="[a-zA-Z0-9]*")
//	private String password;

}
