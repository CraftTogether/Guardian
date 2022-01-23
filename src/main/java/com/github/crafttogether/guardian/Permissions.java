package com.github.crafttogether.guardian;

import java.util.List;

public enum Permissions {

    ADMIN("administrators"),
    MOD("moderators");

    private final String key;

    Permissions(String key) {
        this.key = key;
    }

    public List<String> getRolesIds() {
        return Plugin.getInstance().getConfig().getStringList(this.key);
    }

}
