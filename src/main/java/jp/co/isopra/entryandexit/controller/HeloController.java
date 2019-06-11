package jp.co.isopra.entryandexit.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.repositories.LocationRepository;
import jp.co.isopra.entryandexit.repositories.MemberRepository;
import jp.co.isopra.entryandexit.service.RecordService;

@Controller
public class HeloController {

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	RecordService recordService;


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

	@RequestMapping(value="/recordDetail/{mode}", method=RequestMethod.GET)
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

	@RequestMapping("/recordDetail/regist/{mode}")
	public ModelAndView recordRegist(
			@RequestParam(value="record_date")Date record_date,
			@RequestParam(value="location_id")int location_id,
			@RequestParam(value="entry_member_id")int entry_member_id,
			@RequestParam(value="entry_time")Timestamp entry_time,
			ModelAndView mav) {
		System.out.println( location_id + " " + entry_member_id + " " + entry_time);
		return new ModelAndView("redirect:/");
	}

}