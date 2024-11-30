package org.skyjava.course2.domains;

public record Answer(String answer, boolean isCorrect) {

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
