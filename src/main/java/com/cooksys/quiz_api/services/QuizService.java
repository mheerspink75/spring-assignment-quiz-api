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

  // Get Question by ID
  QuizResponseDto getQuestionsById(Long quizID);


  /*
  QuizResponseDto deleteQuizById(Long quizID);

  QuizResponseDto getRandomQuestion(Long quizID);



   */
}
