package net.azura.newitem.general;

import java.util.List;

public interface ItemRepresentation {
    /* Lore */
    void addLoreLine(String lore);
    void setLore(String... lore);
    void setLore(List<String> lore);
    void clearLore();
    void setLore(int line, String lore);
    String getLore(int line);
    List<String> getLore();
    int getLoreSize();

    /* Display Name */
    void setDisplayName(String displayName);
    String getDisplayName();
}
