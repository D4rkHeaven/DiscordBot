package bot;

import bot.filter.Filter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

/**
 * Bot initialization
 */
@Slf4j
public class TrainingBot extends ListenerAdapter {

    @Setter
    private boolean debugMode = false;

    Filter filter = new Filter(this);

    public static void main(String[] args) throws LoginException {
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new TrainingBot())
                .setActivity(Activity.listening("!help"))
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (filter.isCommand(event.getMessage().getContentRaw())) {
            try {
                filter.execute(event);
            } catch (Exception e) {
                log.warn("Exception: ", e);
                if (debugMode) {
                    event.getChannel().sendMessage(e.toString()).submit();
                }
            }
        }
    }
}
