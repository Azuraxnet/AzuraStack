package net.azura.command.error;


import net.azura.command.Util;

import java.util.function.Consumer;

public class CommandError {
    private final Consumer<CommandError> errorConsumer;
    boolean showCommand = true;
    private String command;
    private String error;

    public CommandError(Consumer<CommandError> errorConsumer) {
        command = "";
        this.errorConsumer = errorConsumer;
    }

    public String getError() {
        StringBuilder sb = new StringBuilder();
        boolean nl = false;
        if (error != null) {
            sb.append(Util.colorize(error));
            nl = true;
        }
        if (showCommand) {
            if (nl) {
                sb.append("\n");
            }
//            sb.append(Util.colorize(RyuCommons.getInstance().getConfig().getString("helpMenu.usagePrefix") + " /" +command.trim()));
        }
        return sb.toString();
    }

    public void setError(String error) {
        this.error = error;
    }

    public CommandError addCommand(String cmd) {
        command = command + " " + cmd;
        return this;
    }

    public void setShowCommand(boolean showCommand) {
        this.showCommand = showCommand;
    }

    public void throwError() {
        errorConsumer.accept(this);
    }
}
