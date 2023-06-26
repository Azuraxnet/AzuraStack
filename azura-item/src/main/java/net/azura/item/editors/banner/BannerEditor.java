package net.azura.item.editors.banner;

import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

import java.util.List;

public interface BannerEditor {
    BannerEditor addPattern(Pattern pattern);

    BannerEditor setPattern(int i, Pattern pattern);

    BannerEditor addPatterns(Pattern... patterns);

    BannerEditor addPatterns(List<Pattern> patterns);

    BannerEditor clearPatterns();

    BannerEditor removePattern(Pattern pattern);

    BannerEditor removePattern(int i);

    BannerEditor removePatternByType(PatternType type);

    List<Pattern> getPatterns();

    BannerEditor setPatterns(List<Pattern> patterns);

    int getPatternCount();
}