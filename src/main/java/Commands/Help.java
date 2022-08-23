package Commands;

import Bot.Manager;
import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Help implements CommandInterface {
    public final Manager manager;

    public Help(Manager m) {
        this.manager = m;
    }

    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) throws FileNotFoundException, IOException {
        if (args.size() > 1) {
            SourceLink.wrongUsage(event.getChannel(), this);
            return;
        }
        if (args.isEmpty()) {
            EmbedBuilder e = new EmbedBuilder().setTitle("Granadis_Bot 2.0");
            e.setFooter("Granadis Bot ver 2.0", "https://cdn.discordapp.com/attachments/808220485917474877/848794323465011210/Untitled-1-Recovered_copy.jpg");
            e.setColor(Color.pink);
            e.setThumbnail("https://cdn.discordapp.com/emojis/836487759619293234.webp?size=240&quality=lossless");
            e.setDescription("All nsfw commands will only work in nsfw channels!\n");
            StringBuilder desc = e.getDescriptionBuilder();
            manager.getCommands().forEach(command -> {
                desc.append("**").append(command.getCommand()).append("**").append(": ").append(command.getHelp()).append(".").append("\n");
            });
            event.getChannel().sendMessage(e.build()).queue();
            return;
        }
        CommandInterface command = manager.getCommand(String.join("", args));
        if (command == null) {
            SourceLink.error_command_dne(event.getChannel(), this, args);
            return;
        }
        SourceLink.wrongUsage(event.getChannel(), this);
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Description: Help command of this bot.\n" +
                "Usage: `" + SourceLink.BOT_PREFIX + getCommand() + "`";
    }
}
