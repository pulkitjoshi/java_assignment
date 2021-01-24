package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.BloodDonationCampRegistration;
import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.CampInfo;
import com.example.demo.entity.Filter;

@Controller
public class BloodDonorController {

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private ModelAndView modelAndView;
	
	@Autowired
	private BloodDonor bloodDonor;
	
	@Autowired
	private Filter filter;
	
	@Autowired
	private CampInfo campInfo;
	
	@Autowired
	private BloodDonationCampRegistration bloodDonationCampRegistration;
	
	
	@GetMapping(path = "/showdonor")
	public ModelAndView showAll(Filter filter) {
		
		modelAndView.setViewName("showDonor");
		modelAndView.addObject("command", filter);
		
		BloodDonor[] donorListByEligibilty=null;
		if(filter.getEligibilty().equals("all"))
		{
			donorListByEligibilty= allDonor();
		}
		else
		{
			donorListByEligibilty= eligibleDonor();
		}
		
		BloodDonor[] cityDonor= cityDonor(filter.getDonorCity());
		BloodDonor[] bloodGroup= bloodGroup("b+");
		
		
		
		 List<BloodDonor> listByEligibilty = Arrays.asList(donorListByEligibilty); 
		 List<BloodDonor> cityDonorList = Arrays.asList(cityDonor);
		 List<BloodDonor> bloodGroupList = Arrays.asList(bloodGroup);
		 Set<BloodDonor> resultList = listByEligibilty.stream()  .distinct()   .collect(Collectors.toSet()); 
		
		 if(filter.getDonorCity()!=null&&filter.getDonorCity()!="")
		 {
			resultList = listByEligibilty.stream()  .distinct()  .filter(cityDonorList::contains)  .collect(Collectors.toSet());
	       
			if(filter.getDonorBloodGroup()!=null&&filter.getDonorBloodGroup()!="")
			{
				
				resultList = resultList.stream()  .distinct()  .filter(bloodGroupList::contains)  .collect(Collectors.toSet());
			}
		 }
		 
		
		 else if(filter.getDonorBloodGroup()!=null&&filter.getDonorBloodGroup()!="")
				{
			 
					
					resultList = resultList.stream()  .distinct()  .filter(bloodGroupList::contains)  .collect(Collectors.toSet());
				}
			 
		
		 
		 
		
		
	   this.modelAndView.addObject("donor",resultList);
	
		/*for (BloodDonor var : resultList) 
		{ 
		    System.out.println(var);;
		}*/
	
		
		
		return this.modelAndView;
		
	}
	
	
	@GetMapping(path = "/donor")
	public ModelAndView init() {
		modelAndView.setViewName("addBloodDonor");
		modelAndView.addObject("command", bloodDonor);
		
		return modelAndView;
	}
	
	@PostMapping(path = "/submit")
	public ModelAndView onSubmit(@ModelAttribute("command") BloodDonor donor ) {
		
		modelAndView.setViewName("success");
		BloodDonor added = this.template.postForObject("http://BLOOD-BANK-SERVICE/api/v1/donor", donor, BloodDonor.class);
		modelAndView.addObject("added","Thank You!");
		return modelAndView;
}
	
	
	@GetMapping(path = "/updatedonor")
	public ModelAndView update() {
		modelAndView.setViewName("updateDonorDetails");
		modelAndView.addObject("command", bloodDonor);
		
		return modelAndView;
	}
	
