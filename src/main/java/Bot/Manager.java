package Bot;

import java.io.IOException;
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
        addCommand(new AllTag());
        addCommand(new DailyHentai());
        addCommand(new RemoveDailyHentai());
        addCommand(new Help(this));
        addCommand(new Support());
        addCommand(new Recommend());
        addCommand(new Code());
        addCommand(new Image());

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

    void run(GuildMessageReceivedEvent event) throws IOException {
        final String msg = event.getMessage().getContentRaw();
        if (!msg.startsWith(SourceLink.BOT_PREFIX)) {
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
