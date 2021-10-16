package com.cooksys.quiz_api.services;

import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import org.springframework.http.ResponseEntity;

public interface QuizService {

  // Get All Quizzes
  List<QuizResponseDto> getAllQuizzes();

  // Post Create Quiz
  QuizResponseDto createQuiz(QuizResponseDto quiz);

  // Get Quiz By ID
  ResponseEntity<QuizResponseDto> getQuizById(Long quizID);

  // Get Random Question by ID
  QuestionResponseDto getRandomQuestion(Long quizID);

  // Delete Quiz by ID
  ResponseEntity<QuizResponseDto> deleteQuizById(Long quizID);

  // Delete Quiz Question by ID
  ResponseEntity<QuestionResponseDto> deleteQuestion(Long quizID, Long questionID);

  // Patch Quiz Rename Quiz
  QuizResponseDto renameQuiz(Long quizID, String newName);

  // Patch Quiz Add Question
  QuizResponseDto addQuestionToQuiz(Long quizID, Question question);

}
