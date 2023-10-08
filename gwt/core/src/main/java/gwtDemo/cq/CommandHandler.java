package gwtDemo.cq;

import gwtDemo.cq.api.Command;
import gwtDemo.cq.api.CommandFrame;

public abstract class CommandHandler<COMMAND extends Command> {
	public abstract CommandFrame getCommandFrame(COMMAND command);
	
	public abstract void doComplexValidation(); 
}
