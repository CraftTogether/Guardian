package com.github.crafttogether.guardian;

import org.bukkit.permissions.PermissionAttachment;

import java.util.List;
import java.util.function.Function;

public enum Permissions {

    ADMIN("administrators", perms -> {
        perms.setPermission("mod", true);
        perms.setPermission("dev", true);
        perms.setPermission("admin", true);
        return perms;
    }),
    DEV("developers", perms -> {
        perms.setPermission("mod", true);
        perms.setPermission("dev", true);
        perms.setPermission("admin", false);
        return perms;
    }),
    MOD("moderators", perms -> {
        perms.setPermission("mod", true);
        perms.setPermission("dev", false);
        perms.setPermission("admin", false);
        return perms;
    });

    private final String key;
    private final Function<PermissionAttachment, PermissionAttachment> permissionAttachment;

    Permissions(String key, Function<PermissionAttachment, PermissionAttachment> permissions) {
        this.key = key;
        this.permissionAttachment = permissions;
    }

    public List<String> getRolesIds() {
        return Plugin.getInstance().getConfig().getStringList("discord." + this.key);
    }

    public PermissionAttachment applyPermissions(PermissionAttachment permissionAttachment) {
        return this.permissionAttachment.apply(permissionAttachment);
    }

}
