package me.kustled.radditions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelCheckEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;

    public LevelCheckEvent(Player player){
        this.player = player;
    }

    public Player getCheckPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
