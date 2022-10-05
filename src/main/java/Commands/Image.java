package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.io.IOException;
import java.util.List;

public class Image implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws IOException {
        if(!SourceLink.checkNSFW(event)) {
            event.getChannel().sendMessage(SourceLink.error_nsfw_embed().build()).queue();
            return;
        }
        if (args.size() != 0) {
            SourceLink.wrongUsage(event.getChannel(), this);
            return;
        }
        int rngCode = SourceLink.rngPublic.nextInt(5629447) + 1;
        event.getChannel().sendMessage("https://danbooru.donmai.us/posts/" + rngCode).queue();
    }

    @Override
    public String getCommand() {
        return "image";
    }

    @Override
    public String getHelp() {
        return "Description: Sends a random image in this textchannel! (can be SFW)\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() +  "`";
    }
}
