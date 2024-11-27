package org.skyjava.course2.domains;

import org.jetbrains.annotations.NotNull;

public class QuestionJava extends Question {
    public QuestionJava() {
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
