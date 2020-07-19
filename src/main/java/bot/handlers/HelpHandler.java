package bot.handlers;

import net.dv8tion.jda.api.EmbedBuilder;

public class HelpHandler implements CommandHandler {

    EmbedBuilder embed = new EmbedBuilder()
            .setAuthor("Bot commands","https://github.com/D4rkHeaven/DiscordBot")
            .setTitle("Here is the list of all my commands\n")
            .addField("Info","'!help'",true)
            .addField("Settings","'!debug on/off'",true);

    @Override
    public String execute(String command) {
        return "Here is the list of all my commands.\n`!help`\n`!about`\n`!debug on/off`\n`!profile`";
    }
}
