package com.github.crafttogether.hydroperms;

import com.github.crafttogether.kelp.Kelp;
import com.github.crafttogether.rinku.Connection;
import com.github.crafttogether.rinku.Rinku;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.entity.Player;

public class Guardian {

    public boolean has(Permissions permission, Player player) {
        final Connection connection = Rinku.find(c -> c.getMinecraft().equals(player.getUniqueId().toString()));
        if (connection == null) return false;

        final String guildId = Plugin.getInstance().getConfig().getString("guildId");

        final Guild guild = Kelp.getClient().getGuildById(guildId);
        if (guild == null) throw new IllegalArgumentException("Cannot find a guild with the id " + guildId);
        final Member member = guild.retrieveMemberById(connection.getDiscord()).complete();
        if (member == null) return false;

        final boolean hasRole = member.getRoles().stream().anyMatch(role -> permission.getRolesIds().contains(role.getId()));
        return hasRole;
    }

}
