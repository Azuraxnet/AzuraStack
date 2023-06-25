package net.azura.messages.messages;

import net.azura.messages.interfaces.MessageReferencesProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class MessageReferences implements MessageReferencesProvider {

    public MessageReferences(Map<String, String> references) {
        this.references = references;
    }

    @Override
    public String get(String key) {
        if(StringUtils.isBlank(key)) {
            throw new NullPointerException("The message key specified was either null or empty.");
        }
        return references.get(key);
    }

    protected final Map<String, String> references;
}
