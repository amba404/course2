package org.skyjava.course2.services;

import org.skyjava.course2.domains.Answer;
import org.skyjava.course2.domains.Question;
import org.skyjava.course2.domains.QuestionJava;
import org.skyjava.course2.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Constructor;
import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Random rand = new Random();
    private final List<Question> questions = new ArrayList<>();
    private final Class<? extends Question> classQuestion = QuestionJava.class;

    @Override
    public Question add(String question, String answer) {
        return add(question, new Answer(answer, false));
    }

    @Override
    public Question add(String question, Answer answer) {
        Question newQuestion = getNewQuestion(question, answer);
        if (!questions.contains(newQuestion)) {
            questions.add(newQuestion);
            return newQuestion;
        } else {
            Question oldQuestion = questions.get(questions.indexOf(newQuestion));
            oldQuestion.setAnswer(answer);
            return oldQuestion;
        }
    }

    @Override
    public Question remove(String question, String answer) {
        Question newQuestion = getNewQuestion(question, new Answer(answer, false));
        if (questions.contains(newQuestion)) {
            int pos = questions.indexOf(newQuestion);
            Question result = questions.get(pos);
            result.delAnswer(answer);
            if (result.getAnswersCount() == 0) {
                questions.remove(pos);
            }
            return result;
        }
        return null;
    }

    @Override
    public Question find(String question, String answer) {
        Question newQuestion = getNewQuestion(question, new Answer(answer, false));
        if (questions.contains(newQuestion)) {
            int pos = questions.indexOf(newQuestion);
            return questions.get(pos);
        }
        return null;
    }

    @Override
    public Question find(long id) {
        return questions.stream()
                .filter(q -> q.getId() == id).findFirst()
                .orElse(null);
    }

    @Override
    public Question getRandomQuestion() {
        if (getSize() == 0) {
            return null;
        }
        return questions.get(rand.nextInt(getSize()));
    }

    @Override
    public Collection<Question> getAll() {
        return List.copyOf(questions);
    }

    @Override
    public Collection<Question> getRandomQuestions(int count) {
        int size = getSize();
        if (size == 0) {
            return List.of();
        } else if (count >= size / 2) {
            List<Question> all = new ArrayList<>(getAll());
            Collections.shuffle(all);
            return all.subList(0, Math.min(size, count));
        } else {
            Set<Question> set = new HashSet<>();
            while (set.size() < count) {
                set.add(getRandomQuestion());
            }
            return List.copyOf(set);
        }
    }

    @Override
    public int getSize() {
        return questions.size();
    }

    @Override
    public Question remove(long id) {
        Question result = find(id);
        if (result != null) {
            questions.remove(result);
        }
        return result;
    }

    @Override
    public String getTheme() {
        try {
            Constructor<? extends Question> constructor = classQuestion.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance().getTheme();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Question getNewQuestion(String question, Answer answer) {
        try {
            Constructor<? extends Question> constructor = classQuestion.getConstructor(String.class, Answer.class);
            return constructor.newInstance(question, answer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
