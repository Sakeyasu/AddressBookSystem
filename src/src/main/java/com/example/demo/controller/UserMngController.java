package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

@Controller
@SessionAttributes(types=UserForm.class)
public class UserMngController {
	//サービスの注入
	@Autowired
	UserService service;
	
	/** 
	 * form-backing bean の初期化
	 */
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}

	/**
	 * ユーザ管理メニュー表示
	 * @return usermng
	 */
	@GetMapping("usermng")
	public String viewUsermng(UserForm form) {
		// セッションの値をクリア
		form.setName(null);
		form.setBirth(null);
		form.setTel(null);
		form.setMail(null);
		form.setPost_num(null);
		form.setPref(null);
		form.setCity_town(null);
		form.setSt_num(null);
//		form.setPassword(null);
		// ユーザ管理メニュー表示
		return "usermng";
	}
	/**
	 * 一覧表示
	 * @return user_list
	 */
	@PostMapping(value="menu",params="select")
	public String listView(Model model) {//, @RequestParam Map<String,String> allParams) {
		// サービスの全件取得処理を呼び出す
		Iterable<User> list = service.findAll();
		// 取得した一覧情報をモデルに格納
		model.addAttribute("list", list);
		// 一覧表示
		return "user_list";
	}
	/**
	 * 登録画面表示
	 * @return user_input
	 */
	@PostMapping(value="menu",params="insert")
	public String userInputView() {
		return "user_input";
	}
	/**
	 * 登録確認画面表示
	 * @param form
	 * @return user_confirm
	 */
	@PostMapping("confirm")
	public String userConfirmView(@Validated UserForm form,
			BindingResult bindingResult
			) {
		// 入力チェック処理
		if(bindingResult.hasErrors()) {
			//ユーザ登録画面へ返す
			return "user_input";
		}

		return "user_confirm";
	}
	/**
	 * 登録処理
	 * @param form
	 * @return user_complete
	 */
	@PostMapping("insert")
	public String userCompleteView(UserForm form) {
		User user = new User(
				null, // 'id' を自動生成させるために null を設定
				form.getName(),
				form.getBirth(),
				form.getTel(),
				form.getMail(),
				form.getPost_num(),
				form.getPref(),
				form.getCity_town(),
				form.getSt_num(),
				false
		);
		service.insertUser(user);
		return "user_complete";
	}
	/**
	 * 更新画面表示
	 * @param model
	 * @return
	 */
	@PostMapping(value="menu" , params="update")
	public String showUpdate(Model model) {
		// サービスの全件取得処理を呼び出す
		Iterable<User> list = service.findAll();
		// 取得した一覧情報をモデルに格納
		model.addAttribute("list", list);
		// 更新画面表示
		return "user_update";
	}

	/**
	 * 更新入力画面表示
	 * @param model
	 * @param id
	 * @return
	 */
	@PostMapping("updateInput")
	public String showUpdateInput(Model model,
			@RequestParam Integer id ) {
		//受け取ったユーザーID（メール）からデータを1件取得する
		Optional<User> userOpt = service.findById(id);
		//データが取得できなかった場合は更新画面に戻す
		if(userOpt.isEmpty()) {
			// サービスの全件取得処理を呼び出す
			Iterable<User> list = service.findAll();
			// 取得した一覧情報をモデルに格納
			model.addAttribute("list", list);
			// エラーメッセージをモデルに追加
	        model.addAttribute("errorMessage", "指定されたユーザーが見つかりませんでした。");
			// 更新画面表示
			return "user_update";
		}
//		//データをモデルに格納する
//		model.addAttribute("userOpt", userOpt.get());
//		//戻り値はビュー名（更新入力画面）
//		return "user_updateInput";
		User user = userOpt.get();
        UserForm form = new UserForm();
        form.setId(user.getId());
        form.setName(user.getName());
        form.setBirth(user.getBirth());
        form.setTel(user.getTel());
        form.setMail(user.getMail());
        form.setPost_num(user.getPost_num());
        form.setPref(user.getPref());
        form.setCity_town(user.getCity_town());
        form.setSt_num(user.getSt_num());
        model.addAttribute("userForm", form);
        return "user_updateInput";
		
	}
	
	/**
	 * 更新確認画面表示
	 * @param form
	 * @param bindingResult
	 * @return user_updateConfirm
	 */
	@PostMapping("updateConfirm")
	public String userUpdateConfirmView(@Validated UserForm form,
			BindingResult bindingResult) {
		// 入力チェック処理
		if(bindingResult.hasErrors()) {
			// ユーザ更新入力画面へ返す
			return "user_updateInput";
		}
		return "user_updateConfirm";
	}

	
	/**
	 * 更新処理および更新完了画面表示
	 * @param f
	 * @return
	 */
	@PostMapping(value="updtcomplete",params="update")
	public String showUpdtComplete(UserForm f) {
		//Userテーブル更新用インスタンス作成
		User user = new User(
				f.getId(),
				f.getName(),
				f.getBirth(),
				f.getTel(),
				f.getMail(),
				f.getPost_num(),
				f.getPref(),
				f.getCity_town(),
				f.getSt_num(),
				false
				);
		//Userテーブル更新処理を行う
		service.memberUpdate(user);
		//戻り値はビュー名（更新完了画面）
		return "user_updtComplete";
	}
	/**
	 * 削除画面表示処理
	 * @param model
	 * @return
	 */
	@PostMapping(value="menu" , params="delete")
	public String showDelete(Model model) {
		//サービスからUserテーブル一覧を呼び出す
		Iterable<User> list = service.findAll();
		//モデルにデータを格納
		model.addAttribute("list", list);
		//戻り値はビュー名（削除画面）
		return "user_delete";
	}
	/**
	 * 削除確認画面表示
	 * @param model
	 * @param id
	 * @return
	 */
	@PostMapping("deleteConfirm")
	public String showDeleteConfirm(Model model,
			@RequestParam Integer id ) {
		
		//受け取ったユーザーID（メール）からデータを1件取得する
		Optional<User> userOpt = service.findById(id);
		//データが取得できなかった場合は削除画面に戻す
		if(userOpt.isEmpty()) {
			// サービスの全件取得処理を呼び出す
			Iterable<User> list = service.findAll();
			// 取得した一覧情報をモデルに格納
			model.addAttribute("list", list);
			// エラーメッセージをモデルに追加
	        model.addAttribute("errorMessage", "指定されたユーザーが見つかりませんでした。");
			// 削除画面表示
			return "user_delete";
		}
		User user = userOpt.get();
		
		//データをモデルに格納する
		model.addAttribute("user", user);
		//戻り値はビュー名（削除確認画面）
		return "user_deleteConfirm";
	}
	/**
	 * 削除処理および削除完了画面表示
	 * @param id
	 * @return
	 */
	@PostMapping(value="delcomplete",params="delete")
	public String showDelComplete(@RequestParam Integer id) {
		//Userテーブル削除処理を行う
		service.deleteById(id);
		//戻り値はビュー名（削除完了画面）
		return "user_delComplete";
	}	
}
