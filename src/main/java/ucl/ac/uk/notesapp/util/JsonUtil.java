package ucl.ac.uk.notesapp.util;


import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();
	static{
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}


	/**
	 *
	 * @param filePath
	 * @param clazz
	 * @return
	 * @param <T>
	 * @throws IOException
	 */
	public static <T> List<T> readJsonFile(String filePath, Class<T> clazz)
	throws IOException
	{
		if (filePath == null || filePath.isEmpty())
			throw new IllegalArgumentException("File path is null or empty");
		File file = new File(filePath);
		if(!file.exists())
			throw new IOException("File does not exist");

		return mapper.readValue(file, mapper.getTypeFactory().
				constructCollectionType(List.class, clazz));
	}


	/**
	 *
	 * @param filePath
	 * @param obj
	 * @param <T>
	 * @throws IOException
	 */
	public static<T> void writeJsonFile(String filePath, T obj) throws IOException {
		if(obj == null)
			throw  new IllegalArgumentException("Object is null");
		if (filePath == null || filePath.isEmpty())
			throw new IllegalArgumentException("File path is null or empty");

		mapper.writeValue(new File(filePath), obj);
	}
}
