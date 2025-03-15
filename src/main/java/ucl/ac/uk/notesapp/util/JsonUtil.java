/**
 * Utility class for JSON file operations, including reading and writing JSON data
 * from local directory.
 * Provides methods to deserialize JSON files into Java objects and serialize Java objects
 * into JSON files.
 *
 * @author Luan Fangming
 * @version 2.0
 * @since 2025-03-07
 */

package ucl.ac.uk.notesapp.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *  A utility class for handing JSON file operations using Jackson library.
 */
public class JsonUtil {

	private static final ObjectMapper mapper = new ObjectMapper();
	static{
		mapper.enable(SerializationFeature.INDENT_OUTPUT);// Enables pretty-printing of JSON output
	}

	/**
	 * Reads a JSON file from the indicated file path and deserialize it into an object of the given class type
	 * @param filePath the path to JSON file
	 * @param clazz the class type to deserialize the JSON into
	 * @param <T>   the type of the object to deserialize into
	 * @return      the deserialized object of type T
	 * @throws IllegalArgumentException if the file path is null or empty
	 * @throws RuntimeException         if the file does not exist or if deserialization fails

	 * @throws IOException
	 */

	public static <T> T readJsonFile(String filePath, Class<T> clazz)
	{
		if (filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("File path is null or empty");
		}
		File file = new File(filePath);
		if (!file.exists()) {
			throw new RuntimeException("File does not exist: " + filePath);
		}

		try {
			return mapper.readValue(file, clazz);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read JSON file: " + filePath, e);
		}
	}

	/**
	 * Writes a Java object to a JSON file at the indicated file path.
	 * @param <T>       the type of the object to serialize
	 * @param filePath  the path where the JSON file will be written
	 * @param obj       the object to serialize into JSON
	 * @throws IllegalArgumentException if the file path is null or empty, or if the object is null
	 * @throws RuntimeException         if serialization fails
	 */
	public static<T> void writeJsonFile(String filePath, T obj)
	{
		if(obj == null)
			throw new IllegalArgumentException("Object is null");
		if (filePath == null || filePath.isEmpty())
			throw new IllegalArgumentException("File path is null or empty");

		try {
			mapper.writeValue(new File(filePath), obj);
		} catch (IOException e) {
			System.err.println("Error writing JSON file: " + e.getMessage());
			throw new RuntimeException("Failed to write JSON file", e);
		}
	}
}
