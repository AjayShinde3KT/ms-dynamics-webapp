package com.ms.dynamics.webapp.repository;

import com.ms.dynamics.webapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
