/**
 * Abstract base class representing a note entity in the application.
 * Defines common properties and behaviours for all type of notes, such as title, tags, timestamp,
 * subject and trash status.
 *
 * @author Luan Fangming
 * @version 2.0.1
 * @since 2025-03-07
 */


package ucl.ac.uk.notesapp.model.entity;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ucl.ac.uk.notesapp.util.TimeUtil;

public abstract class Note {

	private String title;
	private List<String> tags = new ArrayList();
	private final String createdTime;
	private String modifiedTime;
	private String subject;
	private boolean inTrash = false;
//	private String content;

	/**
	 * Timestamps are generated when an object is instantiated.
	 */
	public Note() {
		this.createdTime = TimeUtil.getCurrentTime();
		this.modifiedTime = createdTime;
	}


	// Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	/**
	 * Update the modified time to the current time.
	 */
	public void setModifiedTime() {
		this.modifiedTime = TimeUtil.getCurrentTime();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Returns the note's unique identifier, derived from its creation time.
	 * Ignored during JSON deserialization.
	 * 
	 * @return the creation timestamp as the note's ID
	 */
	@JsonIgnore
	public String getId() {
		return getCreatedTime();
	}

	/**
	 * Returns the creation time in a human-readable format for webpage display.
	 * 
	 * @return formatted creation time (e.g., "07, March, 18:14:23")
	 */
	public String showCreatedTime() {
		return TimeUtil.toReadableString(createdTime);
	}

	/**
	 * Returns the modified time in a human-readable format for webpage display.
	 * 
	 * @return formatted modified time (e.g., "07, March, 18:14:23")
	 */
	public String showModifiedTime() {
		return TimeUtil.toReadableString(modifiedTime);
	}

	public boolean isInTrash() {
		return inTrash;
	}

	public void setInTrash(boolean onTrash) {
		this.inTrash = onTrash;
	}


	// public void setContent(String content) {}
}
