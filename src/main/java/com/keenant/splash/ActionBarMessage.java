package com.keenant.splash;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.TitleAction;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ActionBarMessage {
    private final String text;

    public ActionBarMessage(String text) {
        this.text = text;
    }

    public void send(Player player) {
        PacketContainer packet = new PacketContainer(Server.CHAT);
        packet.getTitleActions().write(0, TitleAction.TIMES);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(this.text));
        packet.getBytes().write(0, (byte) 2);
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
