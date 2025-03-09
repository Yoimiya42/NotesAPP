package ucl.ac.uk.notesapp.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtil {
	private static final DateTimeFormatter READABLE_FORMATTER =
			DateTimeFormatter.ofPattern("d MMMM, HH:mm:ss", Locale.ENGLISH);
	private static final DateTimeFormatter SERIALIZED_FORMATTER =
			DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	public static String getCurrentTime()
	{   return LocalDateTime.now().format(SERIALIZED_FORMATTER);   }

	public static String toReadableString(String sequencedTime)
	{
		return LocalDateTime.parse(sequencedTime, SERIALIZED_FORMATTER).format(READABLE_FORMATTER);
	}
	
}
