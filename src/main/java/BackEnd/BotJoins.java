package BackEnd;

import Sources.SourceLink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Objects;

public class BotJoins extends ListenerAdapter {
    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {

        Guild guild = event.getGuild();
        EmbedBuilder joinedInformation = new EmbedBuilder();
        joinedInformation.setColor(Color.PINK);
        joinedInformation.setThumbnail("https://i.imgur.com/Yl2ibZy.jpg");
        joinedInformation.setTitle("**Granadis_Bot 2.0**");
        joinedInformation.setFooter("Bot made by Granadis#8876", "https://cdn.discordapp.com/attachments/808220485917474877/848794323465011210/Untitled-1-Recovered_copy.jpg");
        joinedInformation.addField("Thanks for using this bot!", "To continue, please use `-help` for more commands.", true);
        joinedInformation.addField("Support me!", "Support me on Patreon!\n" + SourceLink.PATREON_LINK, true);
        Objects.requireNonNull(guild.getDefaultChannel()).sendMessage(joinedInformation.build()).queue();


    }
}
