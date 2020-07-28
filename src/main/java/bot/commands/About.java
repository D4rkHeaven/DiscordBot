package bot.commands;

import lombok.Data;
import net.dv8tion.jda.api.EmbedBuilder;

@Data
public class About extends InfoCommand {

    public About (){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Training bot", "https://github.com/D4rkHeaven/DiscordBot")
                .setDescription("Bot for practice in Java");
        this.setAnswer(embed.build());
    }
}
