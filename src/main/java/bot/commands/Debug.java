package bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;

public class Debug extends SettingCommand {

    public Debug(Boolean mode) {
        EmbedBuilder embed = new EmbedBuilder()
                .setDescription("Debug mode " + mode.toString());
        this.setAnswer(embed.build());
    }
}
