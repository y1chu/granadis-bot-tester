package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Recommend implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        try {
            if (args.size() > 1) {
                SourceLink.wrongUsage(event.getChannel(), this);
                return;
            }
            int code = Integer.parseInt(args.get(0));
            // if in database
            // if in banned
            // if in queue

            Files.write(Paths.get(SourceLink.REC), (code + "\n").getBytes(), StandardOpenOption.APPEND);
            EmbedBuilder recieved = new EmbedBuilder();
            recieved.setColor(Color.green);
            recieved.setTitle("Success! " + code + " added to the queue!");
            recieved.setDescription("Thanks! Your recommendation has been recorded.");
            recieved.setThumbnail("https://cdn.discordapp.com/emojis/836487759619293234.webp?size=240&quality=lossless");
            event.getChannel().sendMessage(recieved.build()).queue();

        } catch (Exception e) {
            SourceLink.wrongUsage(event.getChannel(), this);
        }

    }

    @Override
    public String getCommand() {
        return "rec";
    }

    @Override
    public String getHelp() {
        return "Description: Gives the owner a hentai recommendation!\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + " [Code]`";
    }
}
