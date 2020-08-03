package bot.utils;

import bot.listeners.MessageListener;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Deletes messages containing bad words
 */
@Slf4j
public class CensorshipFilter {

    private final Set<String> badWords;

    public CensorshipFilter() {
        badWords = new HashSet<>();
        getBadWords();
    }

    /**
     * Clear past messages after bot startup
     *
     * @param event bot startup
     */
    public void censoring(ReadyEvent event) {
        TextChannel textChannel = event.getJDA().getTextChannelById(MessageListener.getBOT_ID());
        StringBuilder censor = new StringBuilder();
        assert textChannel != null;
        Map<User, List<Message>> userListMap = textChannel
                .getIterableHistory().stream()
                .takeWhile(message -> message.getAuthor().getIdLong() != MessageListener.getBOT_ID())
                .filter(message -> hasBadWords(message.getContentRaw().toLowerCase()))
                .collect(Collectors.groupingBy(Message::getAuthor));
        userListMap.forEach((user, messages) -> {
            messages.forEach(message -> message.delete().submit());
            censor.append("Deleted ").append(messages.size()).append(" messages from ").append(user.getAsTag()).append("\n");
        });
        if (censor.length() != 0) {
            textChannel.sendMessage(censor).submit();
        }
    }

    /**
     * Checks string for bad words
     *
     * @param message input string
     * @return boolean result
     */
    public boolean hasBadWords(final String message) {
        return badWords.stream().anyMatch(message::contains);
    }

    /**
     * Parse "censor.txt" in resources package to set of strings
     */
    private void getBadWords() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("src/main/resources/censor.txt"), StandardCharsets.UTF_8))) {
            String word;
            while ((word = reader.readLine()) != null) {
                if (!word.isEmpty())
                    badWords.add(word);
            }
        } catch (final Exception ex) {
            log.warn("Censorship filter disabled. File 'censor.txt' not found");
        }
    }
}
