import Commands.*;
// import Events.*;
import Sources.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class RunBot {
    private static JDA jda;
    private static int guildNum;
    public static void main(String[] args) throws Exception {
        // Creating the bot using tokens
        jda = JDABuilder.createDefault(SourceLink.BOT_TOKEN).build();
        guildNum = jda.getGuilds().size();

        jda.addEventListener(new HTVideo());

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


