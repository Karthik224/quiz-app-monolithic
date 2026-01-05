package com.kartp.quizapp.services;
import com.kartp.quizapp.dao.QuestionDao;
import com.kartp.quizapp.dao.QuizDao;
import com.kartp.quizapp.model.Question;
import com.kartp.quizapp.model.QuestionWrapper;
import com.kartp.quizapp.model.Quiz;
import com.kartp.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQue, String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);

        // will get questions to set from DB
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQue);

        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("SUCCESS table created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
        Optional<Quiz> quiz= quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionFromDB){
            QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> resp) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int score=0,i=0;
        for(Response response:resp){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                score=score+1;
            i++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
