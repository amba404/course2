package org.skyjava.course2.services;

import org.skyjava.course2.domains.Question;
import org.skyjava.course2.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private Random rand = new Random();
    List<Question> questions = new ArrayList<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if(!questions.contains(newQuestion)) {
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
        return null;
    }

    @Override
    public Question find(String question, String answer) {
        return null;
    }

    @Override
    public Question find(long id) {
        return null;
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
        if (getSize() == 0) {
            return List.of();
        } else if (count >= getSize() / 2) {
            List<Question> all = new ArrayList<>(getAll());
            Collections.shuffle(all);
            return all.subList(0, count);
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
        return null;
    }
}
