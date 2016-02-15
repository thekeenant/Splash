package com.keenant.splash;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.TitleAction;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class SplashMessage {
    @Getter private String title;
    @Getter private String subtitle;
    @Getter private int fadeIn;
    @Getter private int duration;
    @Getter private int fadeOut;

    public SplashMessage(String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
    }

    public int getTotalDuration() {
        return this.fadeIn + this.duration + this.fadeOut;
    }

    public SplashMessage withTitle(String title) {
        this.title = title;
        return this;
    }

    public SplashMessage withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public SplashMessage withFadeIn(int ticks) {
        this.fadeIn = ticks;
        return this;
    }

    public SplashMessage withDuration(int ticks) {
        this.duration = ticks;
        return this;
    }

    public SplashMessage withFadeOut(int ticks) {
        this.fadeOut = ticks;
        return this;
    }

    public SplashMessage withFade(int ticks) {
        this.fadeIn = ticks;
        this.fadeOut = ticks;
        return this;
    }

    public void send(Player player) {
        setTitle(player);
        setSubtitle(player);
        setTimes(player);
    }

    private void setTitle(Player player) {
        PacketContainer packet = new PacketContainer(Server.TITLE);
        packet.getTitleActions().write(0, TitleAction.TITLE);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(this.title == null ? "" : this.title));
        sendPacket(player, packet);
    }

    private void setSubtitle(Player player) {
        PacketContainer packet = new PacketContainer(Server.TITLE);
        packet.getTitleActions().write(0, TitleAction.SUBTITLE);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(this.subtitle == null ? "" : this.subtitle));
        sendPacket(player, packet);
    }

    private void setTimes(Player player) {
        PacketContainer packet = new PacketContainer(Server.TITLE);
        packet.getTitleActions().write(0, TitleAction.TIMES);
        packet.getIntegers().write(0, this.fadeIn);
        packet.getIntegers().write(1, this.duration);
        packet.getIntegers().write(2, this.fadeOut);
        sendPacket(player, packet);
    }

    private void sendPacket(Player player, PacketContainer packet) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
