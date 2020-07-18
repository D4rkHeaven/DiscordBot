package handlers;

import exceptions.InvalidCommandException;

public class ProfileHandler implements CommandHandler {
    @Override
    public String execute(String command) throws InvalidCommandException {
        return "stab";
    }
}
