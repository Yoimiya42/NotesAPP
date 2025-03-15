/**
 * Entity class representing a plain text note, extending the Abstract Note class.
 * 
 * @author: Luan Fangming
 * @version: 2.0
 * @since 2025-03-07
 */



package ucl.ac.uk.notesapp.model.entity;

public class TextNote extends Note {
	private String content;

	// No-arg constructor for Jackson
	public TextNote(){
		super();
	}

	// Getters and Setters
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
