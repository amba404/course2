package org.skyjava.course2.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyjava.course2.domains.Answer;
import org.skyjava.course2.domains.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class JavaQuestionServiceTest {
    List<String> testList = new ArrayList<>();
    JavaQuestionService service = new JavaQuestionService();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            String s = "Test " + i;
            testList.add(s);
            service.add(s, s);
        }

        Assertions.assertEquals(10, service.getSize());
    }

    @AfterEach
    void tearDown() {
        for (String s : testList) {
            Question q = service.find(s, s);
            if (q != null) {
                service.remove(q.getId());
            }
        }

        Assertions.assertEquals(0, service.getSize());
    }

    @Test
    void getTheme_Returns_java() {
        Assertions.assertEquals("java", service.getTheme());
    }

    @Test
    void getSize_Returns_10() {
        Assertions.assertEquals(10, service.getSize());
    }

    @Test
    void findByString_Returns_Object() {
        String s = testList.get(0);
        Assertions.assertNotNull(service.find(s, s));
    }

    @Test
    void findByString_Returns_null() {
        String s = testList.get(0) + "1111";
        Assertions.assertNull(service.find(s, s));
    }

    @Test
    void findById_Returns_null() {
        Assertions.assertNull(service.find(100));
    }

    @Test
    void findById_Returns_Object() {
        String s = testList.get(0);
        Question q = service.find(s, s);
        Question q2 = service.find(q.getId());
        Assertions.assertNotNull(q2);
        Assertions.assertEquals(q, q2);
    }

    @Test
    void add_Doubles() {
        String s = testList.get(0);
        service.add(s, s);
        Assertions.assertEquals(10, service.getSize());
    }

    @Test
    void add_Answer() {
        String s = testList.get(0);
        Question q1, q2, q3;

        q1 = service.add(s, new Answer(s, false));
        Assertions.assertEquals(10, service.getSize());

        int answersCount1 = q1.getAnswersCount();
        int answersCorrect1 = q1.getAnswersCorrect().size();
        Assertions.assertEquals(1, answersCount1);
        Assertions.assertEquals(1, answersCorrect1);

        q2 = service.add(s, new Answer(s + "a", false));
        Assertions.assertEquals(10, service.getSize());

        int answersCount2 = q2.getAnswersCount();
        int answersCorrect2 = q2.getAnswersCorrect().size();
        Assertions.assertEquals(2, answersCount2);
        Assertions.assertEquals(0, answersCorrect2);
        Assertions.assertEquals(q1, q2);

        q3 = service.add(s, new Answer(s, true));
        int answersCount3 = q3.getAnswersCount();
        int answersCorrect3 = q3.getAnswersCorrect().size();
        Assertions.assertEquals(2, answersCount3);
        Assertions.assertEquals(1, answersCorrect3);
        Assertions.assertEquals(q1, q3);
    }

    @Test
    void remove_By_String() {
        String s = testList.get(0);
        Question q1, q2, q3;

        q1 = service.find(s, s);
        Assertions.assertNotNull(q1);
        q2 = service.add(s, new Answer(s + "a", false));
        Assertions.assertEquals(q1, q2);
        Assertions.assertEquals(2, q2.getAnswersCount());

        q3 = service.remove(s, s + "a");
        Assertions.assertEquals(1, q3.getAnswersCount());
        q3 = service.remove(s, s);
        Assertions.assertEquals(0, q3.getAnswersCount());
        q3 = service.remove(s, s);
        Assertions.assertNull(q3);

    }

    @Test
    void getRandomQuestions(){
        Collection<Question> q = service.getAll();

        Assertions.assertNotEquals(q, service.getRandomQuestions(q.size()));

        Assertions.assertNotEquals(q.size()+2,
                service.getRandomQuestions(q.size()+2).size());

        Assertions.assertNotEquals(service.getRandomQuestions(3),
                service.getRandomQuestions(3));

    }

}