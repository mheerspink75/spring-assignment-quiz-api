package com.cooksys.quiz_api.controllers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.services.QuizService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;


  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }
  
  // TODO: Implement the remaining 6 endpoints from the documentation.

  // Post Quiz: Creates a quiz and adds it to the collection - Working
  @PostMapping
  public QuizResponseDto createQuiz(@RequestBody QuizResponseDto quiz) {
    return quizService.createQuiz(quiz);
  }

  // Get Quiz: Gets the specified quiz from the collection - Working
  @GetMapping("/{id}")
  public ResponseEntity<QuizResponseDto> getQuizById(@PathVariable("id") Long quizID) {
    return quizService.getQuizById(quizID);
  }

  // Get Random Question: Gets random quiz question by ID - Working
  @GetMapping("{id}/random")
  public QuestionResponseDto getRandomQuestion(@PathVariable("id") Long quizID) {
    return quizService.getRandomQuestion(quizID);
  }

  // Delete Quiz: Deletes the specified quiz from the collection - Working
  @DeleteMapping("/{id}")
  public ResponseEntity<QuizResponseDto> deleteQuizById(@PathVariable("id") Long quizID){
    return quizService.deleteQuizById(quizID);
  }

  //Delete: Deletes the specified question from the specified quiz - Working
  @DeleteMapping("/{id}/delete/{questionID}")
  public ResponseEntity<QuestionResponseDto> deleteQuestion(@PathVariable("id") Long quizID, @PathVariable("questionID") Long questionID ) {
    return quizService.deleteQuestion(quizID, questionID);
  }

  // Patch Quiz: Rename the specified quiz using the name given - Working
  @PatchMapping("/{id}/rename/{newName}")
  public QuizResponseDto renameQuiz(@PathVariable("id") Long quizID,@PathVariable("newName") String newName) {
    return quizService.renameQuiz(quizID, newName);
  }

  // Patch Quiz: Adds a question to the specified quiz - Working
  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestionToQuiz(@PathVariable("id") Long quizID, @RequestBody Question question) {
    return quizService.addQuestionToQuiz(quizID, question);
  }

}
