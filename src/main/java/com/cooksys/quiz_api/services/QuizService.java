package com.cooksys.quiz_api.services;

import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;

public interface QuizService {

  // Get All Quizzes
  List<QuizResponseDto> getAllQuizzes();

  // Get Quiz By ID
  QuizResponseDto getQuizById(Long quizID);

  // Get Random Question
  QuestionResponseDto getRandomQuestion(Long quizID);

  // Delete Quiz by ID
  QuizResponseDto deleteQuizById(Long quizID);

  // Delete Quiz Question by ID
  QuestionResponseDto deleteQuestion(Long quizID, Long questionID);

  // Quiz Create Quiz
  QuizResponseDto createQuiz(QuizResponseDto quiz);

  // Patch Rename Quiz
  //QuizResponseDto renameQuiz(Long quizID, String newName);






  /*


   */
}
