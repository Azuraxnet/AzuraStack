package net.azura.messages;

import net.azura.messages.format.MessageFileFormat;
import net.azura.messages.interfaces.AzuraLocaleProvider;
import net.azura.messages.interfaces.MessageReferencesProvider;
import net.azura.messages.interfaces.loading.FileFormat;
import net.azura.messages.interfaces.loading.MessageLoader;
import net.azura.messages.loaders.PropertyFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

/**
 * TODO:
 * - Write tests for all functionality of how this API will be used.
 * - Check for unhandled exceptions, and then deal with any I find.
 * - Convert any System.out.println()'s to an actual logger related to this API.
 */
public class AzuraMessages implements AzuraLocaleProvider {
    private String localization;
    private MessageReferencesProvider messageReferences;

    private MessageLoader messageLoader;

    public AzuraMessages(Path defaultFile) {
        this(new PropertyFileLoader(defaultFile, new MessageFileFormat("lang_<lang>")), "en_US");
    }

    public AzuraMessages(Path path, FileFormat messageFileFormat) {
        this(new PropertyFileLoader(path, messageFileFormat, false), "en_US");
    }

    public AzuraMessages(MessageLoader messageLoader, String defaultLocale) {
        this.messageLoader = messageLoader;
        this.localization = defaultLocale;

        this.messageReferences = messageLoader.load(defaultLocale);
    }

    @Override
    public void setLocalization(String localization) {
        if(this.localization != null && this.localization.equals(localization)) {
            LOGGER.debug("The localization is already set to {}", localization);
            return;
        }

        this.localization = localization;
        this.messageReferences = messageLoader.load(localization);
    }

    @Override
    public String getLocalization() {
        return localization;
    }

    @Override
    public String[] getSupportedLocalizations() {
        return messageLoader.getSupportedLocalizations();
    }

    @Override
    public MessageReferencesProvider getMessageReferences() {
        return messageReferences;
    }

    protected static final Logger LOGGER = LogManager.getLogger(AzuraMessages.class);
}
