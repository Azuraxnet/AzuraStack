package net.azura.messages;

import net.azura.messages.AzuraMessages;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AzuraMessagesTest {
    private AzuraMessages azuraMessages;

    @Before
    public void initAzuraMessages() {
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir + "\\target\\");
        azuraMessages = new AzuraMessages(path);
    }

    @Test
    public void getLocales() {
        String[] locales = azuraMessages.getSupportedLocalizations();
        assertNotNull(locales);
        System.out.println(Arrays.toString(locales));
    }

    @Test
    public void setLocalization() {
        azuraMessages.setLocalization("en_GB");
        assertEquals(azuraMessages.getLocalization(), "en_GB");

        azuraMessages.setLocalization("en_US");
        assertEquals(azuraMessages.getLocalization(), "en_US");
    }
}
