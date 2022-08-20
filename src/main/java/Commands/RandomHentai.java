package Commands;
import BackEnd.GenerateHentai;
import BackEnd.TagPath;
import Sources.SourceLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.io.IOException;
import java.util.List;

import Interface.CommandInterface;

public class RandomHentai implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws IOException {
        try {
            boolean isNSFW = SourceLink.checkNSFW(event);
            if(isNSFW && args.size() == 0) {
                event.getChannel().sendMessage(GenerateHentai.generateHentaiFromSource(SourceLink.FAVORITES)).queue();
            } else if (isNSFW && args.size() == 1) {
                String tag = TagPath.tagChoice(args.get(0));
                if (tag == null) {
                    event.getChannel().sendMessage("Tag does not exist. Try another one or use `-alltag`.").queue();
                    return;
                }
                event.getChannel().sendMessage(GenerateHentai.generateHentaiFromSource(tag)).queue();
            } else if (!isNSFW){
                event.getChannel().sendMessage(SourceLink.error_nsfw_embed().build()).queue();
            } else {
                SourceLink.wrongUsage(event.getChannel(), this);
            }
        } catch(Exception e) {
            SourceLink.errorLogger(e);
            SourceLink.wrongUsage(event.getChannel(), this);
        }
    }

    @Override
    public String getCommand() {
        return "rhentai";
    }

    @Override
    public String getHelp() {
        return "Description: Gives you a random hentai from my database!\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + " [Optional_Tag]" + "`";
    }
}
