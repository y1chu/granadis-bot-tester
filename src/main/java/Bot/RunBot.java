package Bot;

import BackEnd.BotJoins;
import BackEnd.SendDailyHentai;
import Commands.DailyHentai;
import Sources.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.File;
import java.util.Scanner;
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
        jda.addEventListener(new SendDailyHentai());
        jda.addEventListener(new BotJoins());

        // initialize channel List with the database when restarting
        File sources = new File(SourceLink.ALL_CHANNELS);
        Scanner readSource = new Scanner(sources);
        while (readSource.hasNext()) {
            DailyHentai.allChannels.add(readSource.nextLine());
        }

        // for changing presence
        new Timer().schedule(new RunBot.timerPresence(), 0, 60000);
    }

    public static class timerPresence extends TimerTask {
        String[] presenceList = {"Hentai | -help", "Total Guilds: " + guildNum, "test msg", "another test msg", "rory", "hello"};

        @Override
        public void run() {
            // timer to get n
            int rng = SourceLink.rngPublic.nextInt(presenceList.length);
            jda.getPresence().setActivity(Activity.playing(presenceList[rng]));
        }
    }

}


