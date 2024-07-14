package bot;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;


import java.util.ArrayList;
import java.util.List;

import tools.*;

public abstract class CommandBot extends AbstractBot {

    protected List<OptionData> options = new ArrayList<OptionData>();
    private boolean ephemeral;

    protected abstract String respond(Command command);
    protected abstract String getCommand();
    protected abstract String getCommandHelp();

    public CommandBot() {
        this.ephemeral = false;
    }

    private static List<TextPair> convertOptions(List<OptionMapping> options) {
        List<TextPair> result = new ArrayList<TextPair>();
        for (OptionMapping o : options) {
            result.add(new TextPair(o.getName(), o.getAsString()));
        }
        return result;
    }

    @Override
    public final void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;
        if (event.getName().equals(getCommand())) {
            Command command = new Command(event.getUser().getGlobalName(), event.getUser().getId(), event.getCommandString(), event.getChannel().getType().equals(ChannelType.PRIVATE));
            for (OptionMapping o : event.getOptions()) {
                command.addOption(o.getName(), o.getAsString());
            }
            String replyMessage = respond(command);
            event.reply(replyMessage).setEphemeral(true).queue();
        }
    }


    /**
     * To include an option for the command. An option, for example, /regcode 123456, the 123456 is an option.
     *
     * @param optionName The name of the option.
     * @param optionDescription The description of the option.
     * @param isRequired Whether this option is required.
     *
     */
    public final void addOption(String optionName, String optionDescription, boolean isRequired) {
        options.add(new OptionData(OptionType.STRING, optionName, optionDescription, isRequired));
    }



    public final CommandData getCommands() {
        SlashCommandData c = Commands.slash(getCommand(), getCommandHelp());
        for (OptionData o : options) {
            c.addOption(o.getType(), o.getName(), o.getDescription(), o.isRequired());
        }
        return c;
    }
}
