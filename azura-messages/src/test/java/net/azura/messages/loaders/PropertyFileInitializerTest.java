package net.azura.messages.loaders;

import net.azura.messages.format.MessageFileFormat;
import net.azura.messages.interfaces.loading.FileFormat;
import net.azura.messages.loaders.PropertyFileInitializer;
import net.azura.messages.loaders.PropertyFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class PropertyFileInitializerTest {
    private File file;
    private PropertyFileInitializer propertyFileInitializer;
    private FileFormat fileFormat;
    private Path path;

    @Before
    public void init() {
        String currentDir = System.getProperty("user.dir");
        path = Paths.get(currentDir + "\\target\\");
        fileFormat = new MessageFileFormat("lang_<lang>");

        propertyFileInitializer = new PropertyFileInitializer(path, fileFormat, new PropertyFileLoader(path, fileFormat, true));
    }

    @Test
    public void testInitializeFileAndValidateExists() {
        String fileName = generateString();
        LOGGER.info("Attempting to initialize a lang file called {}.properties", fileName);
        propertyFileInitializer.load(fileName);

        file = new File(path + "/" + fileFormat.getFormat().replace("<lang>", fileName) + ".properties");

        assertTrue(file.exists());
    }

    @After
    public void deleteFile() {
        file.delete();
    }

    public static String generateString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    protected static final Logger LOGGER = LogManager.getLogger("test");
}
