# gdx-bp
libgdx boilerplate lib

## usage
build and install to local repo:
```gradle wrapper && ./gradlew build && ./gradlew install```

add to your dependencies in your project's *build.gradle*: 
```
project(":core") {
    ...
    dependencies {
        ...
        compile "com.nightspawn:gdx-bp:0.1"
    }
}
```

add to your MyGame.gwt.xml (or GwtDefinition.gwt.xml):
```<inherits name="com.nightspawn.GdxBp"/>```

## Logging
*GdxLogger* is a (very) simple logger:
```
private static final GdxLogger LOG = new GdxLogger(MyClass.class);

...

    LOG.info("message one={}, two={}", obj1, obj3);

...
```

## Input
*Controls* allows us to map input (keys, mouse...) to self-defined commands:
```
public class MyControls extends Controls<MyControls.InputCommand> {

    public MyControls() {
        addKey(Input.Keys.LEFT, InputCommand.LEFT);
        addKey(Input.Keys.RIGHT, InputCommand.RIGHT);
        addKey(Input.Keys.ESCAPE, InputCommand.EXIT);
    }

    public enum InputCommand {
        LEFT, RIGHT, EXIT;
    }
}
```
*InputMultiplexer* is just that - it sends inputs to multiple InputProcessors.
*InputCommandListener* is an interface to allows us to received commands from a *Controls* instance.
```
public class MyGame implements InputCommandListener<InputCommand> {
    private Stage stage;
    
    public MyGame {
        stage = new Stage(...);
        MyControls controls = new MyControls();
        InputMultiplexer multiplexer = new InputMultiplexer(controls, stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void onCommand(InputCommand c) {
        if (c == InputCommand.EXIT) {
            Gdx.app.exit();
        }
    }
}
```
