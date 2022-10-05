package Bot;

import BackEnd.*;
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
    public static JDA jda;

    public static void main(String[] args) throws Exception {

        // Creating the bot using tokens
        jda = JDABuilder.createDefault(SourceLink.BOT_TOKEN)
                .addEventListeners(new Listener())
                .build();
        jda.addEventListener(new SendDailyHentai());
        jda.addEventListener(new BotJoins());
        jda.addEventListener(new RecQueueSolver());
        jda.addEventListener(new BotInfo());

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

        String[] presenceList = {"Hentai | -help", "Total Guilds: " + BotInfo.totalGuild, "Total Members: " + BotInfo.totalMembers, "Try: -rhentai [Optional_Tag]", "with Rory ❤️", "Support me! Use: -support for more info!"};

        @Override
        public void run() {
            // timer to get n
            int rng = SourceLink.rngPublic.nextInt(presenceList.length);
            jda.getPresence().setActivity(Activity.streaming(presenceList[rng],"https://discord.bots.gg/bots/807849446553419786"));
        }
    }

}


