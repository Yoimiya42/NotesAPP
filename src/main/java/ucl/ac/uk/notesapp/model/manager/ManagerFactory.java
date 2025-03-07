package ucl.ac.uk.notesapp.model.manager;

public class ManagerFactory {
	public static Manager manager;


	public static Manager getManager()
	{
		if(manager == null)
		{
			manager = new Manager();
		}
		return manager;
	}
}
