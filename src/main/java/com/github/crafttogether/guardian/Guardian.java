package com.github.crafttogether.guardian;

import com.github.crafttogether.kelp.Kelp;
import com.github.crafttogether.rinku.Connection;
import com.github.crafttogether.rinku.Rinku;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.*;

public class Guardian implements Listener {
    public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        setupPermissions(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerLevelChangeEvent event) {
        playerPermissions.remove(event.getPlayer().getUniqueId());
    }

    public void setupPermissions(Player player) {
        final UUID uuid = player.getUniqueId();
        final PermissionAttachment attachment = player.addAttachment(Plugin.getInstance());
        this.playerPermissions.put(uuid, attachment);

        final Connection connection = Rinku.find(c -> c.getMinecraft().equals(uuid.toString()));
        if (connection == null) return;

        final Guild guild = Kelp.getClient().getGuildById(Plugin.getInstance().getConfig().getString("discord.guildId"));
        if (guild == null) throw new IllegalArgumentException("discord.guildId is null");
        final Member member = guild.getMemberById(connection.getDiscord());
        if (member == null) return;

        final List<String> roleIds = member.getRoles().stream().map(ISnowflake::getId).toList();

        final List<Permissions> perms = Arrays.stream(Permissions.values()).toList();
        Collections.reverse(perms);
        for (Permissions perm : perms) {
            if (perm.getRolesIds().stream().anyMatch(roleIds::contains)) {
                perm.applyPermissions(attachment);
            }
        }
    }

}
