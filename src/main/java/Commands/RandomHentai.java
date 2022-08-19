package Commands;
import Sources.SourceLink;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.List;
import Interface.CommandInterface;

public class RandomHentai implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) {

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {

    }

    @Override
    public String getCommand() {
        return "rhentai";
    }

    @Override
    public String getHelp() {
        return "Gives you a random meme!\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + "`";
    }
}
