/**
 *  Utility class for handing time-related operations, such as formatting and
 *  retrieving current time.
 *  Provides methods to get the current time in a serialized format and convert
 *  it to a readable string.
 *
 * @author Luan Fangming
 * @version 3.0
 * @since  2025-03-07
*/

package ucl.ac.uk.notesapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtil {
	private static final DateTimeFormatter READABLE_FORMATTER =
			DateTimeFormatter.ofPattern("d MMMM, HH:mm:ss", Locale.ENGLISH);
	private static final DateTimeFormatter SERIALIZED_FORMATTER =
			DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	/**
	 * Retrieves the current time formatted as a serialized string
	 * (e.g., 2025-03-07 14:42:07   ->  20250307144207)
	 * @return the current time in serialized format
	 */
	public static String getCurrentTime() {
		return LocalDateTime.now().format(SERIALIZED_FORMATTER);
	}

	/**
	 * Converts a serialized time string into a human-readable format
	 * (e.g., 20250307144207  -> 07 March, 14:42:07)
	 * @param sequencedTime serialized time string
	 * @return the time in a readable format
	 */
	public static String toReadableString(String sequencedTime) {
		return LocalDateTime.parse(sequencedTime, SERIALIZED_FORMATTER).format(READABLE_FORMATTER);
	}

}
