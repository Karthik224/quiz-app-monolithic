package com.kartp.quizapp.services;

import com.kartp.quizapp.dao.QuestionDao;
import com.kartp.quizapp.model.Question;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        //questionDao.
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST); //findByCategory can be written directly by you to which JPA will underastand as a row with column "Category" is present
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);     /// here "save" is used for both insert and update.
        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }
}
