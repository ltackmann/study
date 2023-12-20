sealed class Action { }

abstract class Command extends Action {} 

abstract class Query extends Action {}

String handleAction(Action action) {
    return switch(action) {
        Command() => "command",
        Query() => "query"
    };
}

sealed class UserCommand extends Command { }

class CreateUserCommand extends UserCommand {
  final String nameToCreate;
  CreateUserCommand(this.nameToCreate);
}

class DeleteUserCommand extends UserCommand {
  final String nameToDelete;
  DeleteUserCommand(this.nameToDelete);
}

String handleUserCommands(UserCommand action) {
    return switch(action) {
        CreateUserCommand() => "Create user ${action.nameToCreate}",
        DeleteUserCommand() => "Delete user ${action.nameToDelete}"
    };
}

main() {
  assertEquals( handleAction(CreateUserCommand("Lars")), "command" );
  assertEquals( handleAction(DeleteUserCommand("Lars")), "command" );

   assertEquals( handleUserCommands(CreateUserCommand("Lars")), "Create user Lars" );
  assertEquals( handleUserCommands(DeleteUserCommand("Lars")), "Delete user Lars" );
}

assertEquals(Object actual,Object expected) {
  if(actual == expected) {
    print("[$actual] matched [$expected]");
  } else {
    throw FormatException("[$actual] did not match [$expected]");
  }
}
