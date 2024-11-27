package org.skyjava.course2.domains;

public class Answer {
    final public String answer;
    final public boolean isCorrect;

    public Answer(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
