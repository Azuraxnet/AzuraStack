package com.azura.item.builder;

import com.azura.item.editors.BannerEditor;
import com.azura.item.editors.ItemEditor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemBannerEditor implements BannerEditor {
    private final BannerMeta meta;

    ItemBannerEditor(ItemEditor editor) {
        this.meta = (BannerMeta) editor.getDefaultItemMeta();
    }

    @Override
    public BannerEditor addPattern(Pattern pattern) {
        meta.addPattern(pattern);
        return this;
    }

    @Override
    public BannerEditor setPattern(int i, Pattern pattern) {
        meta.setPattern(i, pattern);
        return this;
    }

    @Override
    public BannerEditor addPatterns(Pattern... patterns) {
        for (Pattern p : patterns) {
            addPattern(p);
        }
        return this;
    }

    @Override
    public BannerEditor addPatterns(List<Pattern> patterns) {
        for (Pattern p : patterns) {
            addPattern(p);
        }
        return this;
    }

    @Override
    public BannerEditor clearPatterns() {
        meta.setPatterns(new ArrayList<>());
        return this;
    }

    @Override
    public BannerEditor setPatterns(List<Pattern> patterns) {
        meta.setPatterns(patterns);
        return this;
    }

    @Override
    public BannerEditor removePattern(Pattern pattern) {
        List<Pattern> patterns = getPatterns();
        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = patterns.get(i);
            if (p.getPattern() != pattern.getPattern()) {
                continue;
            }
            if (p.getColor() == pattern.getColor()) {
                removePattern(i);
            }
        }
        return this;
    }

    @Override
    public BannerEditor removePatternByType(PatternType type) {
        List<Pattern> patterns = getPatterns();
        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = patterns.get(i);
            if (p.getPattern() == type) {
                removePattern(i);
            }
        }
        return this;
    }

    @Override
    public List<Pattern> getPatterns() {
        return Collections.unmodifiableList(meta.getPatterns());
    }

    @Override
    public BannerEditor removePattern(int i) {
        meta.removePattern(i);
        return this;
    }

    @Override
    public int getPatternCount() {
        return meta.numberOfPatterns();
    }

}
