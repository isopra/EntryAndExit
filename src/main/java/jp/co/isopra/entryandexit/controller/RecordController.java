package jp.co.isopra.entryandexit.controller;



import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.repositories.LocationRepository;
import jp.co.isopra.entryandexit.repositories.MemberRepository;
import jp.co.isopra.entryandexit.repositories.RecordRepository;
import jp.co.isopra.entryandexit.service.RecordService;

@Controller
public class RecordController {

	@Autowired
	RecordRepository recordRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	RecordService recordService;


	@RequestMapping("/record")
	public ModelAndView record(ModelAndView mav,
			@ModelAttribute Record record,
			BindingResult result1,
			@ModelAttribute Member member,
			BindingResult result2,
			@ModelAttribute Location location,
			BindingResult result3) {
		mav.setViewName("record");
		Iterable<Record> recordT = recordRepository.findAll();
		Iterable<Member> memberT = memberRepository.findAll();
		Iterable<Location> locationT = locationRepository.findAll();
		mav.addObject("recordT",recordT);
		mav.addObject("memberT",memberT);
		mav.addObject("locationT",locationT);
		if(result1.hasErrors() || result2.hasErrors()) {
			for(ObjectError error:result1.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			for(ObjectError error:result2.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			for(ObjectError error:result3.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
		}
		return mav;
	}

	@RequestMapping("/recordDetail/regist/1")
	public ModelAndView recordEntryRegist(
			@ModelAttribute("formModel") @Validated Record record,
			@RequestParam(value="record_date") String record_date,
			@RequestParam(value="location_id") int location_id,
			ModelAndView mav
			) {

		if(recordService.searchEntry(record_date, location_id).size() == 0) {
			recordRepository.saveAndFlush(record);
			mav = new ModelAndView("redirect:/");
		}else {
			mav = new ModelAndView("redirect:/");
		}

		return mav;

	}

	@RequestMapping("/recordDetail/regist/2")
	public ModelAndView recordExitRegist(
			@RequestParam(value="record_date")Date record_date,
			@RequestParam(value="location_id")int location_id,
			@RequestParam(value="exit_member_id")int exit_member_id,
			@RequestParam(value="exit_time")Timestamp exit_time,
			@RequestParam(value="created_time")Timestamp created_time,
			@RequestParam(value="entry_member_id")int entry_member_id,
			@RequestParam(value="entry_time")Timestamp entry_time) {
		Record entity = new Record();
		entity.setRecord_date(record_date);
		entity.setLocation_id(location_id);
		entity.setExit_member_id(exit_member_id);
		entity.setExit_time(exit_time);
		entity.setCreated_time(created_time);
		entity.setEntry_member_id(entry_member_id);
		entity.setEntry_time(entry_time);
		recordService.registerRecord(entity);
		return new ModelAndView("redirect:/");
	}

}
