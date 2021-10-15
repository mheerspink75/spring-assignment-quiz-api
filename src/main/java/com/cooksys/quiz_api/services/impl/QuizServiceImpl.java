package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final QuestionMapper questionMapper;


  @Override
  // Get All Quizzes
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }

  @Override
  // Get Quiz By ID
  public QuizResponseDto getQuizById(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);
    return quizMapper.entityToDto(quiz);
  }

  @Override
  // Get Random Question
  public QuestionResponseDto getRandomQuestion(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);

    return null;
  }


  /*

  @Override
  // Delete Quiz By ID - Need to implement
  public QuizResponseDto deleteQuizById(Long quizID) {
    //return quizMapper.entityToDto(quizRepository.getById(quizID));
    return null;
  }

  @Override
  // Get a random question - Need to implement
  public QuestionResponseDto getRandomQuestion(Long id) {
   // Quiz quiz = quizRepository.findById(id);
    // return quizMapper.entityToDto(quizRepository.getById(id));
    return null;
  }
  */
}
