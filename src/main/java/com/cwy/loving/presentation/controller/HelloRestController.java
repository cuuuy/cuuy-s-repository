package com.cwy.loving.presentation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cwy.loving.domain.model.entity.Hello;
import com.cwy.loving.infrastructure.dao.HelloDao;

@RestController
public class HelloRestController {

	@Autowired
	private HelloDao helloDao;

	@RequestMapping("/add")
	public Hello add(Hello hello) {

		Hello helloData = helloDao.save(hello);

		return helloData;
	}

	@RequestMapping("/list")
	public List<Hello> list(Model model) {

		List<Hello> helloList = helloDao.findAll();

		return helloList;
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id) {
		
		Hello hello = helloDao.findOne(id);
		
		model.addAttribute("hello", hello);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid Hello hello, BindingResult bindingResult) {
	
		if (bindingResult.hasErrors()) {
		
			return "list";
		}
		
		return "redirect:/list/" + helloDao.save(hello).getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id) {
		
		helloDao.delete(id);
		
		return "redirect:/list";
	}

	@RequestMapping("/")
	public String index() {
		return "helloworld!";
	}
}