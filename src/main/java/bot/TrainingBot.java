package bot;

import bot.listeners.MessageListener;
import bot.listeners.VoiceChannelListener;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Bot initialization
 */
@Slf4j
public class TrainingBot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        try (FileInputStream file = new FileInputStream("src/main/resources/config.properties")) {
            Properties property = new Properties();
            property.load(file);
            JDABuilder.createDefault(property.getProperty("bot.token"))
                    .addEventListeners(new MessageListener(), new VoiceChannelListener())
                    .setActivity(Activity.listening("!help"))
                    .build();
        } catch (IOException e) {
            log.error("Properties file not found!");
            e.printStackTrace();
        }
    }
}