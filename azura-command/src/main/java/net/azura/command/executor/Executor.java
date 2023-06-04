package net.azura.command.executor;

import net.azura.command.error.CommandError;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public interface Executor {
    boolean execute(Queue<String> args, HashMap<String, String> parameters);

    List<String> getTabs(Queue<String> args);

    String getName();

    void setErrorChain(CommandError commandError);

    void throwError(String error, boolean showCommand);

    List<String> getCommands();

    String getDescription();
}
