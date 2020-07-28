package bot.handlers;

import bot.TrainingBot;
import bot.commands.About;
import bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AboutHandler implements CommandHandler<About> {

    TrainingBot bot;

    public AboutHandler(TrainingBot bot) {
        this.bot = bot;
    }

    @Override
    public About generateCommand(MessageReceivedEvent message, TrainingBot bot) {
        About aboutCommand = new About();
        aboutCommand.setTargetChannel(message.getChannel());
        aboutCommand.setAnswer(generateEmbed());
        return aboutCommand;
    }

    @Override
    public void execute(Command command) {
        command.getTargetChannel().sendMessage(command.getAnswer()).submit();
    }

    private MessageEmbed generateEmbed() {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Training bot", "https://github.com/D4rkHeaven/DiscordBot")
                .setDescription("Bot for practice in Java");
        return embed.build();
    }
}
