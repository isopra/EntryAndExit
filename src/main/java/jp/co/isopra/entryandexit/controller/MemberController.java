package jp.co.isopra.entryandexit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.repositories.MemberRepository;
import jp.co.isopra.entryandexit.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberRepository repository;

	@Autowired
	private MemberService service;

	@RequestMapping("/member")
	public ModelAndView member(ModelAndView mav) {
		mav.setViewName("member");
		//Iterable<Member> list = repository.findAllOrderById();

		//MemberService利用
		List<Member> list = service.getAll();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value="/memberNew/{hidden}", method=RequestMethod.GET)
	public ModelAndView memberNew(
			@ModelAttribute("formModel") Member member,
			@PathVariable int hidden ,
			ModelAndView mav) {
		mav.setViewName("memberEdit");
		mav.addObject("formModel", member);
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 1);
		return mav;
	}

	@RequestMapping(value="/memberNew/{hidden}", method=RequestMethod.POST)
	public ModelAndView memberCreate(
			@ModelAttribute("formModel")@Validated Member member,
			@PathVariable int hidden,
			BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
			repository.saveAndFlush(member);
			res = new ModelAndView("redirect:/memberFace/" + hidden);
		}else {
			mav.setViewName("memberEdit");
			mav.addObject("hidden", hidden);
			mav.addObject("check", hidden == 1);
			res = mav;
		}
		return res;
	}

	@RequestMapping(value="/memberEdit/{hidden}", method=RequestMethod.GET)
	public ModelAndView memberEdit(
			@ModelAttribute("formModel") Member member,
			@RequestParam(value="member") int id,
			@PathVariable int hidden,
			ModelAndView mav) {
		mav.setViewName("memberEdit");
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 1);
		Optional<Member> data = repository.findById(id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value="/memberEdit/{hidden}", method=RequestMethod.POST)
	public ModelAndView memberUpdate(
			@ModelAttribute("formModel")@Validated Member member,
			@PathVariable int hidden,
			BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
			repository.saveAndFlush(member);
			res = new ModelAndView("redirect:/member");
		}else {
			mav.setViewName("memberEdit");
			mav.addObject("hidden", hidden);
			mav.addObject("check", hidden == 1);
			res = mav;
		}
		return res;
	}

	@RequestMapping("/memberFace/{hidden}")
	public ModelAndView memberFace(
			@ModelAttribute("formModel")Member member,
			@PathVariable int hidden,
			ModelAndView mav) {
		mav.setViewName("memberFace");
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 1);
		//Iterable<Member> list = repository.findAll();
		List<Member> list = service.getAll();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("/memberRegistFace/{hidden}")
	public ModelAndView memberRegistFace(
			@ModelAttribute("formModel")Member member,
			@RequestParam(value="member_id")int id,
			@PathVariable int hidden,
			ModelAndView mav) {
		mav.setViewName("memberFace");
		mav.addObject("hidden", hidden);
		mav.addObject("check", hidden == 1);
		Optional<Member> data = repository.findById(id);
		mav.addObject("formModel", data.get());
		return mav;
	}

}
