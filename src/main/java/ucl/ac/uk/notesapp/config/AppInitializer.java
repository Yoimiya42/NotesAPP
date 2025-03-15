package ucl.ac.uk.notesapp.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ucl.ac.uk.notesapp.model.service.Model;
import ucl.ac.uk.notesapp.model.service.ModelFactory;


@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Model manager = ModelFactory.getModel();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}
}
