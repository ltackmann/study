package gwtDemo.client.framework;

/**
 * Responsible for determining if a user is has the privileges to perform actions.
 * 
 * Created as a interface so different implementations can exists (for example looking 
 * up privileges remotely on the server)
 */
public interface SecurityManager {
	/**
	 * Determine if user is allowed to view page
	 * 
	 * @param page
	 * @return
	 */
	boolean canUserViewPage(Class<Page> page);
}
