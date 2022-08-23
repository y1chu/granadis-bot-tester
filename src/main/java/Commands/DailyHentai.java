package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.List;

public class DailyHentai implements CommandInterface {

    public static final Set<String> allChannels = new HashSet<>();

    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        if (SourceLink.checkNSFW(event) && args.size() == 0) {
            // get the channel id then check if its in the list
            String channelID = event.getChannel().getId();
            if (allChannels.contains(channelID)) {
                SendAlreadyExistEmbed(event);
                return;
            }
            // send an embed for conformation
            event.getChannel().sendMessage(SourceLink.ConfirmedBlock().build()).queue();
            // write to file if not exist in list
            Files.write(Paths.get(SourceLink.ALL_CHANNELS), (channelID + "\n").getBytes(), StandardOpenOption.APPEND);
            // get all the channels
            File sources = new File(SourceLink.ALL_CHANNELS);
            Scanner readSource = new Scanner(sources);
            while (readSource.hasNext()) {
                allChannels.add(readSource.nextLine());
            }
            // System.out.println(allChannels);


        } else if (!SourceLink.checkNSFW(event)) {
            event.getChannel().sendMessage(SourceLink.error_nsfw_embed().build()).queue();
        } else {
            SourceLink.wrongUsage(event.getChannel(), this);
        }
    }


    @Override
    public String getCommand() {
        return "hhere";
    }

    @Override
    public String getHelp() {
        return "Description: Sends a random hentai in this textchannel everyday!\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + "`";
    }



    private void SendAlreadyExistEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder existBlock = new EmbedBuilder();
        existBlock.setColor(Color.RED);
        existBlock.setTitle("ERROR: Already exist");
        existBlock.setDescription("This channel is already reciving daily hentai!\n" +
                "To remove the function, use `-hremove`");
        existBlock.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        event.getChannel().sendMessage(existBlock.build()).queue();
    }

}


