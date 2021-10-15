package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;


  @Override
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }

  @Override
  public QuizResponseDto getQuizById(Long id) {
    return quizMapper.entityToDto(quizRepository.getById(id));
  }

}
