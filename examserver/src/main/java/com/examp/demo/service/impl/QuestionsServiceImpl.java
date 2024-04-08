package com.examp.demo.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examp.demo.entity.exam.Questions;
import com.examp.demo.entity.exam.Quiz;
import com.examp.demo.repo.QuestionRepository;
import com.examp.demo.service.QuestionsService;

@Service
public class QuestionsServiceImpl implements QuestionsService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Questions addQuestions(Questions question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Questions updateQuestions(Questions question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Questions> getQuestions() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Questions getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
		
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		
		Questions questions=new Questions();
		questions.setQuesId(quesId);
		this.questionRepository.delete(questions);
		
		
	}

	@Override
	public Questions get(Long questionsId) {
		return this.questionRepository.getOne(questionsId);
	}

}
