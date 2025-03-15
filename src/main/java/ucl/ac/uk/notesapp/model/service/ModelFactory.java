/**
 * Factory class responsible for providing a singleton instance of the Model class in the application.
 * Ensures only one instance of the Model class is created and used throughout the application execution.
 * @author Luan Fangming
 * @version 1.0
 * @since 2025-03-07
 */


package ucl.ac.uk.notesapp.model.service;

public class ModelFactory
{
	private static Model model;

	public static Model getModel()
	{
		if(model == null)
			model = new Model();

		return model;
	}

}
