 package com.examp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examp.demo.entity.exam.Category;
import com.examp.demo.entity.exam.Quiz;
import com.examp.demo.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	//add quiz
		@PostMapping("/")
		public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
		{
			Quiz quiz1=this.quizService.addQuiz(quiz);
			return ResponseEntity.ok(quiz1);
		}
		
		//get quiz by id
		@GetMapping("/{qId}")
		public Quiz getQuiz(@PathVariable("qId") Long qId)
		{
			return this.quizService.getQuiz(qId);
		}
		
		//get all quiz 
		@GetMapping("/getall")
		public ResponseEntity<?> getQuizes()
		{
			return ResponseEntity.ok(this.quizService.getQuizzes());
		}
		
		//update quiz
		@PutMapping("/update")
		public ResponseEntity<Quiz> update(@RequestBody Quiz quiz)
		{
			return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
		}

		//delete quiz
		@DeleteMapping("/{qId}")
		public void deleteQuiz(@PathVariable("qId") Long qId)
		{
			this.quizService.deleteQuiz(qId);
		}
		
		@GetMapping("/category/{cid}")
		public List<Quiz> getQuizzesOfCategory(@PathVariable("cid")Long cid)
		{
			Category category=new Category();
			category.setCid(cid);
			return this.quizService.getQuizzesOfCategory(category);
		}
		
		//get active quizzes
		@GetMapping("/active")
		public List<Quiz> getActiveQuizzes()
		{
			return this.quizService.getActiveQuizzes();
		}
		
		//get active quizzes of category
		@GetMapping("/category/active/{cid}")
		public List<Quiz> getActiveQuizzesByCategory(@PathVariable("cid")Long cid)
		{
			Category category=new Category();
			category.setCid(cid);
			return this.quizService.getActiveQuizzesOfCategory(category);
		}
	

}
