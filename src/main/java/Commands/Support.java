package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Support implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        if(args.size() > 0) {
            SourceLink.wrongUsage(event.getChannel(),this);
            return;
        }
        EmbedBuilder support = new EmbedBuilder();
        support.setColor(Color.pink);
        support.setThumbnail("https://cdn.discordapp.com/attachments/808220485917474877/848794323465011210/Untitled-1-Recovered_copy.jpg");
        support.addField("Support Server", "If you have any questions or found any bugs, please join: \nhttps://discord.gg/evrNWEmMzH", true);
        support.addField("Buy me a cup of coffee!", "Support me on Patreon!\n" + SourceLink.PATREON_LINK, true);
        event.getChannel().sendMessage(support.build()).queue();
    }

    @Override
    public String getCommand() {
        return "support";
    }

    @Override
    public String getHelp() {
        return "Description: Sends the owner's support server and Patreon.\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + "`";
    }
}
