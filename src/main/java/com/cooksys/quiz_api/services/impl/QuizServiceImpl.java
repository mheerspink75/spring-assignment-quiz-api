package com.cooksys.quiz_api.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
  private final QuestionMapper questionMapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  @Override
  // Get All Quizzes
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }



  @Override
  // Post Create new Quiz - Completed
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

  @Override
  // Get Quiz By ID - Working
  public QuizResponseDto getQuizById(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);
      return quizMapper.entityToDto(quiz);
  }

  @Override
  // Get Random Question - Completed
  public QuestionResponseDto getRandomQuestion(Long quizID) {
    Quiz quiz = quizRepository.getById(quizID);
    if (quiz != null) {
      Random random = new Random();
      int randomQuestion = random.nextInt(quiz.getQuestions().size());
      return questionMapper.entityToDto(quiz.getQuestions().get(randomQuestion));
    }
    return null;
  }

  @Override
  // Delete Quiz By ID - Completed
  public QuizResponseDto deleteQuizById(Long quizID) {
    Optional<Quiz> quizResponse = quizRepository.findById(quizID);
    if (quizResponse.isPresent()) {
      Quiz quiz = quizResponse.get();
      for (Question question : quiz.getQuestions()) {
        answerRepository.deleteAll(question.getAnswers());
      }
      questionRepository.deleteAll(quiz.getQuestions());
      quizRepository.delete(quiz);
      return quizMapper.entityToDto(quiz);
    }
    return null;
  }

  @Override
  // Delete Quiz Question by ID - Completed
  public QuestionResponseDto deleteQuestion(Long quizID, Long questionID) {
    Optional<Question> questionResponse = questionRepository.findById(questionID);
    if (questionResponse.isPresent()) {
      Question question = questionResponse.get();
      answerRepository.deleteAll(question.getAnswers());
      questionRepository.delete(question);
      return questionMapper.entityToDto(question);
    }
    return null;
  }

  @Override
  // Patch Rename Quiz - Completed
  public QuizResponseDto renameQuiz(Long quizID, String newName) {
    Optional<Quiz> quizResponse = quizRepository.findById(quizID);
    if (quizResponse.isPresent()) {
      Quiz quiz = quizResponse.get();
      quiz.setName(newName);
      quizRepository.save(quiz);
      return quizMapper.entityToDto(quiz);
    }
    return null;
  }

  @Override
  // Patch Add Question to Quiz - Need to Implement
  public QuizResponseDto addQuestionToQuiz(Long quizID, Question question) {
    Optional<Quiz> quizResponse = quizRepository.findById(quizID);
    if (quizResponse.isPresent()) {
      Quiz quiz = quizResponse.get();
      question.setQuiz(quiz);
      quiz.getQuestions().add(question);
      for (Answer answer : question.getAnswers()) {
        answer.setQuestion(question);
      }
      questionRepository.save(question);
      answerRepository.saveAll(question.getAnswers());
      quizRepository.save(quiz);
      return quizMapper.entityToDto(quiz);
    }
    return null;
  }

}
