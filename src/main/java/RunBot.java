import Commands.*;
// import Events.*;
import Sources.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;
import java.util.Timer;
import java.util.TimerTask;

public class RunBot extends ListenerAdapter {
    private static JDA jda;
    private static int guildNum;

    public static void main(String[] args) throws Exception {
        // Creating the bot using tokens
        jda = JDABuilder.createDefault(SourceLink.BOT_TOKEN)
                .addEventListeners(new Listener())
                .build();
        guildNum = jda.getGuilds().size();

        // for changing presence
        new Timer().schedule(new RunBot.timerPresence(), 0, 60000);
    }

    public static class timerPresence extends TimerTask {
        String[] presenceList = {"Hentai | HThelp", "Total Guilds: " + guildNum, "test msg", "another test msg", "rory","hello"};
        @Override
        public void run() {
            // timer to get n
            int rng = SourceLink.rngPublic.nextInt(presenceList.length);
            jda.getPresence().setActivity(Activity.playing(presenceList[rng]));
        }
    }

}


