/**
 * Entity class for representing a single media item within a multimedia note.
 * Includes an image URL, hyperlink, and description. 
 * @author Luan Fangming
 * @version 1.0.3
 * @since 2025-03-10
 */

package ucl.ac.uk.notesapp.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaItem{

	@JsonProperty("url")
	private String imageUrl;
	private String hyperLink;
	private String description;

	// No-arg constructor for Jackson
	public MediaItem() {}

	public MediaItem(String imageUrl, String hyperLink, String description)
	{
		this.imageUrl = imageUrl;
		this.hyperLink = hyperLink;
		this.description = description;
	}


	// Getters and Setters
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getHyperLink() {
		return hyperLink;
	}

	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
