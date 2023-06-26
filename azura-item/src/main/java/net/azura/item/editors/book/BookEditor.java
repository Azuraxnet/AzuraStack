package net.azura.item.editors.book;

import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public interface BookEditor {
    BookEditor addPage(String page);

    BookEditor addPages(String... pages);

    BookEditor setPage(int index, String page);

    BookEditor removePage(int index);

    BookEditor clearPages();

    boolean hasPages();

    boolean hasTitle();

    boolean hasType();

    boolean hasAuthor();

    int getPageCount();

    String getAuthor();

    BookEditor setAuthor(String author);

    String getTitle();

    BookEditor setTitle(String title);

    BookMeta.Generation getBookType();

    BookEditor setBookType(BookMeta.Generation generation);

    List<String> getPages();

    BookEditor setPages(List<String> pages);

    BookMeta getBookMeta();
}