package Sources;

import Interface.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SourceLink {

    public static Random rngPublic = new Random();
    // public static String BOT_TOKEN = System.getenv("TOKEN");
    public static String BOT_TOKEN = "ODY3ODg0ODAxNDY4OTIzOTQ0.Ga_NEv.DUn8fu56VlbwGAxa-dwr5Z7gF5Ktz5YIBK2H-Q";
    public final static String BOT_PREFIX = "-";
    public final static String OWNERID = "714284046892400731";
    public final static String OWNER_SERVER = "807846011627110471";
    public final static String PATREON_LINK = "https://patreon.com/Granadis_Bot?utm_medium=clipboard_copy&utm_source=copyLink&utm_campaign=creatorshare_creator";

    //paths
    public static final String ERROR_LOGS = "src/main/java/BackEnd/ErrorLogs.txt";
    public static final String ALL_CHANNELS = "src/main/java/BackEnd/AllRecevingChannel.txt";
    public static final String ALL_TAGS = "src/main/java/Sources/AllTags.txt";
    public static final String FAVORITES = "src/main/java/Sources/Favorites.txt";
    public static final String BANNED = "src/main/java/Sources/Banned.txt";
    public static final String REC = "src/main/java/Sources/Rec.txt";

    // Old data
    public static final String CHECK_DUPLICATE = "/Users/apple/Documents/GitHub/GranadisBot/src/main/java/AllSource/CheckFileDuplication.txt";
    public static final String ALL_SOURCE = "/Users/apple/Documents/GitHub/GranadisBot/src/main/java/AllSource/Source.txt";

    public static final String AHEGAO = "src/main/java/Sources/Tags/Ahegao.txt";
    public static final String BIG_BREASTS = "src/main/java/Sources/Tags/Big Breasts.txt";
    public static final String BUSINESS_SUIT = "src/main/java/Sources/Tags/Business Suit.txt";
    public static final String CORRUPTION = "src/main/java/Sources/Tags/Corruption.txt";
    public static final String DEFLORATION = "src/main/java/Sources/Tags/Defloration.txt";
    public static final String IMPREGNATION = "src/main/java/Sources/Tags/Impregnation.txt";
    public static final String INCEST = "src/main/java/Sources/Tags/Incest.txt";
    public static final String LOLI = "src/main/java/Sources/Tags/Loli.txt";
    public static final String MASTURBATION = "src/main/java/Sources/Tags/Masturbation.txt";
    public static final String MILF = "src/main/java/Sources/Tags/MILF.txt";
    public static final String NAKADASHI = "src/main/java/Sources/Tags/Nakadashi.txt";
    public static final String SCHOOL_UNIFORM = "src/main/java/Sources/Tags/School Uniform.txt";
    public static final String SOLE_FEMALE = "src/main/java/Sources/Tags/Sole Female.txt";
    public static final String SOLE_MALE = "src/main/java/Sources/Tags/Sole Male.txt";
    public static final String THREESOME = "src/main/java/Sources/Tags/Threesome.txt";
    public static final String UNCENSORED = "src/main/java/Sources/Tags/Uncensored.txt";

    public static boolean checkNSFW(GuildMessageReceivedEvent event) {
        return event.getChannel().isNSFW();
    }

    public static void wrongUsage(TextChannel tc, CommandInterface c) {
        EmbedBuilder wrongUsage = new EmbedBuilder();
        wrongUsage.setColor(Color.red);
        wrongUsage.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        wrongUsage.setTitle("ERROR: Wrong Command Usage!");
        wrongUsage.setDescription(c.getHelp());
        tc.sendMessage(wrongUsage.build()).queue();
    }


    public static void errorLogger(Exception e) throws IOException {
        String error = e.getMessage();
        Files.write(Paths.get(SourceLink.ERROR_LOGS), (error + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    public static EmbedBuilder error_nsfw_embed() {
        EmbedBuilder nsfw_error = new EmbedBuilder();
        nsfw_error.setColor(Color.red);
        nsfw_error.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        nsfw_error.setTitle("ERROR: NSFW COMMANDS ONLY WORK IN NSFW CHANNELS!");
        nsfw_error.setDescription("Please review: https://techwiser.com/make-nsfw-channel-discord/ on how to make a NSFW channel!");
        return nsfw_error;
    }

    public static void ReadAndEmbed(EmbedBuilder embed, File readTags) throws FileNotFoundException {
        Scanner reader = new Scanner(readTags);
        StringBuilder tagFromFile = new StringBuilder();
        while (reader.hasNext()) {
            tagFromFile.append(reader.nextLine()).append("\n");
        }
        embed.addField("All Available Tags:", tagFromFile.toString(), true);

    }

    public static EmbedBuilder ConfirmedBlock() {
        EmbedBuilder toReturn = new EmbedBuilder();
        toReturn.setColor(Color.green);
        toReturn.setTitle("Success: Daily Hentai");
        toReturn.setDescription("You're all set! Daily hentai will be posted here every <t:1661320800:t>.");
        toReturn.setThumbnail("https://cdn.discordapp.com/emojis/836487759619293234.webp?size=240&quality=lossless");
        return toReturn;
    }

    public static EmbedBuilder removeHentai() {
        EmbedBuilder removeBlock = new EmbedBuilder();
        removeBlock.setTitle("Success: Removed Hentai");
        removeBlock.setColor(Color.green);
        removeBlock.setDescription("Hentai has been removed from this channel!");
        removeBlock.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        return removeBlock;
    }

    public static EmbedBuilder error_does_not_exist() {
        EmbedBuilder error = new EmbedBuilder();
        error.setColor(Color.red);
        error.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        error.setTitle("ERROR: This channel is not receving daily hentai!");
        error.setDescription("Please use `-hhere` to receive daily hentai.");
        return error;
    }

    public static void error_command_dne(TextChannel tc, CommandInterface c, List<String> args) {
        EmbedBuilder wrongUsage = new EmbedBuilder();
        wrongUsage.setColor(Color.red);
        wrongUsage.setThumbnail("https://cdn.discordapp.com/emojis/836461044661878785.webp?size=240&quality=lossless");
        wrongUsage.setTitle("The command `" + String.join("", args) + "` does not exist!");
        wrongUsage.setDescription("Use `" + SourceLink.BOT_PREFIX + "help" + "` for a list of all my commands!");
        tc.sendMessage(wrongUsage.build()).queue();
    }

    public static void sendMessageWithReactions(MessageChannel channel, MessageEmbed embed, String... reactions) {
        channel.sendMessage(embed).queue(msg -> {
            for(String reaction : reactions) {
                msg.addReaction(reaction).queue();
            }
        });

    }

}
