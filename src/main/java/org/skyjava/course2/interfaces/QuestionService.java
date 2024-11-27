package org.skyjava.course2.interfaces;

import org.skyjava.course2.domains.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question remove(String question, String answer);

    Question find(String question, String answer);

    Question getRandomQuestion();

    Collection<Question> getAll();

    Collection<Question> getRandomQuestions(int count);

    int getSize();

    Question find(long id);

    Question remove(long id);

    String getTheme();
}
