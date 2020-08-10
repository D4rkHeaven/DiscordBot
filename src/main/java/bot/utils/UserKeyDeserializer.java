package bot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import net.dv8tion.jda.api.entities.User;

import java.io.IOException;

public class UserKeyDeserializer extends KeyDeserializer {
    @Override
    public User deserializeKey(final String key, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null; // replace null with your logic
    }
}
