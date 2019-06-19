package jp.co.isopra.entryandexit.controller;

import java.util.List;

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
import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.repositories.LocationRepository;
import jp.co.isopra.entryandexit.repositories.MemberRepository;
import jp.co.isopra.entryandexit.repositories.RecordRepository;
import jp.co.isopra.entryandexit.service.LocationService;
import jp.co.isopra.entryandexit.service.RecordService;

@Controller
public class HeloController {

	@Autowired
	RecordRepository recordRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	RecordService recordService;

	@Autowired
	LocationService locationService;

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

	@RequestMapping(value = "/recordDetail/{mode}", method = RequestMethod.GET)
	public ModelAndView recordDetail(ModelAndView mav,
			@PathVariable int mode,
			@ModelAttribute Location location,
			@ModelAttribute ("formModel") Record record,
			@RequestParam(value = "sendPersonId") String sendPersonId) {
		mav.setViewName("recordDetail");
		mav.addObject("mode", mode);
		List<Location> locationList =  locationService.getAll();
		Iterable<Member> memberList = memberRepository.findAllOrderById();
		Iterable<Record>recordList = recordRepository.findAll();
		mav.addObject("recordList", recordList);
		mav.addObject("locationList", locationList);
		mav.addObject("memberList", memberList);
		mav.addObject("personId", sendPersonId);
		return mav;
	}
}