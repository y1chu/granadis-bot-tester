package Interface;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public interface CommandInterface {

    void run(List<String> args, GuildMessageReceivedEvent event);
    void onSlashCommand(SlashCommandEvent event);
    String getCommand();
    String getHelp();

}