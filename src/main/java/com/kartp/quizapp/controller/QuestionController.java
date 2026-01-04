package com.kartp.quizapp.controller;

import com.kartp.quizapp.model.Question;
import com.kartp.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired  //to initialize the below object automatically
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        List<Question> questionList= questionService.getAllQuestions();
        for (Question que:questionList){
            //que.setOption1("sffg");
            System.out.println(que.toString());
        }
        return questionList;
    }

    @GetMapping("category/{cat}")  //@PathVariable passes the {category} value to the String in the parameter
    public List<Question> getQuestionByCategory(@PathVariable("cat") String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
