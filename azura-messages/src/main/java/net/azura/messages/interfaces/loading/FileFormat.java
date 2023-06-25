package net.azura.messages.interfaces.loading;

public interface FileFormat {
    /**
     * This will set the format we will search for when loading files.
     * Placeholder(s):
     * <lang> - This will be the language of the file.
     *
     * e.g. azuramessages_<lang>.properties
     *
     * @param format this is the format the developer wishes to provide us.
     */
    void setFormat(String format);

    /**
     * This will provide the loader with the relevant information to load the file.
     *
     * @return format
     */
    String getFormat();
}
