package BackEnd;

import Commands.DailyHentai;
import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.List;

public class RecQueueSolver extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event)  {
        if (event.getChannel().equals(event.getJDA().getTextChannelById("875322487846608966")) && !event.getUser().isBot()) {

            String messageID = event.getMessageId();
            Message message = event.getChannel().retrieveMessageById(messageID).complete();
            String[] getCode = message.getEmbeds().get(0).getTitle().split(" ");
            String code = getCode[3];

            if(event.getReaction().toString().contains("RE:U+2764")) {
                event.getChannel().sendMessage(hentaiResult("database", code).build()).queue();
                try {
                    RewriteRec(code, SourceLink.FAVORITES);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message.delete().queue();


            } else if (event.getReaction().toString().contains("RE:U+274c")) {
                event.getChannel().sendMessage(hentaiResult("banned", code).build()).queue();
                try {
                    RewriteRec(code, SourceLink.BANNED);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message.delete().queue();

            }
        }
    }

    private void RewriteRec(String sourceCode, String targetFile) throws IOException {
        File sources = new File(SourceLink.REC);
        Scanner readSource = new Scanner(sources);
        Set<String> recCodes = new HashSet<>();
        while(readSource.hasNext()) {
            recCodes.add(readSource.nextLine());
        }
        recCodes.remove(sourceCode);
        PrintWriter writer = new PrintWriter(SourceLink.REC);
        writer.print("");
        writer.close();
        Iterator<String> iterator = recCodes.iterator();
        for (int i = 0; i < recCodes.size(); i++) {
            Files.write(Paths.get(SourceLink.REC), (iterator.next() + "\n").getBytes(), StandardOpenOption.APPEND);
        }
        Files.write(Paths.get(targetFile), (sourceCode + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    private EmbedBuilder hentaiResult(String result, String code) {
        EmbedBuilder res = new EmbedBuilder();
        res.setColor(Color.green);
        res.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        res.setTitle("Success! Hentai " + result + "!");
        res.setDescription(code + " was added to " + result + " source.");
        return res;
    }

}
