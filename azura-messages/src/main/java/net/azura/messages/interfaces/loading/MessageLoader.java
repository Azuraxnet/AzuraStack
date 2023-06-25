package net.azura.messages.interfaces.loading;

import net.azura.messages.interfaces.MessageReferencesProvider;

public interface MessageLoader {
    /**
     * This is responsible for loading messages from the identifier provided.
     *
     * @param identifier The identifier is a string that is used by the loader to find and load a specific message map.
     * @return MessageReferences
     */
    MessageReferencesProvider load(String identifier);
    String[] getSupportedLocalizations();
}
