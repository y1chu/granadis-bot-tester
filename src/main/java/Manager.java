
import java.util.*;
import java.util.regex.Pattern;

import Commands.*;
import Interface.CommandInterface;
import Sources.SourceLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Manager {

    private final Map<String, CommandInterface> commands = new HashMap<>();

    Manager() {
        addCommand(new RandomHentai());

    }

    private void addCommand(CommandInterface c) {
        if (!commands.containsKey(c.getCommand())) {
            commands.put(c.getCommand(), c);
        }
    }

    public Collection<CommandInterface> getCommands() {
        return commands.values();
    }

    public CommandInterface getCommand(String commandName) {
        if (commandName == null) {
            return null;
        }
        return commands.get(commandName);
    }

    void run(GuildMessageReceivedEvent event) {
        final String msg = event.getMessage().getContentRaw();
        System.out.println(msg);
        if (!msg.startsWith(SourceLink.BOT_PREFIX)) {
            System.out.println("hi");
            return;
        }
        final String[] split = msg.replaceFirst("(?i)" + Pattern.quote(SourceLink.BOT_PREFIX), "").split("\\s+");
        final String command = split[0].toLowerCase();
        if (commands.containsKey(command)) {
            final List<String> args = Arrays.asList(split).subList(1, split.length);
            commands.get(command).run(args, event);
        }
    }


}
