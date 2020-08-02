package bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

public class Profile extends InfoCommand {

    public Profile(User author) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(author.getName())
                .setThumbnail(author.getEffectiveAvatarUrl())
                .addField("ID", author.getId(), true)
                .addField("Tag", author.getAsTag(), true);
        this.setAnswer(embed.build());
    }
}
