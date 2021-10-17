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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<QuizResponseDto> createQuiz(QuizResponseDto quiz) {
    Quiz q = quizRepository.saveAndFlush(quizMapper.dtoToEntity(quiz));
    for (Question question : q.getQuestions()) {
      question.setQuiz(q);
      questionRepository.saveAndFlush(question);
      for (Answer a : question.getAnswers()) {
        a.setQuestion(question);
      }
      answerRepository.saveAllAndFlush(question.getAnswers());
    }
    quizRepository.saveAndFlush(quizMapper.dtoToEntity(quiz));
    if (quiz.name == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(quizMapper.entityToDto(quizMapper.dtoToEntity(quiz)), HttpStatus.OK);
  }

  @Override
  // Get Quiz By ID - Working
  public ResponseEntity<QuizResponseDto> getQuizById(Long quizID) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(quizID);
    if (optionalQuiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
      return new ResponseEntity<>(quizMapper.entityToDto(optionalQuiz.get()), HttpStatus.OK);
  }

  @Override
  // Get Random Question - Completed
  public ResponseEntity<QuestionResponseDto> getRandomQuestion(Long quizID) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(quizID);
    if (optionalQuiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Quiz quiz = optionalQuiz.get();
    Random random = new Random();
    int randomQuestion = random.nextInt(quiz.getQuestions().size());
    return new ResponseEntity<>(questionMapper.entityToDto(quiz.getQuestions().get(randomQuestion)), HttpStatus.OK);
  }

  @Override
  // Delete Quiz By ID - Completed
  public ResponseEntity<QuizResponseDto> deleteQuizById(Long quizID) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(quizID);
    if (optionalQuiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
      Quiz quiz = optionalQuiz.get();
      for (Question question : quiz.getQuestions()) {
        answerRepository.deleteAll(question.getAnswers());
      }
      questionRepository.deleteAll(quiz.getQuestions());
      quizRepository.delete(quiz);
      return new ResponseEntity<>(quizMapper.entityToDto(quiz), HttpStatus.OK);
  }

  @Override
  // Delete Quiz Question by ID - Completed
  public ResponseEntity<QuestionResponseDto> deleteQuestion(Long quizID, Long questionID) {
    Optional<Question> optionalQuestion = questionRepository.findById(questionID);
    if (optionalQuestion.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
      Question question = optionalQuestion.get();
      answerRepository.deleteAll(question.getAnswers());
      questionRepository.delete(question);
      return new ResponseEntity<>(questionMapper.entityToDto(question), HttpStatus.OK);
  }

  @Override
  // Patch Rename Quiz - Completed
  public ResponseEntity<QuizResponseDto> renameQuiz(Long quizID, String newName) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(quizID);
    if (optionalQuiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
      Quiz quiz = optionalQuiz.get();
      quiz.setName(newName);
      quizRepository.save(quiz);
      return new ResponseEntity<>(quizMapper.entityToDto(quiz), HttpStatus.OK);
  }

  @Override
  // Patch Add Question to Quiz - Completed
  public ResponseEntity<QuizResponseDto> addQuestionToQuiz(Long quizID, Question question) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(quizID);
    if (optionalQuiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
      Quiz quiz = optionalQuiz.get();
      question.setQuiz(quiz);
      quiz.getQuestions().add(question);
      for (Answer answer : question.getAnswers()) {
        answer.setQuestion(question);
      }
      questionRepository.save(question);
      answerRepository.saveAll(question.getAnswers());
      quizRepository.save(quiz);
      return new ResponseEntity<>(quizMapper.entityToDto(quiz), HttpStatus.OK);
  }

}
