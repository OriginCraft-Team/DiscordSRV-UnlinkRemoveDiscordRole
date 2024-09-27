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
    public String configRoleID;

    public Listener(Main plugin) {
        this.plugin = plugin;
    }

    public void initize() {
        configRoleID = plugin.getConfig().getString("roleID");
    }

    @Subscribe
    public void accountUnlinked(AccountUnlinkedEvent event) {
        role = DiscordUtil.getRole(configRoleID);
        if (role == null) {
            // 如果 role 是 null，顯示自訂的錯誤訊息
            System.out.println("Role not found! Please check the role ID in the config.");
            return;
        }

        // 嘗試獲取成員，並進行 null 檢查
        Member member = DiscordUtil.getMemberById(event.getDiscordId());
        if (member == null) {
            System.out.println("Member not found for Discord ID: " + event.getDiscordId());
            return;
        }

        // 移除角色
        try {
            DiscordUtil.removeRolesFromMember(member, role);
            System.out.println("Successfully removed the role from the member.");
        } catch (Exception e) {
            System.out.println("An error occurred while removing the role: " + e.getMessage());
        }
    }
}
