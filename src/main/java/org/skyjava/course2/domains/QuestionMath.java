package org.skyjava.course2.domains;

import org.jetbrains.annotations.NotNull;

public class QuestionMath extends Question {
    public QuestionMath() {
    }

    public QuestionMath(@NotNull String question, @NotNull Answer answer) {
        super(question, answer);
    }

    public QuestionMath(@NotNull String question, @NotNull Answer answer, long id) {
        super(question, answer, id);
    }

    @Override
    public String getTheme() {
        return "math";
    }
}
