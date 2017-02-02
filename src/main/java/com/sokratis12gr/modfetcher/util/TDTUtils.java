package com.sokratis12gr.modfetcher.util;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import static com.sokratis12gr.modfetcher.util.TDTRoles.*;

/**
 * ModFetcher created by sokratis12GR
 * - TheDragonTeam
 */
public class TDTUtils {

    public static boolean userPermissions(IMessage message, RolePermissions perm) {
        final IUser user = message.getAuthor();

        switch (perm) {
            case ADMIN:
                return CORE_TEAM.hasRole(user) || SEO.hasRole(user) || CEO.hasRole(user) || CORE_TEAM_PUBLIC.hasRole(user);
            case MEMBER:
                return EVERYONE.hasRole(user) || EVERYONE_PUBLIC.hasRole(user);
        }
        return false;
    }

    public enum RolePermissions {
        ADMIN, MEMBER,;
    }
}
