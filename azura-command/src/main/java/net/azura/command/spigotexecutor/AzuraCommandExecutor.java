package net.azura.command.spigotexecutor;

import net.azura.command.error.CommandError;
import net.azura.command.executor.ExecutorWrapper;
import net.azura.command.executor.ConsoleExecutor;
import net.azura.command.executor.Executor;
import net.azura.command.executor.PlayerExecutor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Function;

public class AzuraCommandExecutor implements CommandExecutor, TabCompleter {
    private Function<Player, PlayerExecutor> playerFunction;
    private Function<ConsoleCommandSender, ConsoleExecutor> consoleFunction;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Queue<String> params = new LinkedList<>(new ArrayList<>(Arrays.asList(args)));
        if (params.isEmpty()) return false;
        return getExecutor(sender).execute(params, new HashMap<>());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        Queue<String> params = new LinkedList<>(new ArrayList<>(Arrays.asList(args)));
        if (params.isEmpty()) return new ArrayList<>();
        return getExecutor(sender).getTabs(params);
    }
    private Executor getExecutor(CommandSender sender){
        Executor executor;
        if (sender instanceof Player) {
            executor = new ExecutorWrapper(playerFunction.apply((Player) sender));
        } else {
            executor = new ExecutorWrapper(consoleFunction.apply((ConsoleCommandSender) sender));
        }
        executor.setErrorChain(new CommandError((commandError -> {
            sender.sendMessage(commandError.getError());
        })));
        return executor;
    }
    public void registerPlayerExecutor(Function<Player, PlayerExecutor> playerFunction){
        this.playerFunction = playerFunction;
    }
    public void registerConsoleExecutor(Function<ConsoleCommandSender, ConsoleExecutor> consoleFunction){
        this.consoleFunction = consoleFunction;
    }
}
