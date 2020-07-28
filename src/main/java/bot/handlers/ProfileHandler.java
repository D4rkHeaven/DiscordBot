package bot.handlers;

import bot.commands.Command;
import bot.commands.Profile;
import bot.listeners.MessageListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ProfileHandler implements CommandHandler<Profile> {
    
    MessageListener listener;

    public ProfileHandler(MessageListener listener) {
        this.listener = listener;
    }

    @Override
    public Profile generateCommand(MessageReceivedEvent message, MessageListener listener) {
        Profile profileCommand = new Profile();
        profileCommand.setTargetChannel(message.getChannel());
        profileCommand.setAnswer(generateEmbed(message));
        return profileCommand;
    }

    @Override
    public void execute(Command command) {
        command.getTargetChannel().sendMessage(command.getAnswer()).submit();
    }

    private MessageEmbed generateEmbed(MessageReceivedEvent message) {
        User author = message.getAuthor();
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(author.getName())
                .setThumbnail(author.getEffectiveAvatarUrl())
                .addField("ID", author.getId(), true)
                .addField("Tag", author.getAsTag(), true);
        return embed.build();
    }
}

