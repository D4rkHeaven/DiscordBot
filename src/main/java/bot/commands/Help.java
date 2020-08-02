package bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;

public class Help extends InfoCommand {
    public Help() {
        EmbedBuilder embed = new EmbedBuilder()
                .setAuthor("Training bot", "https://github.com/D4rkHeaven/DiscordBot")
                .setDescription("Here is the list of all my commands\n")
                .addField("Info", "`!help` `!about` `!profile`", true)
                .addField("Settings", "`!debug [on/off]` `!create channel <category_name> <channel_name>`", true);
        this.setAnswer(embed.build());
    }
}
