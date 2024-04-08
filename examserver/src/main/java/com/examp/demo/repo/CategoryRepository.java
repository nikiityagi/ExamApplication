package com.examp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examp.demo.entity.exam.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
