package jp.co.isopra.entryandexit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.repositories.LocationRepository;
import jp.co.isopra.entryandexit.repositories.MemberRepository;

@Controller
public class HeloController {

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	MemberRepository memberRepository;


	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/faceCapture/{mode}")
	public ModelAndView faceCapture(ModelAndView mav,
			@PathVariable int mode) {
		mav.setViewName("faceCapture");
		mav.addObject("mode", mode);
		return mav;
	}

	@RequestMapping("/recordDetail/{mode}")
	public ModelAndView recordDetail(ModelAndView mav,
			@PathVariable int mode,
			@ModelAttribute Location location,
			@RequestParam(value="sendPersonId")String sendPersonId){
		mav.setViewName("recordDetail");
		mav.addObject("mode", mode);
		Iterable<Location> locationList =  locationRepository.findAllOrderById();
		Iterable<Member> memberList =  memberRepository.findAllOrderById();
		mav.addObject("locationList",locationList);
		mav.addObject("memberList", memberList);
		mav.addObject("personId",sendPersonId);
		return mav;
	}

}