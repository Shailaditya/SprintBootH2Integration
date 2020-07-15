package com.shail.h2integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shail.h2integration.dto.UserVO;
import com.shail.h2integration.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<UserVO> get(@PathVariable Long id){
		return userService.exists(id)?ResponseEntity.ok(userService.get(id)) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<UserVO> create(@RequestBody UserVO userVO){
		return new ResponseEntity<UserVO>(userService.create(userVO),HttpStatus.CREATED);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<UserVO> update(@PathVariable Long id,@RequestBody UserVO userVO){
 		if (userVO.getId() == null || !userService.exists(id) || id!= userVO.getId())
 			return ResponseEntity.badRequest().build();
		return new ResponseEntity<UserVO>(userService.update(userVO),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<UserVO> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
