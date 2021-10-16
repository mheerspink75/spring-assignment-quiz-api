package com.cooksys.quiz_api.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.services.QuizService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  @Override
  // Get All Quizzes
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }

  @Override
  // Get Quiz By ID - Working
  public QuizResponseDto getQuizById(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);
    return quizMapper.entityToDto(quiz);
  }

  @Override
  // Get Random Question - Need to Implement`
  public QuestionResponseDto getRandomQuestion(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);
    return (QuestionResponseDto) quizMapper.entitiesToDtos((List<Quiz>) quiz);
  }

  @Override
  // Delete Quiz By ID - Need to implement
  public QuizResponseDto deleteQuizById(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);
    quizRepository.deleteById(quizID);
    return quizMapper.entityToDto(quiz);
  }


  @Override
  // Post Create new Quiz
  public QuizResponseDto createQuiz(QuizResponseDto quiz) {
    Quiz q = quizRepository.saveAndFlush(quizMapper.dtoToEntity(quiz));
    for (Question question : q.getQuestions()) {
      question.setQuiz(q);
      questionRepository.saveAndFlush(question);
      for (Answer a : question.getAnswers()) {
        a.setQuestion(question);
      }
      answerRepository.saveAllAndFlush(question.getAnswers());
    }
    return quizMapper.entityToDto(quizRepository.saveAndFlush(quizMapper.dtoToEntity(quiz)));

  }




}
