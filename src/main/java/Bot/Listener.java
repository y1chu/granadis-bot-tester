package Bot;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import java.io.IOException;

public class Listener extends ListenerAdapter {

    public final Manager m = new Manager();

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        System.out.println(event.getJDA().getSelfUser().getName() + " is online!");
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        try {
            m.run(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}