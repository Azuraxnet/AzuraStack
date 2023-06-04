package net.azura.command.placeholder;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Placeholder {
    private final String placeholder;
    private final List<String> options;
    private boolean optional = false;
    private String defaultValue = null;

    /*
         /m give[<player>] item[<wand>, <amount>]
         */
    //TODO: add support for default
    public Placeholder(String placeholder) {
        this.placeholder = placeholder.toLowerCase(Locale.ROOT);
        this.options = new LinkedList<>();
    }

    public Placeholder(String placeholder, List<String> options) {
        this.placeholder = placeholder;
        this.options = options;
    }

    public String getPlaceholderName() {
        return placeholder;
    }

    public String getPlaceholderTab() {
        return "<" + (optional ? "optional:" : "") + placeholder + ">";
    }

    public List<String> getOptions() {
        if (optional && !options.contains("null")) {
            options.add("null");
        }
        return options;
    }

    public boolean containsOption(String value) {
        for (String s : options) {
            if (s.equalsIgnoreCase(value.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    public Placeholder setOptional() {
        this.optional = true;
        return this;
    }

    public boolean isOptional() {
        return optional;
    }

    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Placeholder setDefaultValue(String value) {
        this.defaultValue = value;
        return this;
    }
}
