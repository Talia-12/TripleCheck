package ram.talia.triplecheck.framework.mod;

import java.util.function.BiConsumer;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.network.chat.TextComponent;
import ram.talia.triplecheck.framework.IntegrationTestManager;

/**
 * Part of the integration test mod features.
 */
final class IntegrationTestCommands
{
    static void registerCommands(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("integrationTest")
            .then(Commands.literal("setup")
                .executes(context -> setupAllTests(context.getSource()))
            )
            .then(Commands.literal("run")
                .executes(context -> runAllTests(context.getSource()))
            )
        );
    }

    private static int runAllTests(CommandSource source)
    {
        IntegrationTestManager.INSTANCE.runAllTests(source.getLevel(), wrap(source));
        return Command.SINGLE_SUCCESS;
    }

    private static int setupAllTests(CommandSource source)
    {
        final BiConsumer<String, Boolean> logger = wrap(source);
        if (IntegrationTestManager.INSTANCE.verifyAllTests(source.getLevel(), logger))
        {
            IntegrationTestManager.INSTANCE.setupAllTests(source.getLevel(), logger);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static BiConsumer<String, Boolean> wrap(CommandSource source)
    {
        return (message, success) -> {
            final TextComponent text = new TextComponent(message);
            if (success)
            {
                source.sendSuccess(text, true);
            }
            else
            {
                source.sendFailure(text);
            }
        };
    }
}
