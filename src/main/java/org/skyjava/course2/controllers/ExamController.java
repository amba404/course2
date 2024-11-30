package org.skyjava.course2.controllers;

import org.skyjava.course2.domains.Answer;
import org.skyjava.course2.domains.Question;
import org.skyjava.course2.interfaces.ExaminerService;
import org.skyjava.course2.interfaces.QuestionService;
import org.skyjava.course2.services.ExaminerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> get(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

    @GetMapping("/{theme}/add")
    public Question add(@PathVariable
                        String theme,
                        @RequestParam(name = "question", required = false)
                        String question,
                        @RequestParam(name = "answer", required = false)
                        String answer,
                        @RequestParam(name = "correct", required = false, defaultValue = "false")
                        boolean correct) {
        QuestionService questionService = examinerService.getExaminerService(theme);
        return questionService.add(question, new Answer(answer, correct));
    }

    @GetMapping("/{theme}/find")
    public Question find(@PathVariable String theme,
                         @RequestParam(name = "question", required = false) String question,
                         @RequestParam(name = "answer", required = false) String answer,
                         @RequestParam(name = "id", required = false) Long id) {
        QuestionService questionService = examinerService.getExaminerService(theme);
        if (id != null) {
            return questionService.find(id);
        } else {
            return questionService.find(question, answer);
        }
        //"find";
    }

    @GetMapping("/{theme}/remove")
    public Question remove(@PathVariable String theme,
                           @RequestParam(name = "question", required = false) String question,
                           @RequestParam(name = "answer", required = false) String answer,
                           @RequestParam(name = "id", required = false) Long id) {
        QuestionService questionService = examinerService.getExaminerService(theme);
        if (id != null) {
            return questionService.remove(id);
        } else {
            return questionService.remove(question, answer);
        }
    }

    @GetMapping("/{theme}")
    public Collection<Question> getAll(@PathVariable String theme) {
        QuestionService questionService = examinerService.getExaminerService(theme);
        return questionService.getAll();
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleException_405(UnsupportedOperationException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException_400(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
