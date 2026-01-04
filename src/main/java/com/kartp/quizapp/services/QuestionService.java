package com.kartp.quizapp.services;

import com.kartp.quizapp.dao.QuestionDao;
import com.kartp.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
        //questionDao.
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category); //findByCategory can be written directly by you to which JPA will underastand as a row with column "Category" is present
    }

    public String addQuestion(Question question) {
        questionDao.save(question);     /// here "save" is used for both insert and update.
        return "SUCCESS";
    }
}
