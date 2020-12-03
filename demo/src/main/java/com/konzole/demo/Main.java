package com.konzole.demo;

import com.konzole.framework.*;

public class Main {

    public static void main(String[] args) {
        new Lifecycle(new DemoStructure()).run();
    }

    private static final class DemoStructure extends Structure {
        @Override
        protected Question create() {
            return new Question("What operation to execute?")
                .addOption(new Option("File upload")
                        .setQuestion(new Question("Where is the file located?")
                                .addOption(new ExecuteOption("Local storage") {
                                    @Override
                                    public void execute() {
                                        String destination = getInput("destination").getValue();
                                        System.out.println(String.format("Uploading file to %s", destination));
                                    }
                                }
                                .addInput(new Input("Destination")))
                        )
                );
        }
    }
}
