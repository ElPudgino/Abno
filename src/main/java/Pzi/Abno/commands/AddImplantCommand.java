package pzi.abno.commands;

import java.text.DateFormat;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import pzi.abno.Abno;
import pzi.abno.capabilities.entity_implants.EntityImplantsProvider;
import pzi.abno.capabilities.entity_implants.EntityImplantsUtils;
import pzi.abno.capabilities.entity_implants.IEntityImplants;

public class AddImplantCommand extends CommandBase
{
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public String getName() 
    {
		return "addimplant";
	}

	@Override
	public String getUsage(ICommandSender sender) 
    {
		return "/addimplant";
	}

	@Override
	public List<String> getAliases() 
    {
		List<String> aliases = Lists.<String>newArrayList();
		aliases.add("/addimplant");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
    {	   
        if (args.length < 2)
        {
            return;
        }
        EntityPlayer player = getPlayer(server,sender,args[0]); 
        Abno.logger.info("called addimplant with args: "+args[0]+", "+args[1]);
        sender.sendMessage(format(net.minecraft.util.text.TextFormatting.DARK_GREEN, "Added implant: "+args[0]));
        Abno.logger.info("getting capability");
        IEntityImplants implants = player.getCapability(EntityImplantsProvider.IMPLANT_CAP, null);
        if (implants != null)
        {
            Abno.logger.info("implants list found");
            implants.AddImplant(EntityImplantsUtils.CreateImplantInstance(args[1]), player);
        }
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) 
    {
		return false;
	}
	
	private TextComponentTranslation format(TextFormatting color, String str, Object... args)
    {
        TextComponentTranslation ret = new TextComponentTranslation(str, args);
        ret.getStyle().setColor(color);
        return ret;
    }
}
