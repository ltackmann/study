interface Instruction<T : InstructionResult>

interface InstructionResult

fun interface InstructionHandler<T : Instruction<TR>, TR : InstructionResult> {
    fun handle(arg : T) : TR
}

sealed class UserInstruction : Instruction<UserInstructionResult>
data class UserInstructionResult(val result : String) : InstructionResult

class CreateUser(val userToCreate : String) : UserInstruction()

fun handleUserInstructions(instruction : UserInstruction) : String {
    val userCreationHandler = InstructionHandler<CreateUser, UserInstructionResult> {
        if(it.userToCreate.isBlank()) {
            UserInstructionResult("failure")
        } else {
            UserInstructionResult("created user ${it.userToCreate}")
        }
    }
    
    val res = when(instruction) {
        is CreateUser -> userCreationHandler.handle(instruction).result
    }
    
    return res
}

fun main() {
    print( "creeate user with no name: ${handleUserInstructions(CreateUser(""))}\n" )
    
    print( "creeate user with name: ${handleUserInstructions(CreateUser("Real name"))}\n" )
}