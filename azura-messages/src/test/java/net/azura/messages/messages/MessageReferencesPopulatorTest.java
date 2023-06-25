package net.azura.messages.messages;

import net.azura.messages.AzuraMessages;
import net.azura.messages.format.MessageFileFormat;
import net.azura.messages.interfaces.loading.FileFormat;
import net.azura.messages.loaders.PropertyFileInitializer;
import net.azura.messages.loaders.PropertyFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static net.azura.messages.loaders.PropertyFileInitializerTest.generateString;

public class MessageReferencesPopulatorTest {
    private File file;
    private AzuraMessages azuraMessages;

    @Before
    public void init() {
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir + "\\target\\");
        FileFormat fileFormat = new MessageFileFormat("lang_<lang>");
        azuraMessages = new AzuraMessages(new PropertyFileInitializer(path, fileFormat, new PropertyFileLoader(path, fileFormat, true)), "en_US");

        String generatedFileName = generateString();
        file = new File(path + "/" + fileFormat.getFormat().replace("<lang>", generatedFileName) + ".properties");
        azuraMessages.setLocalization(generatedFileName);
    }

    @Test
    public void testPopulationAndValidateIsInFile() {
        String messageKey = generateString();
        azuraMessages.getMessageReferences().get(messageKey);
        try(FileReader fileReader = new FileReader(file)) {
            Properties properties = new Properties();
            properties.load(fileReader);

            assertEquals(properties.getProperty(messageKey), "");
        } catch (IOException e) {
            fail("The file does not exist, so we cannot check whether the property exists.");
        }
    }

    @After
    public void deleteFile() {
        file.delete();
    }
}
