package by.jwd.lemesheuski.xml_parser.controller.command_helper;

import by.jwd.lemesheuski.xml_parser.controller.command.ICommand;

import java.util.Map;

public interface CommandHelper {
    ICommand getCommand(String commandName);

    default ICommand getICommand(String commandName, Map<CommandName, ICommand> commands) {
        ICommand command;

        if (commandName != null) {
            try {
                CommandName name = CommandName.valueOf(commandName.toUpperCase());
                command = commands.get(name);
            } catch (IllegalArgumentException e) {
                command = commands.get(CommandName.NO_SUCH_COMMAND);
            }
        }else{
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }

        return command;
    }
}
