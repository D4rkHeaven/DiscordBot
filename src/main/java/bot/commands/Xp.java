package bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;

public class Xp extends InfoCommand {

    public Xp() {
        EmbedBuilder embed = new EmbedBuilder()
                .setDescription("Can't calculate your xp.");
        this.setAnswer(embed.build());
    }
    public Xp(int xp) {
        EmbedBuilder embed = new EmbedBuilder()
                .setDescription("You have " + xp + " xp.");
        this.setAnswer(embed.build());
    }
}