	@PostMapping(path = "/update")
	public ModelAndView onUpdate(@ModelAttribute("command") BloodDonor donor ) {
		System.out.println("hello"+donor);
		modelAndView.setViewName("success");
		this.template.put("http://BLOOD-BANK-SERVICE/api/v1/updatedonor", donor);
		modelAndView.addObject("added","Thank You!");
		return modelAndView;
}
	
	
	@GetMapping(path="/delete")
	public ModelAndView setDeleteDonorView() {
		modelAndView.setViewName("deleteDonor");
		modelAndView.addObject("command",bloodDonor);
		return modelAndView;
	}
	
	
	@PostMapping(path="/deletedonor")
	public ModelAndView deleteDonor(@ModelAttribute("command") BloodDonor donor) {
		boolean isDonorExist = this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/donorExist/"+donor.getDonorName()+"/"+donor.getDonorMobileNumber(), Boolean.class);
		
		if(isDonorExist) {
			modelAndView.setViewName("success");
			System.out.println("http://BLOOD-BANK-SERVICE/api/v1/donors/"+donor.getDonorName()+"/"+donor.getDonorMobileNumber());
			this.template.delete("http://BLOOD-BANK-SERVICE/api/v1/donor/"+donor.getDonorName()+"/"+donor.getDonorMobileNumber());
			modelAndView.addObject("added","Thank You For Your Services!");
			
		}
		else
		{
			modelAndView.setViewName("success");
			modelAndView.addObject("added","Not Registered Yet!");
		}
		
		
		return modelAndView;
	}
	
	
	
	
	
	
	private BloodDonor[] allDonor() {
		
		BloodDonor[] donor= this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/showDonor/",BloodDonor[].class);
	
		return donor;
	     
	}
	

	 private BloodDonor[] cityDonor(String city) {
		
		BloodDonor[] donor= this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/showDonor/"+city,BloodDonor[].class);
	
		return donor;
	     
	}
	 
	 private BloodDonor[] bloodGroup(String group) {
			
			BloodDonor[] donor= this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/showDonorByGroup/"+group,BloodDonor[].class);
		
			return donor;
		     
		}
	 
	 private BloodDonor[] eligibleDonor() {
			
			BloodDonor[] donor= this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/showDonorByEligibility/",BloodDonor[].class);
		
			return donor;
		     
		}
	
	
	
	@PostMapping(path = "/filter")
	
	public ModelAndView dataFilter(@ModelAttribute("command" ) Filter filter ) {
		
	return showAll(filter);
}

	//=======================camp==============================================
	
	
	@GetMapping(path = "/showCamp")
	public ModelAndView showCamp(CampInfo campInfo) {
		
		modelAndView.setViewName("showCamp");
		modelAndView.addObject("command", campInfo);
		
		
		CampInfo[] camps= this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/showCamp/",CampInfo[].class);
		
	
		
	   this.modelAndView.addObject("camps",camps);
	
		for (CampInfo var : camps) 
		{ 
		    System.out.println(var);;
		}
	
		
		
		return this.modelAndView;
		
	}
	
   @PostMapping(path = "/registerCampId")
	
	public ModelAndView registerCampId(@ModelAttribute("command" ) CampInfo campInfo ) {
		
	return campDonorRegistration(campInfo.getCampId());
}
	
	
	private ModelAndView campDonorRegistration(String campId) {
		
		modelAndView.setViewName("campRegistration");
		modelAndView.addObject("command", bloodDonationCampRegistration);
		modelAndView.addObject("campId",campId);
		return modelAndView;
	}
	
	@PostMapping(path = "/campDonorRegistration")
	public ModelAndView oncampDonorRegistration(@ModelAttribute("command") BloodDonationCampRegistration bloodDonationCampRegistration ) {
		
		modelAndView.setViewName("success");
		BloodDonationCampRegistration added = this.template.postForObject("http://BLOOD-BANK-SERVICE/api/v1/campregister", bloodDonationCampRegistration, BloodDonationCampRegistration.class);
		modelAndView.addObject("added","Thank You!");
		return modelAndView;
}
	
	
	
	 @PostMapping(path = "/viewCampId")
		
		public ModelAndView viewCampId(@ModelAttribute("command" ) CampInfo campInfo ) {
			
		return viewCampRegistration(campInfo.getCampId());
	}
	 
	
		private ModelAndView viewCampRegistration(String campId) {
			
			modelAndView.setViewName("showCampRegistration");
			
			
			
			BloodDonationCampRegistration[] campRegistration= this.template.getForObject("http://BLOOD-BANK-SERVICE/api/v1/showCampRegistration/"+campId,BloodDonationCampRegistration[].class);
			
		
			
		   this.modelAndView.addObject("campRegistration",campRegistration);
		
			
			
			
			return this.modelAndView;
			
		}
	
	
}