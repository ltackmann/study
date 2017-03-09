package lang;

public class GenericsTest {
	public <C extends Command, CH extends CommandHandler<C>> void registerCommandHandler(Class<C> command, Class<CH> handler) {
		System.out.println("command class is " + command.getCanonicalName());
		//handler.newInstance();
	}
	
	public static void main(String[] args) {
		GenericsTest genericsTest = new GenericsTest();
		// works
		genericsTest.registerCommandHandler(CreateCustomerCommand.class, CreateCustomerCommandHandler.class);
		// wont work
		//genericsTest.registerCommandHandler(CreateCustomerCommand.class, DeleteCustomerCommandHandler.class);
	}
}

// API
class Command {}
abstract class CommandHandler<C extends Command> {
	public abstract void handle(C command);
}

// classes
class CreateCustomerCommand extends Command {
	public final String customerName;
	public CreateCustomerCommand(String customerName) {
		this.customerName = customerName;
	}
}
class CreateCustomerCommandHandler extends CommandHandler<CreateCustomerCommand> {
	@Override
	public void handle(CreateCustomerCommand command) {
		System.out.println("creating customer " + command.customerName);
	}
} 
class DeleteCustomerCommand extends Command {
	public final String customerName;
	public DeleteCustomerCommand(String customerName) {
		this.customerName = customerName;
	}
}
class DeleteCustomerCommandHandler extends CommandHandler<DeleteCustomerCommand> {
	@Override
	public void handle(DeleteCustomerCommand command) {
		System.out.println("deleting customer " + command.customerName);
	}
} 