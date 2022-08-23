package BackEnd;

import Commands.DailyHentai;
import Sources.SourceLink;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendDailyHentai extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        ZonedDateTime currentTime = ZonedDateTime.now();
        ZonedDateTime nextHentai = currentTime.withHour(20).withMinute(50).withSecond(30);

        if (currentTime.compareTo(nextHentai) > 0) {
            nextHentai = nextHentai.plusDays(1);
        }
        Duration durationUntilNextHentai = Duration.between(currentTime, nextHentai);
        long initialDelayHentai = durationUntilNextHentai.getSeconds();

        ScheduledExecutorService schedulerFirstHentai = Executors.newScheduledThreadPool(1);
        schedulerFirstHentai.scheduleAtFixedRate(() -> {
                    for (String ch : DailyHentai.allChannels) {
                        TextChannel channel = event.getJDA().getTextChannelById(ch);
                        if(channel == null) {
                            continue;
                        }
                        if (channel.isNSFW()) {
                            try {
                                channel.sendMessage("Here's your daily dose of hentai ;)\n" + GenerateHentai.generateHentaiFromSource(SourceLink.FAVORITES)).queue();
                                Calendar calendar = Calendar.getInstance();
                                int day = calendar.get(Calendar.DAY_OF_WEEK);
                                if (day == Calendar.MONDAY) {
                                    // TODO add recBlock

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                initialDelayHentai,
                TimeUnit.DAYS.toSeconds(1),
                TimeUnit.SECONDS);
    }

}
