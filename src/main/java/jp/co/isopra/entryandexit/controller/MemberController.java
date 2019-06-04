package jp.co.isopra.entryandexit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.repositories.MemberRepository;

@Controller
public class MemberController {

	@Autowired
	MemberRepository repository;

	@RequestMapping("/member")
	public ModelAndView member(ModelAndView mav) {
		mav.setViewName("member");
		Iterable<Member> list = repository.findAll();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("/memberFace/{hidden}")
	public ModelAndView memberFace(@PathVariable int hidden, ModelAndView mav) {
		mav.setViewName("memberFace");
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 1);
		return mav;
	}

	@RequestMapping("/memberUpdate/{hidden}")
	public ModelAndView memberUpdate(@PathVariable int hidden, ModelAndView mav) {
		mav.setViewName("memberEdit");
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 2);
		return mav;
	}

	@RequestMapping("/memberEdit/{hidden}")
	public ModelAndView memberEdit(@PathVariable int hidden ,ModelAndView mav) {
		mav.setViewName("memberEdit");
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 1);
		return mav;
	}

//	@RequestMapping("/memberEdit/{hidden}")
//	public ModelAndView memberEdit(@PathVariable int hidden, ModelAndView mav) {
//		mav.setViewName("memberEdit");
//		mav.addObject("hidden", hidden);
//		mav.addObject("check", hidden == 1);
//		return mav;
//	}
}
