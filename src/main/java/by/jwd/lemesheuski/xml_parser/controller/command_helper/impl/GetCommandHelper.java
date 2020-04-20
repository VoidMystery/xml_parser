package by.jwd.lemesheuski.xml_parser.controller.command_helper.impl;

import by.jwd.lemesheuski.xml_parser.controller.command.ICommand;
import by.jwd.lemesheuski.xml_parser.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.xml_parser.controller.command.impl.get.Main;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class GetCommandHelper implements CommandHelper {
    private Map<CommandName, ICommand> commands = new HashMap<>();
    public GetCommandHelper() {
        commands.put(CommandName.MAIN, new Main());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
