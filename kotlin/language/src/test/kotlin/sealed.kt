import kotlin.test.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertEquals

sealed class Action

abstract class Command : Action()
abstract class Query : Action()

fun handleAction(action: Action) : String {
    return when(action) {
        is Command -> "command"
        is Query -> "query"
    }
}

sealed class UserCommand : Command()

class CreateUserCommand(nameToCreate : String) : UserCommand()

class DeleteUserCommand(nameToDelete : String) : UserCommand()

fun handleUserCommands(action : UserCommand) : String {
    return when(action) {
        is CreateUserCommand -> "create user ${action.nameToCreate}"
        is DeleteUserCommand -> "delete user ${action.nameToDelete}"
    }
}

class MyTest {
    @Test fun `action handling`() {
        assertEquals( handleAction(CreateUserCommand("Lars")), "command" )
        assertEquals( handleAction(CreateUserCommand("Lars")), "command" )
    }

    @Test fun `command handling`() {
        assertEquals( handleUserCommands(CreateUserCommand("Lars")), "create user Lars" )
        assertEquals( handleUserCommands(CreateUserCommand("Lars")), "delete user Lars" )
    }
}
