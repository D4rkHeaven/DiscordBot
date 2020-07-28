package bot.listeners;

import bot.filter.Filter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

@Slf4j
public class MessageListener extends ListenerAdapter {
    @Setter
    private boolean debugMode = false;

    Filter filter=new Filter(this);

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
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
