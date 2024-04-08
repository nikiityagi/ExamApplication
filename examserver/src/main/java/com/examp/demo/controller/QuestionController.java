package com.examp.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

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

import com.examp.demo.entity.exam.Questions;
import com.examp.demo.entity.exam.Quiz;
import com.examp.demo.service.QuestionsService;
import com.examp.demo.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionsService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add questions
	@PostMapping("/")
	public ResponseEntity<Questions> add(@RequestBody Questions questions)
	{
		return ResponseEntity.ok(this.questionService.addQuestions(questions));
	}
	
	//update the question
	@PutMapping("/update")
	public ResponseEntity<Questions> update(@RequestBody Questions questions)
	{
		return ResponseEntity.ok(this.questionService.updateQuestions(questions));
	}
	
	//get all questions of any qid
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid)
	{
//		Quiz quiz=new Quiz();
//		quiz.setqId(qid);
//		Set<Questions> questionsOfQuiz=this.questionService.getQuestionsOfQuiz(quiz);
//		return ResponseEntity.ok(questionsOfQuiz);
		
		 Quiz quiz=this.quizService.getQuiz(qid);
		 Set<Questions> questions=quiz.getQuestions();
		 List<Questions> list=new ArrayList(questions);
		 if((list.size()) > Integer.parseInt(quiz.getNumberOfQuestions()))
		 {
			 list=(ArrayList) list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
		 }
		 
		 list.forEach((q)->{
			 q.setAnswer("");
		 });
		 
		 Collections.shuffle(list);
		 return ResponseEntity.ok(list);
	  }
	
	//get all questions of any qid of ADMIN
		@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid)
	{
		Quiz quiz=new Quiz();
		quiz.setqId(qid);
		Set<Questions> questionsOfQuiz=this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);
		
	//	return ResponseEntity.ok(list);
	  }
	
	
	//get single questions
	@GetMapping("/{quesId}")
	public Questions get(@PathVariable("quesId")Long quesId)
	{
		return this.questionService.getQuestion(quesId);
	}
	
	//delete questions
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId)
	{
		this.questionService.deleteQuestion(quesId);
	}
	
	//eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Questions> questions)
	{
		System.out.println(questions);
		double marksGot=0;
		int correctAnswer=0;
		int attempted=0;
		for(Questions q:questions){
			//single questions
			Questions question=this.questionService.get(q.getQuesId());
			if(question.getAnswer().equals(q.getGivenAnswer()))
			{
				//correct
				correctAnswer++;
				double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size() ;
						//this.questions[0].quiz.maxMarks/this.questions.length;
						marksGot+=marksSingle;
			}
			if(q.getGivenAnswer()!=null)
			{
				attempted++;
			}
			//System.out.println(q.getGivenAnswer());
		}
		
		Map<String,Object> map=Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
	

}
