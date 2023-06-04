package net.azura.command.executor;

import net.azura.command.error.CommandError;

import java.util.*;

public class InvalidExecutor implements Executor {
    private final List<String> options;
    private CommandError commandError;

    public InvalidExecutor() {
        this.options = new LinkedList<>();
    }

    @Override
    public boolean execute(Queue<String> args, HashMap<String, String> parameters) {
        Objects.requireNonNull(commandError);
        applyOptions();
        commandError.setShowCommand(true);
        commandError.throwError();
        return false;
    }

    @Override
    public List<String> getTabs(Queue<String> args) {
        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return "invalid";
    }

    @Override
    public void setErrorChain(CommandError commandError) {
        this.commandError = commandError;
    }

    @Override
    public void throwError(String error, boolean showCommand) {
        Objects.requireNonNull(commandError);
        applyOptions();
        commandError.setShowCommand(showCommand);
        commandError.setError(error);
        commandError.throwError();
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    private void applyOptions() {
        if (!options.isEmpty()) {
            StringBuilder sb = new StringBuilder().append("[");
            for (String s : options) {
                sb.append(s).append("/");
            }
            sb.setLength(sb.length() - 1);
            sb.append("]");
            commandError.addCommand(sb.toString());
        }
    }

    @Override
    public List<String> getCommands() {
        return new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
