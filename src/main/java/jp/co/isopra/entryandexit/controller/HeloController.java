package jp.co.isopra.entryandexit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.repositories.LocationRepository;

@Controller
public class HeloController {

	@Autowired
	LocationRepository locationRepository;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/faceCapture")
	public String faceCapture() {
		return "faceCapture";
	}

	@RequestMapping("/recordDetail")
	public ModelAndView recordDetail(ModelAndView mav,
			@ModelAttribute Location location) {
		mav.setViewName("recordDetail");
		Iterable<Location> list =  locationRepository.findAllOrderById();
		mav.addObject("list",list);
		return mav;
	}

}