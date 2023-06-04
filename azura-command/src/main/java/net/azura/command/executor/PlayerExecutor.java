package net.azura.command.executor;

import net.azura.command.defaults.HelpCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Queue;

public final class PlayerExecutor extends ParameterizedExecutor{
    private String baseCommand;
    public PlayerExecutor(Player player, String baseCommand) {
        this.baseCommand = baseCommand;
        addExecutor(new HelpCommand(player, this));
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
