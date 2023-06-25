package com.azura.item.meta;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.awt.print.Book;
import java.util.List;

public class ExampleBookMetaEditor extends AbstactMetaEditor<ExampleBookMetaEditor, BookMeta> implements BookMetaEditor{
    private final BookMeta meta;
    private BasicMetaEditor<BookMeta> metaEditor;
    public ExampleBookMetaEditor(BookMeta meta){
        this.meta = meta;
        this.metaEditor =  new BasicMetaEditor<>(meta);
    }
    @Override
    public BookMetaEditor setBookType(BookMeta.Generation generation) {
        return null;
    }

    @Override
    public BookMetaEditor setTitle(String title) {
        return null;
    }

    @Override
    public BookMetaEditor setAuthor(String author) {
        return null;
    }

    @Override
    public BookMeta.Generation getBookType() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public boolean hasTitle() {
        return false;
    }

    @Override
    public boolean hasAuthor() {
        return false;
    }

    @Override
    public BookMetaEditor addPage(String page) {
        return null;
    }

    @Override
    public BookMetaEditor addPages(String... pages) {
        return null;
    }

    @Override
    public BookMetaEditor setPage(int index, String page) {
        return null;
    }

    @Override
    public BookMetaEditor setPages(List<String> pages) {
        return null;
    }

    @Override
    public BookMetaEditor setPages(String... pages) {
        return null;
    }

    @Override
    public BookMetaEditor removePage(int index) {
        return null;
    }

    @Override
    public BookMetaEditor clearPages() {
        return null;
    }

    @Override
    public List<String> getPages() {
        return null;
    }

    @Override
    public String getPage(int page) {
        return null;
    }

    @Override
    public int getPageCount() {
        return 0;
    }

    @Override
    public boolean hasPages() {
        return false;
    }

    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public BookMetaEditor setTag(@Nonnull Plugin plugin, @Nonnull String key, @Nonnull String value) {
        return null;
    }

    @Override
    public BookMetaEditor clearTags(@Nonnull Plugin plugin, @Nonnull String key) {
        return null;
    }

    @Override
    public String getTag(@Nonnull Plugin plugin, @Nonnull String key) {
        return null;
    }

    @Override
    public BookMetaEditor addLoreLine(String lore) {
        return null;
    }

    @Override
    public BookMetaEditor setLore(String... lore) {
        return null;
    }

    @Override
    public BookMetaEditor setLore(List<String> lore) {
        return null;
    }

    @Override
    public BookMetaEditor clearLore() {
        return null;
    }

    @Override
    public BookMetaEditor setLore(int line, String lore) {
        return null;
    }

    @Override
    public String getLore(int line) {
        return null;
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public int getLoreSize() {
        return 0;
    }

    @Override
    public BookMetaEditor setDisplayName(String displayName) {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public ItemStack build() {
        return null;
    }


    @Override
    public BookMeta getRawMeta() {
        return this.meta.clone();
    }
}
