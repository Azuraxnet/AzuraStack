package net.azura.item.editors.book;

import net.azura.item.editors.ItemEditor;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemBookEditor implements BookEditor {
    private final BookMeta meta;

    public ItemBookEditor(ItemEditor editor) {
        this.meta = (BookMeta) editor.getDefaultItemMeta();
    }

    @Override
    public BookEditor addPage(String page) {
        meta.addPage(page);
        return this;
    }

    @Override
    public BookEditor addPages(String... pages) {
        meta.addPage(pages);
        return this;
    }

    @Override
    public BookEditor setPage(int index, String page) {
        meta.setPage(index, page);
        return this;
    }

    @Override
    public BookEditor setPages(List<String> pages) {
        meta.setPages(pages);
        return this;
    }

    @Override
    public BookEditor removePage(int index) {
        List<String> arr = getPages();
        List<String> pages = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (index == i) continue;
            pages.add(arr.get(i));
        }
        setPages(pages);
        return this;
    }

    @Override
    public BookEditor clearPages() {
        setPages(new ArrayList<>());
        return this;
    }

    @Override
    public BookEditor setBookType(BookMeta.Generation generation) {
        meta.setGeneration(generation);
        return this;
    }

    @Override
    public BookEditor setTitle(String title) {
        meta.setTitle(title);
        return this;
    }

    @Override
    public boolean hasPages() {
        return meta.hasPages();
    }

    @Override
    public boolean hasTitle() {
        return meta.hasTitle();
    }

    @Override
    public boolean hasType() {
        return meta.hasGeneration();
    }

    @Override
    public boolean hasAuthor() {
        return meta.hasAuthor();
    }

    @Override
    public int getPageCount() {
        return meta.getPageCount();
    }

    @Override
    public String getAuthor() {
        return meta.getAuthor();
    }

    @Override
    public String getTitle() {
        return meta.getTitle();
    }

    @Override
    public BookMeta.Generation getBookType() {
        return meta.getGeneration();
    }

    @Override
    public List<String> getPages() {
        return Collections.unmodifiableList(meta.getPages());
    }

    @Override
    public BookMeta getBookMeta() {
        return meta;
    }

    @Override
    public BookEditor setAuthor(String author) {
        meta.setAuthor(author);
        return this;
    }
}
