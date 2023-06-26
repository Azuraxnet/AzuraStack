package net.azura.item.editors.book;

import net.azura.item.editors.MetaEditor;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public interface BookMetaEditor extends MetaEditor<BookMetaEditor, BookMeta> {

    /* General */
    BookMetaEditor setBookType(BookMeta.Generation generation);
    BookMetaEditor setTitle(String title);
    BookMetaEditor setAuthor(String author);
    BookMeta.Generation getBookType();
    String getTitle();
    String getAuthor();
    boolean hasTitle();
    boolean hasAuthor();

    /* Pages */
    BookMetaEditor addPage(String page);
    BookMetaEditor addPages(String... pages);
    BookMetaEditor setPage(int index, String page);
    BookMetaEditor setPages(List<String> pages);
    BookMetaEditor setPages(String... pages);
    BookMetaEditor removePage(int index);
    BookMetaEditor clearPages();
    List<String> getPages();
    String getPage(int page);
    int getPageCount();
    boolean hasPages();
}
