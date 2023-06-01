package com.azura.ui.object;

import com.azura.ui.ItemBuilder;
import com.azura.ui.gui.DefaultUserInterface;
import com.azura.ui.icon.DefaultIcon;
import com.azura.ui.screen.PaginatedScreen;
import com.azura.ui.screen.Screen;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public abstract class PaginatedMenu extends DefaultUserInterface {
    private PaginatedScreen screen;
    public PaginatedMenu(String name){
        this.screen = new PaginatedScreen(getDefaultPreviousIcon(), getDefaultNextIcon());
        this.screen.setName(name);
    }

    protected PaginatedScreen getScreen(){
        return screen;
    }
    private DefaultIcon getDefaultPreviousIcon(){
        return new DefaultIcon(new ItemBuilder(Material.PAPER).setDisplayName("&cPrevious").build()).setActionHandler(action ->{
            if(action.getEvent() instanceof Cancellable){
                ((Cancellable) action.getEvent()).setCancelled(true);
            }
            screen.previousPage();
            getL2UI().fullRefresh();
        });
    }
    private DefaultIcon getDefaultNextIcon(){
        return new DefaultIcon(new ItemBuilder(Material.PAPER).setDisplayName("&cNext").build()).setActionHandler(action ->{
            if(action.getEvent() instanceof Cancellable){
                ((Cancellable) action.getEvent()).setCancelled(true);
            }
            screen.nextPage();
            getL2UI().fullRefresh();
        });
    }



    @Override
    public Screen getScreen(Player player) {
        return screen;
    }

}