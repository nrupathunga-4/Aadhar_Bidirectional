package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundExcpetion;
import com.example.demo.model.AadharCard;
import com.example.demo.model.Person;
import com.example.demo.repository.AadharRepository;
import com.example.demo.repository.PersonRepository;

@Service
public class AadharService {

	@Autowired
	private  PersonRepository personRepository;
	
	@Autowired
	private AadharRepository aadharRepository;
	
	public AadharCard saveAadhar(AadharCard aadharCard)
	{
		return aadharRepository.save(aadharCard);
	}
	
	public  Optional<AadharCard> getPersonByAdharnumber(String adharnumber)
	{
		return aadharRepository.findByAdharnumber(adharnumber);
	}
	
	public AadharCard updateAadhar(AadharCard aadharCard,String adharnumber) throws UserNotFoundExcpetion
	{
	  AadharCard aadharCard2=aadharRepository.findByAdharnumber(adharnumber).orElseThrow(()->new UserNotFoundExcpetion("User Doesn't Exist in Database"));
	  aadharCard2.setAdharaadress(aadharCard.getAdharaadress());
	   
	  aadharRepository.save(aadharCard2);
	  return aadharCard2;
	}
	
	public void deleteAadhar(String aadharnumber) throws UserNotFoundExcpetion
	{
		aadharRepository.findByAdharnumber(aadharnumber).orElseThrow(()->new UserNotFoundExcpetion("user Doesn't Exist in the Database"));
		aadharRepository.deleteById(aadharnumber);
	}
}
