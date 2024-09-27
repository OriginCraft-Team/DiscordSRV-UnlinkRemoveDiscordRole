package tw.origincraft.Discord_URDR;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public JavaPlugin instance;
    public Listener listener;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig();
        listener = new Listener();
        listener.initize(this);
        DiscordSRV.api.subscribe(new Listener());
    }

    @Override
    public void onDisable() {
        DiscordSRV.api.unsubscribe(new Listener());
    }
}
