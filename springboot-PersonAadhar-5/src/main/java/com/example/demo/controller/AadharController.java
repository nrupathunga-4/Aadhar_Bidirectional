package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundExcpetion;
import com.example.demo.model.AadharCard;
import com.example.demo.service.AadharService;

@RestController
public class AadharController {

	@Autowired
	private AadharService aadharService;
	
	@PostMapping("/save")
	public ResponseEntity<AadharCard> saveAadhar(@RequestBody   AadharCard aadharCard)
	{
		return new ResponseEntity<AadharCard>(aadharService.saveAadhar(aadharCard),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{adharnumber}")
	public ResponseEntity<AadharCard> getPersonByAdharnumber(@PathVariable String adharnumber)
	{
		Optional<AadharCard> aadhar=aadharService.getPersonByAdharnumber(adharnumber);
		return aadhar.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@PutMapping("/update/{adharnumber}")
	public ResponseEntity<AadharCard> updateAadhar(@RequestBody  AadharCard aadharCard,@PathVariable String adharnumber) throws UserNotFoundExcpetion
	{
		return new ResponseEntity<AadharCard>(aadharService.updateAadhar(aadharCard, adharnumber),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{adharnumber}")
	public ResponseEntity<String> deleteAadhar(@PathVariable  String adharnumber) throws UserNotFoundExcpetion
	{
		aadharService.deleteAadhar(adharnumber);
		return new ResponseEntity<String>("Aadhar Deleted from Database",HttpStatus.OK);

	}
}
