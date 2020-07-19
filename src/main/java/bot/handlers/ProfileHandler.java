package bot.handlers;

import bot.exceptions.InvalidCommandException;
import net.dv8tion.jda.api.entities.User;

public class ProfileHandler implements CommandHandler {
    private User author;

    public ProfileHandler() {
    }

    public ProfileHandler(User author) {
        this.author = author;
    }

    @Override
    public String execute(String command) throws InvalidCommandException {

        return "Username: " + author.getName() + "\nUser id: " + author.getId();
    }
}
