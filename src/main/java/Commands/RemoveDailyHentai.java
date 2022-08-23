package Commands;

import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;

public class RemoveDailyHentai implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        String channel = event.getChannel().getId();
        if (!DailyHentai.allChannels.contains(channel) && args.size() == 0) {
            event.getChannel().sendMessage(SourceLink.error_does_not_exist().build()).queue();
            return;
        }
        if (SourceLink.checkNSFW(event) && DailyHentai.allChannels.contains(channel) && args.size() == 0) {
            DailyHentai.allChannels.remove(channel);
            PrintWriter writer = new PrintWriter(SourceLink.ALL_CHANNELS);
            writer.print("");
            writer.close();
            Iterator<String> iterator = DailyHentai.allChannels.iterator();
            for (int i = 0; i < DailyHentai.allChannels.size(); i++) {
                Files.write(Paths.get(SourceLink.ALL_CHANNELS), (iterator.next() + "\n").getBytes(), StandardOpenOption.APPEND);
            }
            event.getChannel().sendMessage(SourceLink.removeHentai().build()).queue();
        }
    }

    @Override
    public String getCommand() {
        return "hremove";
    }

    @Override
    public String getHelp() {
        return "Description: Removes daily hentai for this text channel.\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + "`";
    }
}
