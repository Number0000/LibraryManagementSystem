package com.SmoothStack.SmoothStackLoginCase5.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	Page<Author> findAll(Pageable pageRequest);
		
}

