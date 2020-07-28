package bot.commands;

import lombok.Data;
import net.dv8tion.jda.api.EmbedBuilder;

@Data
public class Debug extends SettingCommand {
    private Boolean mode;

    public Debug(Boolean mode) {
        this.mode = mode;
        EmbedBuilder embed = new EmbedBuilder()
                .setDescription("Debug mode" + mode.toString());
        this.setAnswer(embed.build());
    }
}
