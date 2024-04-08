package com.examp.demo.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examp.demo.entity.exam.Questions;
import com.examp.demo.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Questions,Long>{

	Set<Questions> findByQuiz(Quiz quiz);
   
}
