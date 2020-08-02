package bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;

public class Create extends SettingCommand {

    public Create() {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Created channel.");
        this.setAnswer(embed.build());
    }
}
