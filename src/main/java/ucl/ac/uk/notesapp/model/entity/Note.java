package ucl.ac.uk.notesapp.model.entity;
import ucl.ac.uk.notesapp.util.TimeUtil;

import java.util.List;

public abstract class Note {
	private String title;
	private List<String> tags;
	private final String createdTime;
	private String modifiedTime;
	private String subject;

	public Note() {
		this.createdTime = TimeUtil.getCurrentTime();
		this.modifiedTime = createdTime;
	}

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

	public void setModifiedTime() {
		this.modifiedTime = TimeUtil.getCurrentTime();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getId()
	{
		return this.createdTime;
	}

	public String showCreatedTime() {
		return TimeUtil.toReadableString(createdTime);
	}

	public String showModifiedTime() {
		return TimeUtil.toReadableString(modifiedTime);
	}

	public abstract void setContent(String content);
}
