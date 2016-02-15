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
    public SplashMessage make(@NonNull String title, int duration) {
        return make(title, duration, 0);
    }

    public SplashMessage make(@NonNull String title, int duration, int fadeTime) {
        return make(title, fadeTime, duration, fadeTime);
    }

    public SplashMessage make(@NonNull String title, int fadeIn, int duration, int fadeOut) {
        return make(title, null, fadeIn, duration, fadeOut);
    }

    public SplashMessage make(@Nullable String title, @Nullable String subtitle, int duration) {
        return make(title, subtitle, duration, 0);
    }

    public SplashMessage make(@Nullable String title, @Nullable String subtitle, int duration, int fadeTime) {
        return make(title, subtitle, fadeTime, duration, fadeTime);
    }

    public SplashMessage make(@Nullable String title, @Nullable String subtitle, int fadeIn, int duration, int fadeOut) {
        return new SplashMessage(title, subtitle, fadeIn, duration, fadeOut);
    }
    
    public void clear(Player player) {
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
