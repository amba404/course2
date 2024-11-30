package org.skyjava.course2.services;

import org.skyjava.course2.domains.Question;
import org.skyjava.course2.interfaces.ExaminerService;
import org.skyjava.course2.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Map<String, QuestionService> examinerServices;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        examinerServices = new HashMap<>();
        examinerServices.put(javaQuestionService.getTheme(), javaQuestionService);
        examinerServices.put(mathQuestionService.getTheme(), mathQuestionService);
    }

    @Override
    public QuestionService getExaminerService(String theme) {
        if (!examinerServices.containsKey(theme)) {
            throw new IllegalArgumentException("Раздел не существует: " + theme);
        }
        return examinerServices.get(theme);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Количество вопросов не может быть меньше 1");
        }

        List<Question> questions = new ArrayList<>();

        for (String t : examinerServices.keySet()) {
            QuestionService service = examinerServices.get(t);
            questions.addAll(service.getRandomQuestions(amount));
        }

        Collections.shuffle(questions);
        return questions.subList(0, amount);
    }
}
