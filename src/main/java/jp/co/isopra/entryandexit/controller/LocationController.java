package jp.co.isopra.entryandexit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.repositories.LocationRepository;

@Controller
public class LocationController {

	@Autowired
	LocationRepository locationRepository;

	@RequestMapping("/location")
	public ModelAndView location(@ModelAttribute("formModel")Location tmp, ModelAndView mav) {
		mav.setViewName("location");
		Iterable<Location> list = locationRepository.findAll();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value="/locationNew", method=RequestMethod.GET)
	public ModelAndView locationNew(ModelAndView mav) {
		mav.setViewName("locationEdit");
		mav.addObject("msg", "自動採番");
		return mav;
	}

	@RequestMapping(value="/locationNew", method=RequestMethod.POST)
	public ModelAndView regist() {
		return new ModelAndView();
	}

	@RequestMapping("/locationEdit")
	public String locationEdit() {
		return "locationEdit";
	}
}
