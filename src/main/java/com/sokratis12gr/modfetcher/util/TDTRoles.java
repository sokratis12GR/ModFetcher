package com.sokratis12gr.modfetcher.util;

import com.sokratis12gr.modfetcher.ModFetcher;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

public enum TDTRoles {

    CEO("226044951035772929"),
    SEO("226044971214438400"),
    CORE_TEAM("226780931476815872"),
    EVERYONE("218315723473158146");

    /**
     * The unique id for the role on a server.
     */
    private final String roleId;

    /**
     * The unique id for the guild this role exists on.
     */
    private final String guildId;

    /**
     * An instance of the role as an IRole object.
     */
    private final IRole role;

    /**
     * An instance of the guild as an IGuild object.
     */
    private final IGuild guild;

    /**
     * Creates a representation of a role from Discord in code. This constructor will assume
     * that the role exists on the public MMD server.
     *
     * @param roleId The Id of the role to represent.
     */
    TDTRoles(String roleId) {

        this(roleId, ModFetcher.TDTG_GUILD_ID);
    }

    /**
     * Creates a representation of a role from Discord in code.
     *
     * @param roleId  The Id of the role to represent.
     * @param guildId The Id of the guild the role is on.
     */
    TDTRoles(String roleId, String guildId) {

        this.roleId = roleId;
        this.guildId = guildId;
        this.guild = ModFetcher.instance.getGuildByID(guildId);
        this.role = this.guild != null ? this.guild.getRoleByID(roleId) : null;
    }

    /**
     * Gets the id of the role being represented.
     *
     * @return The id of the role being represented.
     */
    public String getRoleId() {

        return this.roleId;
    }

    /**
     * Gets the id of the guild that the role exists on.
     *
     * @return The id of the guild that the role exists on.
     */
    public String getGuildId() {

        return this.guildId;
    }

    /**
     * Gets the IRole representation of the role.
     *
     * @return The IRole representation of the role.
     */
    public IRole getRole() {

        return this.role;
    }

    /**
     * Gets the IGuild representation of the guild the role exists on.
     *
     * @return The IGuild representation of the guild the role exists on.
     */
    public IGuild getGuild() {

        return this.guild;
    }

    /**
     * Checks if a user has the role.
     *
     * @param user The user to check permissions of.
     *
     * @return Whether or not they have the role.
     */
    public boolean hasRole(IUser user) {

        if (this.guild != null)
            for (final IRole role : user.getRolesForGuild(this.guild))
                if (role.getID().equals(this.roleId))
                    return true;

        return false;
    }
}