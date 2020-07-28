package bot.handlers;

import bot.TrainingBot;
import bot.commands.Command;
import bot.commands.Help;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpHandler implements CommandHandler<Help> {

    TrainingBot bot;

    public HelpHandler(TrainingBot bot) {
        this.bot = bot;
    }


    @Override
    public Help generateCommand(MessageReceivedEvent message, TrainingBot bot) {
        Help helpCommand = new Help();
        helpCommand.setTargetChannel(message.getChannel());
        helpCommand.setAnswer(generateEmbed());
        return helpCommand;
    }

    @Override
    public void execute(Command command) {
        command.getTargetChannel().sendMessage(command.getAnswer()).submit();
    }

    private MessageEmbed generateEmbed() {
        EmbedBuilder embed = new EmbedBuilder()
                .setAuthor("Training bot", "https://github.com/D4rkHeaven/DiscordBot")
                .setDescription("Here is the list of all my commands\n")
                .addField("Info", "`!help` `!about` `!profile`", true)
                .addField("Settings", "`!debug on/off`", true);
        return embed.build();
    }
}
