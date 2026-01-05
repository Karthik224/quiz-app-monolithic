package com.kartp.quizapp.controller;

import com.kartp.quizapp.model.Question;
import com.kartp.quizapp.model.QuestionWrapper;
import com.kartp.quizapp.model.Response;
import com.kartp.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @RequestMapping(value = "/create",method= RequestMethod.POST)
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQue
                                                ,@RequestParam String title){
//        return new ResponseEntity<>("all good", HttpStatus.OK);

        return quizService.createQuiz(category,numQue,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> resp){
        return quizService.calculateResult(id,resp);
    }
}
