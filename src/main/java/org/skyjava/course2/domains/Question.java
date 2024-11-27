package org.skyjava.course2.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Question {
    private final Map<String, Boolean> answers = new HashMap<>();
    private long id;
    private String question;

    public Question() {
    }

    public Question(@NotNull String question, @NotNull Answer answer) {
        this(question, answer, 0);
    }

    public Question(@NotNull String question, @NotNull Answer answer, long id) {
        if (question.isBlank() || answer.answer.isBlank()) {
            throw new IllegalArgumentException("Вопрос и/или ответ не должны быть пустыми");
        }
        this.question = question;
        this.answers.put(answer.answer, answer.isCorrect);
        if (id > 0) {
            this.id = id;
        } else {
            this.id = Math.abs((new Random()).nextLong());
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonIgnore
    public int getAnswersCount() {
        return answers.size();
    }

    public Collection<String> getAnswersCorrect() {
        if (answers.size() == 1) {
            return answers.keySet();
        } else {
            return answers.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(Map.Entry::getKey).
                    collect(Collectors.toSet());
        }
    }

    public Collection<String> getAnswersDisplayed() {
        if (answers.size() == 1) {
            return new HashSet<>();
        } else {
            return answers.keySet();
        }
    }

    @JsonIgnore
    public Collection<String> getAnswersAll() {
        return answers.keySet();
    }

    public void setAnswer(@NotNull Answer answer) {
        this.answers.put(answer.answer, answer.isCorrect);
    }

    public void delAnswer(@NotNull String answer) {
        this.answers.remove(answer);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    abstract public String getTheme();

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
                ", answers='" + answers + '\'' +
                '}';
    }

}
