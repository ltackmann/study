package uge7;

import java.util.List;
import java.util.LinkedList;

public class ErasureTest {
	// javap -l -s -c -private ErasureTest
	public static void main(String[] args) {
		UserList<User,List<User>> userList = new UserList<User, List<User>>(new LinkedList<User>());
		userList.addUser(new User());
		userList.addUser(new Manager());
		userList.printUserTypes();
	}
}

// javap -l -s -c -private UserList
class UserList<U extends User, T extends List<U>> {
	final T users;

	// type parameters are erased
	public UserList(T users) {
		this.users = users;
	}

	// single types handled correctly
	public void addUser(U user) {
		this.users.add(user);
	}

	// type parameters are erased
	public void addUsers(T users) {
		this.users.addAll(users);
	}

	public void printUserTypes() {
		// single types handled correctly
		for (U user : users) {
			System.out.println(user.getClass());
		}
	}
}

class User {

}

class Manager extends User {

}
