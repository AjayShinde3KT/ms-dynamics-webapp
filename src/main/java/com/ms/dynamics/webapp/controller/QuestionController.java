package com.ms.dynamics.webapp.controller;

import com.ms.dynamics.webapp.model.Question;
import com.ms.dynamics.webapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Question saveQuestion(@RequestBody Question question) {
        return questionService.saveQuestionWithAnswers(question);
    }

    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestParam("id") Long id, @RequestBody Question updatedQuestion) {
        Question savedQuestion = questionService.updateQuestionWithAnswers(id, updatedQuestion);
        return ResponseEntity.ok(savedQuestion);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestionsWithAnswers();
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteQuestion(@RequestParam("id") Long id) {
        questionService.deleteQuestionWithAnswers(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
