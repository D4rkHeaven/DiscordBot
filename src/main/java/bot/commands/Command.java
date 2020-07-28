package bot.commands;

import lombok.Data;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

/**
 * Superclass of training bot commands
 */
@Data
public class Command {
    MessageEmbed answer;
    MessageChannel targetChannel;
}
