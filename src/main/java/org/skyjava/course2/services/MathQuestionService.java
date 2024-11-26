package org.skyjava.course2.services;

import org.skyjava.course2.domains.Question;
import org.skyjava.course2.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MathQuestionService implements QuestionService {
    private Random rand = new Random();

    @Override
    public Question add(String question, String answer) {
        throw new UnsupportedOperationException("Метод не поддерживается: add");
    }

    @Override
    public Question remove(String question, String answer) {
        throw new UnsupportedOperationException("Метод не поддерживается: remove");
    }

    @Override
    public Question find(String question, String answer) {
        throw new UnsupportedOperationException("Метод не поддерживается: find");
    }

    @Override
    public Question GetRandomQuestion() {
        return find(Math.abs(rand.nextLong()));
    }

    @Override
    public Collection<Question> getAll() {
        return getRandomQuestions(1000);
    }

    @Override
    public Collection<Question> getRandomQuestions(int count) {
        List<Question> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Question q = find(Math.abs(rand.nextLong()));
            result.add(q);
        }
        return result;
    }

    @Override
    public int getSize() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Question find(long id) {
        Random r = new Random(id);
        String question, answer;
        String[] operators = {"+", "-", "*", "/"};
        int[] nums = new int[3];

        String strOperator = operators[r.nextInt(operators.length)];

        switch (strOperator) {
            case "+":
                nums[0] = r.nextInt(50) + 1;
                nums[1] = r.nextInt(50) + 1;
                nums[2] = nums[0] + nums[1];
                break;
            case "-":
                nums[1] = r.nextInt(50) + 1;
                nums[2] = r.nextInt(50) + 1;
                nums[0] = nums[1] + nums[2];
                break;
            case "*":
                nums[0] = r.nextInt(9) + 2;
                nums[1] = r.nextInt(9) + 2;
                nums[2] = nums[0] * nums[1];
                break;
            case "/":
                nums[1] = r.nextInt(9) + 2;
                nums[2] = r.nextInt(9) + 2;
                nums[0] = nums[1] * nums[2];
                break;
        }

        int answerPos = r.nextInt(3);
        answer = String.valueOf(nums[answerPos]);

        StringBuilder[] sb = new StringBuilder[5];
        sb[1] = new StringBuilder(strOperator);
        sb[3] = new StringBuilder("=");

        for (int i = 0; i < nums.length; i++) {
            sb[i * 2] = new StringBuilder(answerPos == i ? "?" : String.valueOf(nums[i]));
        }

        question = Arrays.stream(sb)
                .map(StringBuilder::toString)
                .collect(Collectors.joining(" "));

        return new Question(question, answer, id);
    }

    @Override
    public Question remove(long id) {
        throw new UnsupportedOperationException("Метод не поддерживается: remove");
    }
}
