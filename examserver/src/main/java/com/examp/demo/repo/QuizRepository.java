package com.examp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examp.demo.entity.exam.Category;
import com.examp.demo.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
	
	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c,Boolean b);

}
