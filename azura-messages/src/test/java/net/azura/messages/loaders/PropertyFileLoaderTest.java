package net.azura.messages.loaders;

import net.azura.messages.format.MessageFileFormat;
import net.azura.messages.interfaces.MessageReferencesProvider;
import net.azura.messages.interfaces.loading.MessageLoader;
import net.azura.messages.loaders.PropertyFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class PropertyFileLoaderTest {
    private MessageLoader loader;

    @Before
    public void initAzuraMessages() {
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir + "\\target\\");
        loader = new PropertyFileLoader(path, new MessageFileFormat("lang_<lang>"));
    }

    @Test
    public void testSetLocalizationAndLoad() {
        MessageReferencesProvider messageReferencesProvider = loader.load("en_US");
        assertNotNull(messageReferencesProvider);
    }

    @Test
    public void testNullIndentifier() {
        assertThrows(NullPointerException.class, ()-> {
            loader.load(null);
        });

    }
}
