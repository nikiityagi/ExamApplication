package com.examp.demo.service;

import java.util.Set;

import com.examp.demo.entity.exam.Questions;
import com.examp.demo.entity.exam.Quiz;

public interface QuestionsService {
	
	public Questions addQuestions(Questions question);
	
	public Questions updateQuestions(Questions question);
	
	public Set<Questions> getQuestions();
	
	public Questions getQuestion(Long questionId);
	
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quesId);
	
	public Questions get(Long questionsId);

}
