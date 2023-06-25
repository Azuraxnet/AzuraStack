package net.azura.messages.loaders;

import net.azura.messages.interfaces.MessageReferencesProvider;
import net.azura.messages.interfaces.loading.FileFormat;
import net.azura.messages.interfaces.loading.MessageLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * If the file doesn't exist the file populator will automatically create one.
 */
public class PropertyFileInitializer implements MessageLoader {
    private MessageLoader messageLoader;
    private Path path;
    private  FileFormat fileFormat;

    public PropertyFileInitializer(Path path, FileFormat messageFileFormat, MessageLoader loader) {
        this.messageLoader = loader;
        this.path = path;
        this.fileFormat = messageFileFormat;
    }

    @Override
    public MessageReferencesProvider load(String identifier) {
        String fileName = fileFormat.getFormat().replace("<lang>", identifier);
        File file = new File(path.toString() + "/" + fileName + ".properties");
        try {
            file.createNewFile();
            return messageLoader.load(identifier);
        } catch (IOException e) {
            System.out.println("An error occurred when attempting to create the file.");
            return null;
        }
    }

    @Override
    public String[] getSupportedLocalizations() {
        return messageLoader.getSupportedLocalizations();
    }
}
