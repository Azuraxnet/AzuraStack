package net.azura.command.defaults;

import net.azura.command.executor.Executor;
import net.azura.command.executor.ParameterizedExecutor;
import net.azura.command.placeholder.Placeholder;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class HelpCommand extends ParameterizedExecutor {
    private static final int itemsPerPage = 5;
    private final CommandSender commandSender;
    private final Executor base;

    public HelpCommand(CommandSender commandSender, Executor base) {
        this.commandSender = commandSender;
        this.base = base;
        addPlaceholder(new Placeholder("page").setDefaultValue("1").setOptional());
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    protected boolean executeWithParameters(Queue<String> args, HashMap<String, String> parameters) {
        List<String> commands = base.getCommands();
        commands.sort(String::compareTo);
        int page = 0;
        if (parameters.containsKey("page")) {
            try {
                page = Integer.parseInt(parameters.get("page")) - 1;
            } catch (NumberFormatException e) {

            }
        }
        int maxPages = getMaxPages(commands.size());
        if (page >= maxPages) {
            page = maxPages - 1;
        } else if (page < 0) {
            page = 0;
        }
        int startIndex = page * itemsPerPage;
        int endIndex = (page * itemsPerPage) + itemsPerPage;
//        commandSender.sendMessage(Util.colorize(RyuCommons.getInstance().getConfig().getString("helpMenu.title").replace("<max-page>", String.valueOf(maxPages)).replace("<page>", String.valueOf((page + 1)))));
        for (int i = startIndex; i < endIndex; i++) {
            if (commands.size() > i) {
//                commandSender.sendMessage(Util.colorize(RyuCommons.getInstance().getConfig().getString("helpMenu.commandColour") + "/" + commands.get(i)));
            } else {
                break;
            }
        }
        return true;
    }

    public int getMaxPages(int size) {
        int pages = size / itemsPerPage;// 13/8 = 1
        int leftOver = size % itemsPerPage; //13 % 8 = 5
        if (leftOver > 0) {
            return pages + 1;
        }
        if (pages == 0) {
            return pages + 1;
        }
        //2
        return pages;
    }

    @Override
    public String getDescription() {
        return "Lists all the commands and their description where applicable";
    }
}
