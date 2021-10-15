package com.cooksys.quiz_api.controllers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;

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


  // Get Quiz: Gets the specified quiz from the collection
  @GetMapping("/{id}")
  public QuizResponseDto getQuizById(@PathVariable("id") Long quizID){
    return quizService.getQuizById(quizID);
  }

  // Get Random Question: Gets random quiz question
  @GetMapping("{id}/random")
  public QuizResponseDto getRandomQuestion(@PathVariable("id") Long quizID ) {
    //return quizService.getRandomQuestion(quizID);
    return null;
  }


/*


  // Post Quiz: Creates a quiz and adds it to the collection
  public QuizResponseDto createQuiz(@RequestBody QuizResponseDto quiz) {
    //return quizService.createQuiz(quiz);
    return null;
  }

  // Delete Quiz: Deletes the specified quiz from the collection
  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuizById(@PathVariable("id") Long quizID){
    return quizService.deleteQuizById(quizID);
  }


  // Patch Quiz: Rename the specified quiz using the name given
  @PatchMapping("/{id}/rename/{newName}")
  public QuizResponseDto renameQuiz(@PathVariable("id") Long quizID) {
    return null;
  }


  // Get Random Question
  @GetMapping("/{id}/random")
  public QuestionResponseDto getRandomQuestion(@PathVariable("id") Long id) {
    //return quizService.getRandomQuestion(id);
    return null;
  }


  // Patch Quiz: Adds a question to the specified quiz
  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestionToQuiz(@PathVariable long id, @RequestBody QuestionRequestDto questionRequestDto) {
    return null;
  }


  //Delete: Deletes the specified question from the specified quiz
  @DeleteMapping("/{id}/delete/{questionID}")
  public QuizResponseDto deleteQuestion(@PathVariable long id, @RequestBody QuestionRequestDto questionRequestDto) {
    return null;
  }

*/
}
