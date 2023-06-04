package net.azura.command.executor;

import net.azura.command.defaults.HelpCommand;
import org.bukkit.command.ConsoleCommandSender;

import java.util.HashMap;
import java.util.Queue;

public class ConsoleExecutor extends ParameterizedExecutor{
    private String baseCommand;
    public ConsoleExecutor(ConsoleCommandSender sender, String baseCommand) {
        this.baseCommand = baseCommand;
        addExecutor(new HelpCommand(sender, this));
    }

    @Override
    protected boolean executeWithParameters(Queue<String> args, HashMap<String, String> parameters) {
        Executor executor = getExecutor(args.poll());
        return executor.execute(args, parameters);
    }

    @Override
    public String getName() {
        return baseCommand;
    }
}
