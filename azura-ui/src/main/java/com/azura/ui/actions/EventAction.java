package com.azura.ui.actions;

import com.azura.ui.gui.DefaultUserInterface;
import com.azura.ui.gui.UserInterface;
import com.azura.ui.icon.Icon;
import com.azura.ui.screen.Screen;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.InventoryView;

public final class EventAction implements Action {
    private final Player player;
    private final Event event;
    private final Screen screen;
    private final Integer slot;
    private final InventoryView inventory;
    private final UserInterface userInterface;

    private EventAction(ActionBuilder builder) {
        this.player = builder.player;
        this.event = builder.event;
        this.screen = builder.screen;
        this.slot = builder.slot;
        this.inventory = builder.player.getOpenInventory();
        this.userInterface = builder.userInterface;
    }

    public final Player getPlayer() {
        return player;
    }

    public final Event getEvent() {
        return event;
    }

    @Override
    public Integer getSlot() {
        return slot;
    }

    @Override
    public UserInterface getUserInterface() {
        return userInterface;
    }

    @Override
    public Icon getIcon() {
        return screen.getIcon(slot);
    }

    @Override
    public InventoryView getRawInventory() {
        return inventory;
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

    public static class ActionBuilder {
        private Player player;
        private Event event;
        private Screen screen;
        private Integer slot;
        private UserInterface userInterface;

        public ActionBuilder() {
            player = null;
            event = null;
            screen = null;
            slot = null;
            userInterface = null;
        }

        public ActionBuilder setEvent(Event event) {
            this.event = event;
            return this;
        }

        public ActionBuilder setScreen(Screen screen) {
            this.screen = screen;
            return this;
        }

        public ActionBuilder setSlot(int slot) {
            this.slot = slot;
            return this;
        }

        public void setL2UserInterface(UserInterface userInterface) {
            this.userInterface = userInterface;
        }

        public ActionBuilder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        public Player getPlayer() {
            return player;
        }

        public Action build() {
            return new EventAction(this);
        }
    }

    public static ActionBuilder newInstance() {
        return new ActionBuilder();
    }
}