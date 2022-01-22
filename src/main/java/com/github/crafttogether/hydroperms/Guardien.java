package com.github.crafttogether.hydroperms;

import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

public class Guardien {

    public boolean has(Permissions permission, Player player) {
        final JSONArray members = Members.get();
        for (int i = 0; i < members.length(); i++) {
            final JSONObject data = members.getJSONObject(i);

            final String discordId = data.getString("discord");
            final String minecraftUuid = data.getString("minecraft");

            // TODO Actual check

        }

        return false;
    }

}
