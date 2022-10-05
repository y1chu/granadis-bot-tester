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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recommend implements CommandInterface {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        try {
            if(!SourceLink.checkNSFW(event)) {
                event.getChannel().sendMessage(SourceLink.error_nsfw_embed().build()).queue();
                return;
            }
            if (args.size() > 1) {
                SourceLink.wrongUsage(event.getChannel(), this);
                return;
            }
            int code = Integer.parseInt(args.get(0));
            if (checkInFile(SourceLink.BANNED, code)) {
                event.getChannel().sendMessage(exist(" banned source").build()).queue();
                return;
            }
            if (checkInFile(SourceLink.FAVORITES, code)) {
                event.getChannel().sendMessage(exist(" the database").build()).queue();
                return;
            }
            if (checkInFile(SourceLink.REC, code)) {
                event.getChannel().sendMessage(exist(" the queue").build()).queue();
                return;
            }

            Files.write(Paths.get(SourceLink.REC), (code + "\n").getBytes(), StandardOpenOption.APPEND);
            EmbedBuilder recieved = new EmbedBuilder();
            recieved.setColor(Color.green);
            recieved.setTitle("Success! " + code + " added to the queue!");
            recieved.setDescription("Thanks! Your recommendation has been recorded.");
            recieved.setThumbnail("https://cdn.discordapp.com/emojis/836487759619293234.webp?size=240&quality=lossless");
            event.getChannel().sendMessage(recieved.build()).queue();

            EmbedBuilder adminBlock = new EmbedBuilder();
            adminBlock.setTitle("Hentai code recieved: " + code);
            adminBlock.setColor(Color.pink);
            adminBlock.setDescription("Admins, you've received a hentai recommendation!\n" +
                    "Use the reactions to add it to my database or ban it.");
            adminBlock.setThumbnail("https://cdn.discordapp.com/emojis/836487759619293234.webp?size=240&quality=lossless");

            event.getJDA().getTextChannelById("875322487846608966").sendMessage("https://nhentai.net/g/" + code + "/").queue();
            SourceLink.sendMessageWithReactions(event.getJDA().getTextChannelById("875322487846608966"), adminBlock.build(), "U+2764", "U+274C");


        } catch (Exception e) {
            SourceLink.wrongUsage(event.getChannel(), this);
        }

    }

    public EmbedBuilder exist(String existedText) {
        EmbedBuilder existedBlock = new EmbedBuilder();
        existedBlock.setColor(Color.red);
        existedBlock.setTitle("ERROR");
        existedBlock.setDescription("This hentai is already in " + existedText + ".");
        existedBlock.setThumbnail("https://cdn.discordapp.com/emojis/836475858843729920.webp?size=240&quality=lossless");
        return existedBlock;
    }

    public boolean checkInFile(String file, int code) throws FileNotFoundException {
        File sources = new File(file);
        Scanner readSource = new Scanner(sources);
        List<String> sourceAsList = new ArrayList<>();
        while (readSource.hasNext()) {
            sourceAsList.add(readSource.nextLine());
        }
        String codeInString = Integer.toString(code);
        return sourceAsList.contains(codeInString);
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
