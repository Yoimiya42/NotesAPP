package ucl.ac.uk.notesapp.model.entity;


import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;


import ucl.ac.uk.notesapp.util.TimeUtil;

public class AbstractNote {
	private String title;
	private ArrayList<String> tags;
	private final String createdTime;
	private String modifiedTime;

	public AbstractNote(String title, ArrayList<String> tags)
	{
		this.title = title;
		this.tags = tags;
		this.createdTime = TimeUtil.getCurrentTime();
		this.modifiedTime = createdTime;
	}

	public String getTitle() {return title;}
	public List<String> getTags() {return tags;}
	public String getCreatedTime() {return TimeUtil.toReadableString(createdTime);}
	public String getModifiedTime() {return TimeUtil.toReadableString(modifiedTime);}

	public void setTitle(String title) {this.title = title;}
	public void setTags(ArrayList<String> tags) {this.tags = tags;}
	public void setModifiedTime() {this.modifiedTime = TimeUtil.getCurrentTime();}


}
