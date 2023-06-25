package net.azura.messages.messages;

import net.azura.messages.interfaces.MessageReferencesProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

public class MessageReferencesPopulator implements MessageReferencesProvider {

    public MessageReferencesPopulator(Map<String, String> references, File file) {
        this.references = references;
        this.file = file;

        try(FileReader fileReader = new FileReader(file)) {
            Properties p = new Properties();
            p.load(fileReader);

            properties = p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String key) {
        if(StringUtils.isBlank(key)) {
            throw new NullPointerException("The message key specified was either null or empty.");
        }
        if(references.containsKey(key)) {
            return references.get(key);
        }
        save(key, "");
        return references.get(key);
    }

    public void save(String key, String value) {
        if(StringUtils.isBlank(key)) {
            throw new NullPointerException("The message key specified was either null or empty.");
        }
        if(references.containsKey(key))return;
        references.put(key, value);
        try(OutputStream fileWriter = Files.newOutputStream(file.toPath())) {
            properties.setProperty(key, value);
            properties.store(fileWriter, null);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final Map<String, String> references;
    protected final File file;
    protected final Properties properties;
}
