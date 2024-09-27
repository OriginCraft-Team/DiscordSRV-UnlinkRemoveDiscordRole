package tw.origincraft.Discord_URDR;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.AccountUnlinkedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Member;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Role;
import github.scarsz.discordsrv.listeners.DiscordAccountLinkListener;
import github.scarsz.discordsrv.util.DiscordUtil;

public class Listener extends DiscordAccountLinkListener {
    public Main plugin;
    public Role role;

    public void initize(Main plugin) {
        this.plugin = plugin;
        if (plugin.getConfig().getString("roleID") == null) {
            plugin.getConfig().set("roleID", "1221462285810401411");
            plugin.saveConfig();
        }
        String configRoleID = plugin.getConfig().getString("UnlinkRemoveDiscordRole_ID");
        role = DiscordUtil.getRole(configRoleID);
        if (role == null) {
            plugin.getLogger().warning("Role not found! Please check the role ID in the config.");
        }

    }

    @Subscribe
    public void accountUnlinked(AccountUnlinkedEvent event) {
        Member member = DiscordUtil.getMemberById(event.getDiscordId());
        DiscordUtil.removeRolesFromMember(member, role);
    }
}
