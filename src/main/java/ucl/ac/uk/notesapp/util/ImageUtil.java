/**
 * Utility class for handing image file operations, primarily focused on saving uploaded images.
 * Provides methods to store images in a specified directory and return their relative paths.
 * @author Luan Fangming
 * @version 2.0
 * @since 2025-03-07
 */


package ucl.ac.uk.notesapp.util;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * A utility class for managing image file storage.
 */
public class ImageUtil {

	/** The relative directory for storing images. */
	public static final String IMAGE_DIR = "/images/";

	/**
	 * Saves an uploaded image file to the indicated directory and returns its relative path.
	 * @param filePart the uploaded file part from a multipart request
	 * @param uploadDir the absolute path of the directory where the image will be saved.
	 * @return the relative path of the saved image (e.g., "/images/01234567891234_file.jpg")
	 * @throws IllegalArgumentException if filePart is null, uploadDir is null/empty, or file name is invalid
	 * @throws IOException              if the directory cannot be created or file writing fails
	 */
	public static String saveImage(Part filePart, String uploadDir) throws IOException
	{
		// Generate a unique file name using timestamp
		String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();

		File dir = new File(uploadDir);
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Failed to create upload directory: " + uploadDir);
		}

		String filePath = uploadDir + File.separator + fileName;
		filePart.write(filePath);

		String relativePath =  IMAGE_DIR + fileName;

		return relativePath;
	}

}
