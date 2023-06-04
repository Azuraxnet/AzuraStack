package net.azura.command.executor;


import net.azura.command.error.CommandError;
import net.azura.command.exception.InvalidConfigurationException;
import net.azura.command.placeholder.Placeholder;

import java.util.*;
import java.util.stream.Collectors;


public abstract class ParameterizedExecutor implements Executor {
    private final Set<Executor> executors;
    private final List<Placeholder> placeholders;
    //Add support tabs/placeholders/auto-execution
    //placeholders =
    //command <ph>
    private final InvalidExecutor defaultExecutor;
    private CommandError commandError;

    public ParameterizedExecutor() {
        executors = new HashSet<>();
        placeholders = new ArrayList<>();
        this.defaultExecutor = new InvalidExecutor();
    }

    //TODO: Add support for optional parameters
    @Override
    public final List<String> getTabs(Queue<String> args) {
        int counter = 0;
        List<String> tab = new LinkedList<>();
        while (counter < placeholders.size()) {
            //Doesn't exist
            if (args.peek() == null) {
                return tab;
            }
            if (args.peek().isEmpty()) {
                tab.add(placeholders.get(counter).getPlaceholderTab());
                return tab;
            }
            String match = args.poll();
            if (args.peek() == null) {
                tab.addAll(placeholders.get(counter).getOptions().stream().filter(x -> x.toLowerCase(Locale.ROOT).startsWith(match.toLowerCase(Locale.ROOT))).collect(Collectors.toList()));
                return tab;
            }
            counter++;
        }
        //Placeholders are set
        List<String> possible = executors.stream().map(Executor::getName).collect(Collectors.toList());
        if (args.isEmpty()) {
            return new ArrayList<>();
        }
        //
        final String toMatch = args.poll();
        if (toMatch.isEmpty()) return possible;
        if (!isExecutor(toMatch)) {
            return possible.stream().map(String::toLowerCase)
                    .filter(x -> x.startsWith(toMatch))
                    .collect(Collectors.toList());
        }
        return getExecutor(toMatch).getTabs(args);
    }

    @Override
    public final boolean execute(Queue<String> args, HashMap<String, String> parameters) {
        int counter = 0;
        if (commandError == null) {
            commandError = new CommandError((e) -> {

            });
            try {
                throw new InvalidConfigurationException("The command: " + getName() + " was not added as an executor! Error messages will not work properly.");
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        commandError.addCommand(getName());
        defaultExecutor.setErrorChain(commandError);
        for (Executor executor : executors) {
            executor.setErrorChain(commandError);
            defaultExecutor.addOption(executor.getName());
        }
        while (counter < placeholders.size()) {
            Placeholder placeholder = placeholders.get(counter);
            commandError.addCommand(placeholder.getPlaceholderTab());
            //Doesn't exist
            if (args.peek() == null || args.peek().isEmpty()) {
                if (placeholder.isOptional()) {
                    if (placeholder.hasDefaultValue()) {
                        parameters.put(placeholder.getPlaceholderName(), placeholder.getDefaultValue());
                    }
                } else {
                    commandError.throwError();
                    return false;
                }
            } else {
                String val = args.poll();
                //If it is optional, check if they left it null
                if (placeholder.isOptional() && val.equalsIgnoreCase("null")) {
                    if (placeholder.hasDefaultValue()) {
                        parameters.put(placeholder.getPlaceholderName(), placeholder.getDefaultValue());
                    }
                    //If it is optional check if the value is contained
                } else if (placeholder.isOptional() && placeholder.containsOption(val)) {
                    parameters.put(placeholder.getPlaceholderName(), val);
                    //If it is optional aand the only value is null
                } else if (placeholder.isOptional() && placeholder.getOptions().get(0).equalsIgnoreCase("null")) {
                    parameters.put(placeholder.getPlaceholderName(), val);
                    //Lastly if it isn't optional add it
                } else if (!placeholder.isOptional()) {
                    parameters.put(placeholder.getPlaceholderName(), val);
                } else {
                    commandError.throwError();
                }
            }

            counter++;
        }

        return executeWithParameters(args, parameters);
    }

    protected boolean executeWithParameters(Queue<String> args, HashMap<String, String> parameters) {
        Executor executor = getExecutor(args.poll());
        return executor.execute(args, parameters);
    }

    public final Executor getExecutor(String name) {
        if (name == null) return defaultExecutor;
        Optional<Executor> executor = executors.stream().filter(e -> e.getName().equalsIgnoreCase(name.toLowerCase(Locale.ROOT))).findFirst();
        return executor.orElse(defaultExecutor);
    }

    private boolean isExecutor(String name) {
        return !getExecutor(name).equals(defaultExecutor);
    }

    public final void addExecutor(Executor executor) {
        this.executors.add(executor);
    }

    public final void addExecutors(Executor... executors) {
        this.executors.addAll(new ArrayList<>(Arrays.asList(executors)));
    }

    public final void addPlaceholder(Placeholder placeholder) {
        this.placeholders.add(placeholder);
    }

    @Override
    public void setErrorChain(CommandError commandError) {
        this.commandError = commandError;
    }

    @Override
    public void throwError(String error, boolean showCommand) {
        Objects.requireNonNull(commandError);
        commandError.setShowCommand(showCommand);
        commandError.setError(error);
        commandError.throwError();
    }

    public List<String> getCommands() {
        List<String> commands = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (Placeholder placeholder : placeholders) {
            sb.append("&c").append(placeholder.getPlaceholderTab()).append(" ");
        }
        if (executors.isEmpty()) {
            if (getDescription() != null) {
//                commands.add(getName() + " " + sb + RyuCommons.getInstance().getConfig().getString("helpMenu.descriptionColour") + " - " + getDescription());
            } else {
                commands.add(getName() + " " + sb);
            }

        }
        for (Executor executor : executors) {
            for (String key : executor.getCommands()) {
                commands.add(getName() + " " + sb + key);
            }
        }
        return commands;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
