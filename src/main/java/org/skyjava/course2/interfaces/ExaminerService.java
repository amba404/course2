package org.skyjava.course2.interfaces;

import org.skyjava.course2.domains.Question;

import java.util.Collection;

public interface ExaminerService {
    QuestionService getExaminerService(String theme);

    Collection<Question> getQuestions(int amount);
}
