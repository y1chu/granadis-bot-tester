package Sources;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Random;

public class SourceLink {

    public static Random rngPublic = new Random();
    public static String BOT_TOKEN = System.getenv("TOKEN");


    public static EmbedBuilder error_nsfw_embed() {
        EmbedBuilder nsfw_error = new EmbedBuilder();
        nsfw_error.setColor(Color.red);
        nsfw_error.setTitle("ERROR: NSFW COMMANDS ONLY WORKS IN NSFW CHANNELS!");
        nsfw_error.setDescription("Please review: https://techwiser.com/make-nsfw-channel-discord/ on how to make a NSFW channel!");
        return nsfw_error;
    }


}
