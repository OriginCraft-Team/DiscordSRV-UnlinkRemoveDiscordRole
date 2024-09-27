package tw.origincraft.Discord_URDR;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public Listener listener;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        listener = new Listener(this);
        listener.initize();
        DiscordSRV.api.subscribe(listener);
    }

    @Override
    public void onDisable() {
        DiscordSRV.api.unsubscribe(listener);
    }
}
