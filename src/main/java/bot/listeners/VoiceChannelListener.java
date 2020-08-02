package bot.listeners;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Slf4j
public class VoiceChannelListener extends ListenerAdapter implements Runnable {

    @Getter
    private static final long GUILD_ID = 732199841819787315L;

    VoiceChannel voiceChannel;
    private Category dynamicVoiceChannels;

    public VoiceChannelListener() {
    }

    public VoiceChannelListener(VoiceChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    /**
     * Check past events after bot startup
     *
     * @param event bot startup
     */
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        try {
            super.onReady(event);
            Guild guild = event.getJDA().getGuildById(GUILD_ID);
            assert guild != null;
            Optional<Category> optionalCategory = guild.getCategories()
                    .stream()
                    .filter(isDynamicCategoryExist())
                    .findAny();
            dynamicVoiceChannels = optionalCategory.orElseGet(() -> {
                try {
                    return guild.createCategory("Dynamic (d)").submit().get();
                } catch (InterruptedException | ExecutionException e) {
                    log.warn("Exception: ", e);
                }
                return null;
            });
            createVoiceChannel();
        } catch (NullPointerException e) {
            log.warn("Exception: ", e);
        }
    }

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        createVoiceChannel();
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        Runnable voiceChannelListener = new VoiceChannelListener(event.getChannelLeft());
        Thread thread = new Thread(voiceChannelListener);
        thread.start();
    }

    @Override
    public void run() {
        Runnable task = () -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (voiceChannel.getMembers().size() == 0) voiceChannel.delete().submit();
        };
        Thread closeTask = new Thread(task);
        closeTask.start();
    }

    @NotNull
    private Predicate<Category> isDynamicCategoryExist() {
        return category -> category.getName().toLowerCase().equals("dynamic (d)");
    }

    /**
     * Try to create voice channel if guild doesn't have empty one
     */
    private void createVoiceChannel() {
        assert dynamicVoiceChannels != null;
        int voiceChannelCount = dynamicVoiceChannels.getVoiceChannels().size();
        if (dynamicVoiceChannels.getVoiceChannels().stream().noneMatch(voiceChannel -> voiceChannel.getMembers().size() == 0)) {
            dynamicVoiceChannels.createVoiceChannel("Channel " + (voiceChannelCount + 1)).submit();
        }
    }
}
