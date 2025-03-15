/**
 * Abstract Base servlet class implementing a dynamic method invocation pattern for
 * HTTP request routing, providing a foundation for servlets in the application by using
 * Java reflection to map request URIs to subclass methods, simplifying request handing and
 * reducing boilerplate code.
 *
 * @author Luan Fangming
 * @version 3.1
 * @since 2025-03-09
 */

package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public abstract class BaseServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		invokeMethod(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		invokeMethod(req, resp);
	}

	/**
	 * Dynamically invokes a subclass method based on the last segment of the request URI.
	 * Extracts the method name from the URI(e.g., "crud/createNote" -> "createNote") and uses
	 * reflection to call it.
	 * @param req   the HTTP request containing the URI
	 * @param resp  the HTTP response for output
	 * @throws ServletException  if method invocation fails due to servlet-specific issues
	 * @throws IOException       if an I/O error occurs during response handling
	 */

	private void invokeMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri == null || uri.trim().isEmpty())
		{
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request URI cannot be null or empty.");
			return;
		}

		String methodName = uri.substring(uri.lastIndexOf("/") + 1);
		if (methodName.isEmpty())
		{
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URI, no such method name.");
			return;
		}

		System.out.println("Invoking method: " + methodName);

		Class<? extends BaseServlet> cls = this.getClass();
		try {
			Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException e) {
			System.out.println("Method not found: " + methodName);
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No such endpoint: " + methodName);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}

