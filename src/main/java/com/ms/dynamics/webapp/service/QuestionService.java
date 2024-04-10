package com.ms.dynamics.webapp.service;

import com.ms.dynamics.webapp.model.Question;

import java.util.List;

public interface QuestionService {

    public Question saveQuestionWithAnswers(Question question);

    public Question updateQuestionWithAnswers(Long id, Question updatedQuestion);

    public List<Question> getAllQuestionsWithAnswers();

    public void deleteQuestionWithAnswers(Long questionId);



}
