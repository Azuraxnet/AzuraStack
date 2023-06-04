package net.azura.command.executor;


import net.azura.command.error.CommandError;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class ExecutorWrapper implements Executor {
    private final Executor executor;

    public ExecutorWrapper(Executor executor) {
        this.executor = executor;
    }

    @Override
    public boolean execute(Queue<String> args, HashMap<String, String> parameters) {
        return executor.execute(args, parameters);
    }


    @Override
    public List<String> getTabs(Queue<String> args) {
        return executor.getTabs(args);
    }

    @Override
    public String getName() {
        return executor.getName();
    }

    @Override
    public void setErrorChain(CommandError error) {
        executor.setErrorChain(error);
    }

    @Override
    public void throwError(String error, boolean showCommand) {
        executor.throwError(error, showCommand);
    }

    @Override
    public List<String> getCommands() {
        return executor.getCommands();
    }

    @Override
    public String getDescription() {
        return executor.getDescription();
    }
}
