package bot.listeners;

import bot.utils.CensorshipFilter;
import bot.utils.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

/**
 * Main listener for bot
 */
@Slf4j
public class MessageListener extends ListenerAdapter {
    @Getter
    private static final long GUILD_ID = 732199841819787315L;
    @Setter
    private boolean debugMode;
    private CensorshipFilter censorshipFilter;
    private Filter filter;

    public MessageListener() {
        debugMode = false;
        censorshipFilter = new CensorshipFilter();
        filter = new Filter(this);
    }

    /**
     * Check past events after bot startup
     *
     * @param event bot startup
     */
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        super.onReady(event);
        censorshipFilter.censoring(event);
    }

    /**
     * Listen new messages
     *
     * @param event new message
     */
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        try {
            if (censorshipFilter.hasBadWords(event.getMessage().getContentRaw().toLowerCase())) {
                event.getMessage().delete().submit();
                event.getChannel().sendMessage("Message from "
                        + event.getAuthor().getAsTag() + " deleted because it contains bad words.").submit();
            } else if (filter.isCommand(event.getMessage().getContentRaw()))
                filter.execute(event);
        } catch (Exception e) {
            log.warn("Exception: ", e);
            if (debugMode) {
                event.getChannel().sendMessage(e.toString()).submit();
            }
        }
    }
}
