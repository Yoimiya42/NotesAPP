package ucl.ac.uk.notesapp.model.entity;

import java.util.ArrayList;

public class PlainTextNote extends Note {
	private String content;

	public PlainTextNote(){
		super();
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
