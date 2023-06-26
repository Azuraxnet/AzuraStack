package net.azura.messages.format;

import net.azura.messages.interfaces.loading.FileFormat;

public class MessageFileFormat implements FileFormat {
    private String format;

    public MessageFileFormat(String format) {
        this.format = format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String getFormat() {
        return format;
    }
}
