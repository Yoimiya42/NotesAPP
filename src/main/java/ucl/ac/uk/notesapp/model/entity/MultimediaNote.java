/**
 * Entity class representing a multimedia note, extending the Abstract Note class.
 * @author: Luan Fangming
 * @version: 1.3
 * @since 2025-03-10
 */

package ucl.ac.uk.notesapp.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultimediaNote extends Note{

	@JsonProperty("mediaItems")
	private List<MediaItem> mediaItems;
	private NoteType noteType;

	public MultimediaNote() {
		super();
		this.mediaItems = new ArrayList<>();
	}

	// Getter and Setter
	public List<MediaItem> getMediaItems() {
		return mediaItems;
	}

	public void setMediaItems(List<MediaItem> mediaItems) {
		this.mediaItems = mediaItems;
	}

	
	public void addMediaItem(String mediaUrl, String hyperLink, String description)
	{
		this.mediaItems.add(new MediaItem(mediaUrl, hyperLink, description));
	}
}


