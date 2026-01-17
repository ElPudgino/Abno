package pzi.abno.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import pzi.abno.Abno;
import pzi.abno.capabilities.entity_implants.EntityImplantsProvider;
import pzi.abno.capabilities.entity_implants.EntityImplantsUtils;
import pzi.abno.capabilities.entity_implants.IEntityImplant;
import pzi.abno.capabilities.entity_implants.IEntityImplants;

public class GetImplantsCommand extends CommandBase
{
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public String getName() 
    {
		return "getimplants";
	}

	@Override
	public String getUsage(ICommandSender sender) 
    {
		return "/getimplants";
	}

	@Override
	public List<String> getAliases() 
    {
		List<String> aliases = Lists.<String>newArrayList();
		aliases.add("/getimplants");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
    {	   
        if (args.length < 1)
        {
            return;
        }
        EntityPlayer player = getPlayer(server,sender,args[0]); 
        Abno.logger.info("called getimplants with args: "+args[0]);
        IEntityImplants implants = player.getCapability(EntityImplantsProvider.IMPLANT_CAP, null);
        if (implants != null)
        {
            for (IEntityImplant implant : implants.GetImplantList())
            {
                sender.sendMessage(format(net.minecraft.util.text.TextFormatting.DARK_GREEN, implant.GetInGameName()));
            }
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
