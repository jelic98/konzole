package com.konzole.framework;

public abstract class Structure {

    private Question question;

    public Structure() {
        question = create();
    }

    protected abstract Question create();

    Question getQuestion() {
        return question;
    }
}
