package org.skyjava.course2.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.jetbrains.annotations.NotNull;

@Entity
public class QuestionJava extends Question {
    protected QuestionJava() {
    }

    public QuestionJava(@NotNull String question, @NotNull Answer answer) {
        super(question, answer);
    }

    public QuestionJava(@NotNull String question, @NotNull Answer answer, long id) {
        super(question, answer, id);
    }

    @Override
    public String getTheme() {
        return "java";
    }
}
