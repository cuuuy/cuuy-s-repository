package com.cwy.loving.presentation.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwy.loving.domain.mode.entity.Member;
import com.cwy.loving.infrastructure.dao.MemberDao;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	private static String UPLOADED_FOLDER = "c://images//";
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new MultipartResolver() {
			
			@Override
			public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isMultipart(HttpServletRequest request) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void cleanupMultipart(MultipartHttpServletRequest request) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	@GetMapping("/first")
	public ModelAndView first() {
		
		//model.addAttribute("result","helloworld!!");
		ModelAndView mav = new ModelAndView();
		mav.addObject("List", memberDao.findAll());
		//List<Member> list = model.addAttribute("List",memberDao.findAll());
		
		return mav;
	}
	
	@GetMapping("/addMember")
	public String addMember() {
		
		return "/addMember";
	}
	
	@PostMapping("/addMemberSuccess")
	public String addMemberSuccess(Member member, @RequestParam("profile") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		   
		   String name = member.getName();
		   String password = member.getPassword();
		   String profile = member.getProfile();
		   
		   System.out.println(name + "<<<<<<<<<<<name");
		   System.out.println(password + "<<<<<<<<<<<password");
		   System.out.println(profile + "<<<<<<<<<<<profile");
		   
		   member.setPassword(passwordEncoder().encode(member.getPassword()));
			
		   String inDate = new java.text.SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new java.util.Date());
		   //String inTime   = new java.text.SimpleDateFormat("HH.mm.ss").format(new java.util.Date());

		   member.setJoin_date(inDate);
		   member.setEdit_date(inDate);
		   
		   try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		   member.setProfile(file.getOriginalFilename());
		   System.out.println(file+"<<<<<<<<<<<file");
		   
		   memberDao.save(member);
		   
		   //ResponseEntity.ok().headers(headers).body(resource);
		   
		   return "/addMemberSuccess";
	}
	
//	@GetMapping("/addMember")
//	public String singleFileUpload(@RequestParam("profile") MultipartFile file,
//			RedirectAttributes redirectAttributes) {
//		
//		try {
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//			Files.write(path, bytes);
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		return "/first";
//	}
}
