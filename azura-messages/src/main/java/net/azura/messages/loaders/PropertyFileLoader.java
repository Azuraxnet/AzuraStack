package net.azura.messages.loaders;

import net.azura.messages.enums.Locale;
import net.azura.messages.format.MessageFileFormat;
import net.azura.messages.interfaces.MessageReferencesProvider;
import net.azura.messages.interfaces.loading.FileFormat;
import net.azura.messages.interfaces.loading.MessageLoader;
import net.azura.messages.matcher.FileMatcher;
import net.azura.messages.messages.MessageReferences;
import net.azura.messages.messages.MessageReferencesPopulator;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class PropertyFileLoader implements MessageLoader {
    private Path path;
    private FileFormat messageFileFormat;
    private boolean write;
    private File file;

    public PropertyFileLoader(Path path, FileFormat messageFileFormat, boolean write) {
        this.path = path;
        this.messageFileFormat = messageFileFormat;
        this.write = write;
    }

    public PropertyFileLoader(Path path, FileFormat messageFileFormat) {
        this(path, messageFileFormat, false);
    }

    public PropertyFileLoader(Path path) {
        this(path, new MessageFileFormat("lang_<lang>"), false);
    }

    public PropertyFileLoader(Path path, boolean write) {
        this(path, new MessageFileFormat("lang_<lang>"), write);
    }

    @Override
    public MessageReferencesProvider load(String identifier) {
        if(StringUtils.isBlank(identifier)) {
            throw new NullPointerException("The identifier specified was either null or empty.");
        }
        String fileName = messageFileFormat.getFormat().replace("<lang>", identifier);
        if(!doesFileExist(fileName)) {
            LOGGER.debug("The file for {} does not exist! Consider using the file initializer to create files.", identifier);
            return null;
        }

        if(!doesLocaleExist(identifier)) {
            LOGGER.warn("The locale {} is not an official locale, and you should use it at your own risk!", identifier);
        }

        Map<String, String> messages = new HashMap<>();

        try(FileReader fileReader = new FileReader(file)) {
            Properties p = new Properties();
            p.load(fileReader);

            for(String s : p.stringPropertyNames()) {
                messages.put(s, p.getProperty(s));
            }
        }catch (IOException e) {
            LOGGER.error("The file {} does not exist!", file.getName(), e);
            e.printStackTrace();
        }
        if(!write)return new MessageReferences(messages);
        return new MessageReferencesPopulator(messages, file);
    }

    private boolean doesFileExist(String fileName) {
        file = new File(path.toString() + "/" + fileName + ".properties");
        return file.exists();
    }

    @Override
    public String[] getSupportedLocalizations() {
        List<String> supportedLocales = new ArrayList<>();
        File[] files = new File(path.toString()).listFiles();
        if(files == null)return new String[0];
        for(File file : files) {
            if(!file.isFile())continue;
            HashMap<String, String> matched = MATCHER.match(file.toString(), messageFileFormat.getFormat());
            String LANG = matched.get("lang");
            if(LANG == null)continue;
            supportedLocales.add(LANG.replace(".properties", ""));
        }

        return supportedLocales.stream().toArray(String[]::new);
    }


    private boolean doesLocaleExist(String identifier) {
        try {
            Locale.valueOf(identifier.toUpperCase());
        }catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    protected static final FileMatcher MATCHER = new FileMatcher('<', '>');
    protected static final Logger LOGGER = LogManager.getLogger(PropertyFileLoader.class);
}
