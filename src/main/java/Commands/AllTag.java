package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class AllTag implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        boolean isNSFW = SourceLink.checkNSFW(event);
        if (args.size() == 0 && isNSFW) {
            EmbedBuilder tagBlock = new EmbedBuilder();
            File sources = new File(SourceLink.ALL_TAGS);
            tagBlock.setThumbnail("https://cdn.discordapp.com/emojis/836835643636056075.webp?size=240&quality=lossless");
            tagBlock.setColor(Color.PINK);
            tagBlock.setFooter("Made by Granadis#8876", "https://i.imgur.com/qrMk89m.jpg");
            SourceLink.ReadAndEmbed(tagBlock, sources);
            event.getChannel().sendMessage(tagBlock.build()).queue();
        }
        else if (!isNSFW) {
            event.getChannel().sendMessage(SourceLink.error_nsfw_embed().build()).queue();
        } else {
            SourceLink.wrongUsage(event.getChannel(), this);
        }

    }

    @Override
    public String getCommand() {
        return "alltag";
    }

    @Override
    public String getHelp() {
        return "Description: Displays all tags.\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + "`";
    }

}
