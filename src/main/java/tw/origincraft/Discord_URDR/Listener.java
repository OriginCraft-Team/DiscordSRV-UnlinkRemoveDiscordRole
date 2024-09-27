package tw.origincraft.Discord_URDR;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.AccountUnlinkedEvent;
import github.scarsz.discordsrv.listeners.DiscordAccountLinkListener;
import github.scarsz.discordsrv.util.DiscordUtil;

public class Listener extends DiscordAccountLinkListener {
    public Main plugin;
    private String roleID;

    public void initize(Main plugin) {
        this.plugin = plugin;
        if (plugin.getConfig().getString("roleID") == null) {
            plugin.getConfig().set("roleID", "1221462285810401411");
            plugin.saveConfig();
        }
        roleID = plugin.getConfig().getInt("")
    }

    @Subscribe
    public void accountUnlinked(AccountUnlinkedEvent event) {
        DiscordUtil.removeRolesFromMember();
    }
}
