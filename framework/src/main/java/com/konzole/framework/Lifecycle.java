package com.konzole.framework;

import java.util.List;
import java.util.Scanner;

/**
 * Holds behaviour logic based on {@code Structure} instance provided to it.
 *      Looping until the user decides to exit the application.
 */
public class Lifecycle {

    private static final String OPTION_SEPARATOR = " - ";
    private static final String INPUT_SEPARATOR = ": ";
    private static final String SELECTION = "Selection";
    private static final String BACK_OPTION = "Back";
    private static final String EXIT_OPTION = "Exit";
    private static final String WRONG_OPTION = "Wrong option";

    private Structure structure;
    private Scanner scanner;

    /**
     * One and only constructor.
     * @param structure Main application structure which has nested questions, options and inputs.
     */
    public Lifecycle(Structure structure) {
        this.structure = structure;

        scanner = new Scanner(System.in);
    }

    /**
     * Displays main question from provided {@code Structure} instance.
     */
    public void run() {
        ask(structure.getQuestion());
    }

    private void ask(Question question) {
        System.out.println(question.getTitle());

        int index = 0;

        List<Option> options = question.getOptions();
        options.add(new ExecuteOption(BACK_OPTION) {
            @Override
            public void execute() {
                if(question.hasParent()) {
                    ask(question.getParent());
                }
            }
        });
        options.add(new ExecuteOption(EXIT_OPTION) {
            @Override
            public void execute() {
                System.exit(0);
            }
        });

        for(Option option : options) {
            System.out.println(++index + OPTION_SEPARATOR + option.getTitle());
        }

        System.out.print(SELECTION + INPUT_SEPARATOR);

        Option selection;

        try {
            selection = options.get(scanner.nextInt() - 1);
        }catch(IndexOutOfBoundsException e) {
            System.out.println(WRONG_OPTION);
            ask(question);
            return;
        }

        if(selection.hasInputs()) {
            scanner.nextLine();

            for(Input input : selection.getInputs()) {
                System.out.print(input.getTitle() + INPUT_SEPARATOR);
                input.setValue(scanner.nextLine());
            }
        }

        selection.onSelected();

        if(selection.hasQuestion()) {
            ask(selection.getQuestion());
        }else {
            ask(structure.getQuestion());
        }
    }
}
