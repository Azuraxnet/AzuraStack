package net.azura.messages.interfaces;

import com.sun.istack.internal.Nullable;

public interface MessageReferencesProvider {
    /**
     * This is responsible for grabbing a message from the container and returning it.
     *
     * @param key the message key - This is used to identify the message you wish to use.
     * @return String
     */
    @Nullable
    String get(String key);
}
