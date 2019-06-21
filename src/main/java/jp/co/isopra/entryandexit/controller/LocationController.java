package jp.co.isopra.entryandexit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.service.LocationService;

@Controller
public class LocationController {

	@Autowired
	private LocationService service;

	@RequestMapping("/location")
	public ModelAndView location(
			ModelAndView mav) {

		mav.setViewName("location");
		List<Location> list = service.getAll();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value="/locationNew", method=RequestMethod.GET)
	public ModelAndView locationNew(
			@ModelAttribute("formModel")Location location,
			ModelAndView mav) {

		mav.setViewName("locationEdit");
		mav.addObject("check",true);
		mav.addObject("msg", "新規登録");
		return mav;
	}

	@RequestMapping(value="/locationNew", method=RequestMethod.POST)
	public ModelAndView regist(
			@RequestParam("name")String name,
			ModelAndView mav) {

		Location entity = new Location();
		entity.setName(name);
		service.registerLocation(entity);
		return new ModelAndView("redirect:/location");
	}

	@RequestMapping(value = "/locationEdit",method = RequestMethod.GET)
	public ModelAndView send(
			@ModelAttribute("formModel") Location location,
			@RequestParam(value="location")int pLocation,
			ModelAndView mav) {

		mav.setViewName("locationEdit");
		mav.addObject("check",false);
		mav.addObject("msg",pLocation);
		Location dataOptional = service.get(pLocation);
		mav.addObject("formModel",dataOptional);
		return mav;
	}

	@RequestMapping(value = "/locationEdit" , method = RequestMethod.POST)
	public ModelAndView editUpdate(
			@ModelAttribute("formModel") Location location,
			@RequestParam(value="location_id") int location_id,
			@RequestParam(value="name")String name,
			ModelAndView mav) {

		Location entity = new Location();
		entity.setLocation_id(location_id);
		entity.setName(name);
		service.registerLocation(entity);
		return new ModelAndView("redirect:/location");
	}

	@RequestMapping(value="/locationDelete", method=RequestMethod.GET)
	public ModelAndView locationDelete(
			@ModelAttribute("formModel") Location location,
			@RequestParam(value="location")int pLocation,
			ModelAndView mav) {
		mav.setViewName("locationDelete");
		mav.addObject("msg",pLocation);
		Location dataOptional = service.get(pLocation);
		mav.addObject("formModel", dataOptional);
		return mav;
	}

	@RequestMapping(value="/locationDelete", method=RequestMethod.POST)
	public ModelAndView lecationRemove(
			@RequestParam(value="location_id") int location_id,
			@RequestParam(value="name") String name) {

		Location entity = new Location();
		entity.setLocation_id(location_id);
		entity.setName(name);
		entity.setDelete_flag(true);
		service.registerLocation(entity);
		return new ModelAndView("redirect:/location");
	}
}
