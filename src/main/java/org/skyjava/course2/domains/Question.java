package org.skyjava.course2.domains;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class Question {
    private final long id;
    private final String question;
    private String answer;

    public Question(@NotNull String question, @NotNull String answer) {
        this(question, answer, 0);
    }

    public Question(@NotNull String question, @NotNull String answer, long id) {
        if (question.isBlank() || answer.isBlank()) {
            throw new IllegalArgumentException("Вопрос и/или ответ не должны быть пустыми");
        }
        this.question = question;
        this.answer = answer;
        if (id > 0) {
            this.id = id;
        } else {
            this.id = Math.abs((new Random()).nextLong());
        }
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question)
                //        && Objects.equals(answer, question1.answer)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(question
//                , answer
        );
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
