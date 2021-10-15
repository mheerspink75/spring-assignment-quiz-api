package com.cooksys.quiz_api.services;

import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;

public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();

  QuizResponseDto getQuizById(Long quizID);

  QuizResponseDto deleteQuizById(Long quizID);

  QuestionResponseDto getRandomQuestion(Long id);
}
