package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Code implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        // System.out.println(args.size());
        if(!SourceLink.checkNSFW(event)) {
            event.getChannel().sendMessage(SourceLink.error_nsfw_embed().build()).queue();
            return;
        }
        if (args.size() != 1) {
            SourceLink.wrongUsage(event.getChannel(), this);
            return;
        }
        try{
            int code = Integer.parseInt(args.get(0));
            event.getChannel().sendMessage("https://nhentai.net/g/" + code + "/").queue();
        } catch (Exception e) {
            SourceLink.wrongUsage(event.getChannel(), this);
        }

    }

    @Override
    public String getCommand() {
        return "code";
    }

    @Override
    public String getHelp() {
        return "Description: Sends a hentai using the input code! (Code must be numerical numbers)\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + " [Numbers]" + "`";
    }
}
