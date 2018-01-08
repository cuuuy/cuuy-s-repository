package com.cwy.loving.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cwy.loving.domain.model.entity.Member;
import com.cwy.loving.infrastructure.dao.MemberDao;


@RestController
public class MemberRestController {

	@Autowired
	private MemberDao memberDao;
	
	//password ��ȣȭ
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	//���� �߰�
	@PutMapping("/add")
	public Member add(Member member) {
			
		//�н����� ��ȣȭ
		member.setPassword(passwordEncoder().encode(member.getPassword()));
		
		//���Խð� ������Ʈ�ð� �����ð����� ����
		String inDate = new java.text.SimpleDateFormat("yyyy.MM.dd. HH.mm.ss").format(new java.util.Date());
		//String inTime   = new java.text.SimpleDateFormat("HH.mm.ss").format(new java.util.Date());

		member.setJoin_date(inDate);
		member.setEdit_date(inDate);

		return memberDao.save(member);
	}
	
	//���� ��ü��� ����
	@GetMapping("/list")
	public List<Member> list(Model model) {

		List<Member> memberList = memberDao.findAll();

		return memberList;
	}
	
	//���� ����
	@GetMapping("/{id}/edit")
	public String editor(Model model, @PathVariable int id) {
		
		Member member = memberDao.findOne(id);
		
		model.addAttribute("member", member);
		
		return "redirect:/list";
	}
	
//	@PutMapping("/{id}/edit")
//	public String edit(@RequestBody Member member, BindingResult bindingResult, @PathVariable int id) {
//	
//		member = memberDao.findOne(id);
//		
//		if (bindingResult.hasErrors()) {
//			
//			return "list";
//		}
//		
//		memberDao.save(member);
//		
//		return "redirect:/list/" + memberDao.save(member).getId();
//	}
	
	//���� ����
	@PutMapping("/{id}/edit")
	public String edit(@RequestBody Member member, BindingResult bindingResult, @PathVariable int id) {
	
		memberDao.save(member);
		
		return "redirect:/list";
	}
	   
	//���� ����
	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable int id) {
		
		memberDao.delete(id);
		
		return "redirect:/list";
	}
	
	@GetMapping("/first")
	public ModelAndView first() {
		
		//model.addAttribute("result","helloworld!!");
		ModelAndView mav = new ModelAndView();
		mav.addObject("List", memberDao.findAll());
		//List<Member> list = model.addAttribute("List",memberDao.findAll());
		
		return mav;
	}

}