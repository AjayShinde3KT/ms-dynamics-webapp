package com.ms.dynamics.webapp.service.impl;

import com.ms.dynamics.webapp.exception.ResourceNotFoundException;
import com.ms.dynamics.webapp.model.Answer;
import com.ms.dynamics.webapp.model.Question;
import com.ms.dynamics.webapp.repository.AnswerRepository;
import com.ms.dynamics.webapp.repository.QuestionRepository;
import com.ms.dynamics.webapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question saveQuestionWithAnswers(Question question) {
        List<Answer> savedAnswers = null;
        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            // Save the answers if they are provided
            savedAnswers = answerRepository.saveAll(question.getAnswers());
        }
        // Update the question with saved answer entities
        question.setAnswers(savedAnswers);
        return questionRepository.save(question);
    }


    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        // Retrieve all questions along with their associated answers
        return questionRepository.findAll();
    }

    @Override
    public void deleteQuestionWithAnswers(Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question not found with id: " + questionId)
        );
        questionRepository.delete(existingQuestion);
    }

    @Override
    public Question updateQuestionWithAnswers(Long id, Question updatedQuestion) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));

        // Update question properties (if needed)
        existingQuestion.setQuestion(updatedQuestion.getQuestion());
        existingQuestion.setType(updatedQuestion.getType());
        existingQuestion.setEdit_question(updatedQuestion.isEdit_question());

        // Get the new list of answers from the updated question
        List<Answer> updatedAnswers = updatedQuestion.getAnswers();

        // Delete the existing answers from the database
       List<Answer> existingAnswers = existingQuestion.getAnswers();

        // Add the new answers to the existing list
        existingAnswers.clear();
        existingAnswers.addAll(updatedAnswers);

        // Save the updated question (cascades changes to the answers)
        return questionRepository.save(existingQuestion);
    }

}
