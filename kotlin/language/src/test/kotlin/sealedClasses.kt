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

class CreateUserCommand(val nameToCreate : String) : UserCommand()

class DeleteUserCommand(val nameToDelete : String) : UserCommand()

fun handleUserCommands(action : UserCommand) : String {
    return when(action) {
        is CreateUserCommand -> "Create user ${action.nameToCreate}"
        is DeleteUserCommand -> "Delete user ${action.nameToDelete}"
    }
}

class SealedClassesTest {
    @Test fun `action handling`() {
        assertEquals( handleAction(CreateUserCommand("Lars")), "command" )
        assertEquals( handleAction(DeleteUserCommand("Lars")), "command" )
    }

    @Test fun `command handling`() {
        assertEquals( handleUserCommands(CreateUserCommand("Lars")), "Create user Lars" )
        assertEquals( handleUserCommands(DeleteUserCommand("Lars")), "Delete user Lars" )
    }
}
