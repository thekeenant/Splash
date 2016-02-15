package com.keenant.splash;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.TitleAction;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import lombok.NonNull;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class SplashBuilder {
    public ActionBarMessage makeActionBar(@Nullable String text) {
        return new ActionBarMessage(text);
    }
    
    public SplashMessage makeSplash(@NonNull String title, int duration) {
        return makeSplash(title, duration, 0);
    }

    public SplashMessage makeSplash(@NonNull String title, int duration, int fadeTime) {
        return makeSplash(title, fadeTime, duration, fadeTime);
    }

    public SplashMessage makeSplash(@NonNull String title, int fadeIn, int duration, int fadeOut) {
        return makeSplash(title, null, fadeIn, duration, fadeOut);
    }

    public SplashMessage makeSplash(@Nullable String title, @Nullable String subtitle, int duration) {
        return makeSplash(title, subtitle, duration, 0);
    }

    public SplashMessage makeSplash(@Nullable String title, @Nullable String subtitle, int duration, int fadeTime) {
        return makeSplash(title, subtitle, fadeTime, duration, fadeTime);
    }

    public SplashMessage makeSplash(@Nullable String title, @Nullable String subtitle, int fadeIn, int duration, int fadeOut) {
        return new SplashMessage(title, subtitle, fadeIn, duration, fadeOut);
    }

    public void clearActionBar(Player player) {
        makeActionBar(null).send(player);
    }
    
    public void clearSplash(Player player) {
        PacketContainer packet = new PacketContainer(Server.TITLE);
        packet.getTitleActions().write(0, TitleAction.RESET);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(""));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
