[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/jelic98/konzole.svg)](https://jitpack.io/#jelic98/konzole)

# Konzole

Framework for building Java console applications in a declarative manner.  

## Installing

1. Add repository in root ```build.gradle```

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```gradle
dependencies {
    compile 'com.github.jelic98:konzole:1.0.0'
}
```

## Usage

For more details, take a look at this awesome [demo](https://github.com/jelic98/konzole/blob/master/demo/src/main/java/com/konzole/demo/Main.java).  

```java
new Lifecycle(new Structure() {
    @Override
    protected Question create() {
        return new Question("What operation to execute?")
            .addOption(new Option("File upload")
                .setQuestion(new Question("What is a file located?")
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
}).run();
```

## TODO

* Add license
* Add documentation
* Add demo project
* Add demo gif
