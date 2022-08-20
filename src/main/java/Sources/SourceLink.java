package Sources;

import Interface.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.Scanner;

public class SourceLink {

    public static Random rngPublic = new Random();
    // public static String BOT_TOKEN = System.getenv("TOKEN");
    public static String BOT_TOKEN = "ODY3ODg0ODAxNDY4OTIzOTQ0.Ga_NEv.DUn8fu56VlbwGAxa-dwr5Z7gF5Ktz5YIBK2H-Q";
    public final static String BOT_PREFIX = "-";
    public final static String OWNERID = "714284046892400731";

    //paths
    public static final String ERROR_LOGS = "src/main/java/BackEnd/ErrorLogs.txt";
    public static final String ALL_TAGS = "src/main/java/Sources/AllTags.txt";
    public static final String FAVORITES = "src/main/java/Sources/Favorites.txt";
    public static final String BANNED = "src/main/java/Sources/Banned.txt";

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
        tc.sendMessage("Wrong Command Usage!\n" + c.getHelp()).queue();
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


}
