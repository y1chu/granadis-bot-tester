package Sources;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Random;

public class SourceLink {

    public static Random rngPublic = new Random();
    // public static String BOT_TOKEN = System.getenv("TOKEN");
    public static String BOT_TOKEN = "ODY3ODg0ODAxNDY4OTIzOTQ0.Ga_NEv.DUn8fu56VlbwGAxa-dwr5Z7gF5Ktz5YIBK2H-Q";
    public final static String BOT_PREFIX = "-";
    public final static String OWNERID = "714284046892400731";


    public static EmbedBuilder error_nsfw_embed() {
        EmbedBuilder nsfw_error = new EmbedBuilder();
        nsfw_error.setColor(Color.red);
        nsfw_error.setTitle("ERROR: NSFW COMMANDS ONLY WORKS IN NSFW CHANNELS!");
        nsfw_error.setDescription("Please review: https://techwiser.com/make-nsfw-channel-discord/ on how to make a NSFW channel!");
        return nsfw_error;
    }


}
