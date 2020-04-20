package by.jwd.lemesheuski.xml_parser.controller.command_helper.impl;

import by.jwd.lemesheuski.xml_parser.controller.command.ICommand;
import by.jwd.lemesheuski.xml_parser.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.xml_parser.controller.command.impl.post.Print;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class PostCommandHelper implements CommandHelper {
    private Map<CommandName, ICommand> commands = new HashMap<>();
    public PostCommandHelper() {
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.PRINT, new Print());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
