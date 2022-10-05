package BackEnd;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BotInfo extends ListenerAdapter {

    // what info do i want?
    // number of guilds
    // number of guilds gain/lost for the past day
    // number of hentai added (?)
    // weekely summary

    public static int totalGuild;
    public static int totalMembers;


    @Override
    public void onReady(@NotNull ReadyEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        totalGuild = event.getJDA().getGuilds().size();
        for (int i = 0; i < totalGuild; i++) {
            totalMembers += event.getJDA().getGuilds().get(i).getMemberCount();
        }

        class SendDaily extends TimerTask {
            public void run() {
                Objects.requireNonNull(event.getJDA().getTextChannelById("842149680435036200")).sendMessage(DailyEmbed(dtf, now).build()).queue();
            }
        }

        long sendDays = 24 * 60 * 60 * 1000;
        Timer timer = new Timer(true);
        timer.schedule(new SendDaily(), 0, sendDays);

//        class SendWeekly extends TimerTask {
//            public void run() {
//                Objects.requireNonNull(event.getJDA().getTextChannelById("842149680435036200")).sendMessage(WeekelyEmbed(dtf, now).build()).queue();
//            }
//        }
//
//        long sendWeek = 7 * 24 * 60 * 60 * 1000;
//        Timer timer_week = new Timer(true);
//        timer_week.schedule(new SendWeekly(), 0, sendWeek);
    }




    private EmbedBuilder WeekelyEmbed(DateTimeFormatter dtf, LocalDateTime now) {
        EmbedBuilder info = new EmbedBuilder();
        info.setColor(Color.pink);
        info.setTitle("Weekely report - week of " + dtf.format(now));
        info.setDescription("Total guilds: " + totalGuild + "\n" +
                "Total members: " + totalMembers);
        info.setImage("https://static.wikia.nocookie.net/jujutsu-kaisen/images/8/86/Chain_of_a_Thousand_Miles.png/revision/latest?cb=20201207053046");

        return info;

    }

    private EmbedBuilder DailyEmbed(DateTimeFormatter dtf, LocalDateTime now) {
        EmbedBuilder info = new EmbedBuilder();
        info.setColor(Color.blue);
        info.setTitle("Daily report - week of " + dtf.format(now) + ", " + now.getDayOfWeek());
        info.setDescription("Total guilds: " + totalGuild + "\n" +
                "Total members: " + totalMembers);
        info.setImage("https://static.wikia.nocookie.net/jujutsu-kaisen/images/8/86/Chain_of_a_Thousand_Miles.png/revision/latest?cb=20201207053046");

        return info;

    }


}
