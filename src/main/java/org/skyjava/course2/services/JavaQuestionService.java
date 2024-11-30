package org.skyjava.course2.services;

import org.skyjava.course2.domains.Answer;
import org.skyjava.course2.domains.Question;
import org.skyjava.course2.domains.QuestionJava;
import org.skyjava.course2.interfaces.QuestionService;
import org.skyjava.course2.repositories.QuestionRepository;
import org.skyjava.course2.repositories.QuestionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class JavaQuestionService implements QuestionService {
    private final Class<? extends Question> classQuestion = QuestionJava.class;
    private final QuestionRepository repository;

    public JavaQuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(question, new Answer(answer, false));
    }

    @Override
    public Question add(String question, Answer answer) {
        Question newQuestion = getNewQuestion(question, answer);
        newQuestion.setAnswer(answer);
        repository.save(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(String question, String answer) {
        Question result = repository.findByQuestion(question).orElse(null);
        if (result != null) {
            result.delAnswer(answer);
            if (result.getAnswersCount() == 0) {
                repository.delete(result);
            } else {
                repository.save(result);
            }
            return result;
        }
        return null;
    }

    @Override
    public Question find(String question, String answer) {
        return repository.findByQuestion(question).orElse(null);
    }

    @Override
    public Question find(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Question getRandomQuestion() {
        if (getSize() == 0) {
            return null;
        }
        return getRandomQuestions(1).iterator().next();
    }

    @Override
    public Collection<Question> getAll() {
        return repository.findAll();
    }

    @Override
    public Collection<Question> getRandomQuestions(int count) {
        int size = getSize();
        if (size < count) {
            throw new IllegalArgumentException(String.format(
                    Locale.ROOT
                    ,"%s: Количество вопросов меньше запрашиваемого (%d < %d)"
                    , getTheme(), size, count));
        } else {
            Page<Question> all = repository.findAll(new QuestionSpecification(), PageRequest.of(0, count));
            return all.getContent();
        }
    }

    @Override
    public int getSize() {
        return (int) repository.count();
    }

    @Override
    public Question remove(long id) {
        Question result = find(id);
        if (result != null) {
            repository.delete(result);
        }
        return result;
    }

    @Override
    public String getTheme() {
        try {
            Constructor<? extends Question> constructor = classQuestion.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance().getTheme();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Question getNewQuestion(String question, Answer answer) {
        Question result = repository.findByQuestion(question).orElse(null);
        if (result != null) {
            return result;
        }
        try {
            Constructor<? extends Question> constructor = classQuestion.getConstructor(String.class, Answer.class);
            return constructor.newInstance(question, answer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
