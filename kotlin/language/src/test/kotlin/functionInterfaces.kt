import kotlin.test.Test
import kotlin.test.assertEquals

interface Instruction<T : InstructionResult>

interface InstructionResult

fun interface InstructionHandler<T : Instruction<TR>, TR : InstructionResult> {
    fun handle(arg : T) : TR
}

sealed class UserInstruction : Instruction<UserInstructionResult>
data class UserInstructionResult(val result : String) : InstructionResult

class CreateUser(val userToCreate : String) : UserInstruction()

class FunctionInterfacesTest {
    private val userCreationHandler = InstructionHandler<CreateUser, UserInstructionResult> {
        if(it.userToCreate.isBlank()) {
            UserInstructionResult("failure")
        } else {
            UserInstructionResult("created user ${it.userToCreate}")
        }
    }
    
    private fun handleUserInstructions(instruction : UserInstruction) : String {
        return when(instruction) {
            is CreateUser -> userCreationHandler.handle(instruction).result
        }
    }
    
    @Test fun `creeate user with no name`() {
        val res = handleUserInstructions(CreateUser(""))
        assertEquals("failure", res)
    }
    
    @Test fun `creeate user with name`() {
        val res = handleUserInstructions(CreateUser("Real name"))
        assertEquals("created user Real name", res)
    }
}
