package org.skyjava.course2.services;

import org.junit.jupiter.api.Test;
import org.skyjava.course2.domains.Question;
import org.skyjava.course2.interfaces.ExaminerService;
import org.skyjava.course2.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExaminerServiceImplTest {

    @Autowired
    ExaminerService examinerService;

    @Test
    void getExaminerService() {
        assertNotNull(examinerService);
        assertNotNull(examinerService.getExaminerService("java"));
        assertThrows(RuntimeException.class, () -> {
            examinerService.getExaminerService("test");
        });
    }

    @Test
    void getQuestions() {
        QuestionService service = examinerService.getExaminerService("java");
        assertNotNull(service);
        for (int i = 0; i < 10; i++) {
            String s = "Test " + i;
            service.add(s, s);
        }

        Collection<Question> collection = examinerService.getQuestions(5);
        assertNotNull(collection);
        assertEquals(5, collection.size());
        assertNotEquals(examinerService.getQuestions(10), examinerService.getQuestions(10));
    }
}