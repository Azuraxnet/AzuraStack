package net.azura.messages.loaders;

import net.azura.messages.exceptions.LanguageDirectoryDoesNotExistException;
import net.azura.messages.format.MessageFileFormat;
import net.azura.messages.interfaces.MessageReferencesProvider;
import net.azura.messages.interfaces.loading.MessageLoader;
import net.azura.messages.loaders.PropertyFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class PropertyFileLoaderTest {
    private MessageLoader loader;
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

    @Test
    public void testDirectoryNotExist() {
        Path dummyPath = Paths.get(path.toString() + "\\test\\");
        MessageLoader messageLoader = new PropertyFileLoader(dummyPath, new MessageFileFormat("lang_<lang>"));

        assertThrows(LanguageDirectoryDoesNotExistException.class, ()-> {
            messageLoader.load("en_US");
        });
    }

    protected static final Logger LOGGER = LogManager.getLogger("test");
}
