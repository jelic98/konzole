package com.konzole.framework;

/**
 * Holds user input as key value pair.
 */
public class Input {

    private String title, value;

    /**
     * One and only constructor.
     * @param title Input title which is displayed to the user.
     */
    public Input(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

    /**
     * Returns value which is entered by the user.
     */
    public String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }
}
