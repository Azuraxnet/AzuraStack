package net.azura.messages;

import net.azura.messages.AzuraMessages;
import net.azura.messages.format.MessageFileFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AzuraMessagesTest {
    private AzuraMessages azuraMessages;
    private static Path path;

    @BeforeClass
    public static void initLangFiles() {
        String currentDir = System.getProperty("user.dir");
        path = Paths.get(currentDir + "\\target\\");

        try {
            new File(path + "/lang_en_GB.properties").createNewFile();
            new File(path + "/lang_en_US.properties").createNewFile();
        } catch (IOException e) {
            LOGGER.error("Initialization of language files could not be created.");
        }
    }

    @Before
    public void initAzuraMessages() {

        azuraMessages = new AzuraMessages(path, new MessageFileFormat("lang_<lang>"));

        try {
            new File(path + "/lang_en_GB.properties").createNewFile();
            new File(path + "/lang_en_US.properties").createNewFile();
        } catch (IOException e) {
            LOGGER.error("Initialization of language files could not be created.");
        }
    }

    @Test
    public void getLocales() {
        String[] locales = azuraMessages.getSupportedLocalizations();
        assertNotNull(locales);
        LOGGER.info(Arrays.toString(locales));
    }

    @Test
    public void setLocalization() {
        azuraMessages.setLocalization("en_GB");
        assertEquals(azuraMessages.getLocalization(), "en_GB");

        azuraMessages.setLocalization("en_US");
        assertEquals(azuraMessages.getLocalization(), "en_US");
    }

    protected static final Logger LOGGER = LogManager.getLogger("test");
}
