package net.azura.messages.interfaces;

import net.azura.messages.enums.Locale;

public interface AzuraLocaleProvider {
    /**
     * This will set the Locale to the specified enum.
     *
     * @param localization the locale the end user is asking to use.
     */
    default void setLocalization(Locale localization){
        setLocalization(localization.getId());
    }

    /**
     * This will set the Locale to the specified value.
     *
     * @param localization the locale the end user is asking to use.
     */
    void setLocalization(String localization);

    String getLocalization();

    /**
     * Returns the supported locale(s) with this instance.
     *
     * @return String[]
     */
    String[] getSupportedLocalizations();

    /**
     * Returns the MessageReferences related to this locale.
     *
     * @return MessageReferences this is a container that holds all the messages related to this locale.
     */
    MessageReferencesProvider getMessageReferences();
}
