package ucl.ac.uk.notesapp.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Note {
	private String title;
	private List<String> tags;

	public Note(){}

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

	@Override
	public String toString() {
		return "Note{" +
				"title='" + title + '\'' +
				", tags=" + tags +
				'}';
	}
}
