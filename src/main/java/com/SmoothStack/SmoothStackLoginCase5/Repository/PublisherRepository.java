package com.SmoothStack.SmoothStackLoginCase5.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;

@Repository
@Transactional
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	
	Page<Publisher> findAll(Pageable pageRequest);

}

