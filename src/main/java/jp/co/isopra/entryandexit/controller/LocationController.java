package jp.co.isopra.entryandexit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		Iterable<Location> list = locationRepository.findAllOrderById();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value="/locationNew", method=RequestMethod.GET)
	public ModelAndView locationNew(@ModelAttribute("formModel")Location location,
			ModelAndView mav) {
		mav.setViewName("locationEdit");
		mav.addObject("check",true);
		mav.addObject("msg", "新規登録");
		return mav;
	}

	@RequestMapping(value="/locationNew", method=RequestMethod.POST)
	public ModelAndView regist(
			@ModelAttribute @Validated Location location,
			BindingResult result,
			ModelAndView mav) {
		if(!result.hasErrors()) {
			locationRepository.saveAndFlush(location);
			mav = new ModelAndView("redirect:/location");
		}else {
			for(ObjectError error:result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			mav.setViewName("location");
		}
		Iterable<Location> list = locationRepository.findAll();
		mav.addObject("list",list);
		return mav;
	}

	@RequestMapping(value = "/locationEdit",method = RequestMethod.GET)
	public ModelAndView send(@ModelAttribute Location location,
			ModelAndView mav ,
			@RequestParam(value="location")int locationint) {
		mav.setViewName("locationEdit");
		mav.addObject("check",false);
		mav.addObject("msg",locationint);
		Optional<Location> dataOptional = locationRepository.findById(locationint);
		mav.addObject("formModel",dataOptional.get());
		return mav;
	}

	@RequestMapping(value = "/locationEdit" , method = RequestMethod.POST)
	public ModelAndView editUpdate(@ModelAttribute Location location,
			ModelAndView mav) {

		locationRepository.saveAndFlush(location);
		return new ModelAndView("redirect:/location");
	}
}
