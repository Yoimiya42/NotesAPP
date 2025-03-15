/**
 * Enumeration class representing the type of notes supported in the application.
 * Each type is associated with a predefined suffix and a corresponding note class.
 * 
 * @author Luan Fangming
 * @version 4.0.2
 * @since 2025-03-08
 */


package ucl.ac.uk.notesapp.model.entity;

public enum NoteType {
	TEXT("T", TextNote.class),
	MULTIMEDIA("M", MultimediaNote.class),
	TODOLIST("D", TodoListNote.class);

	private final String suffix;
	private final Class<? extends Note> noteClass;

	/**
	 * Constructor for NoteType enumeration.
	 * 
	 * @param suffix	the unique identifier for the note type
	 * @param noteClass	the class representing the note type
	 */
	NoteType(String suffix, Class<? extends Note> noteClass)
	{
		this.suffix = suffix;
		this.noteClass = noteClass;
	}

	public String getSuffix(){
		return suffix;
	}

	public Class<? extends Note> getNoteClass(){
		return noteClass;
	}

	public static NoteType fromSuffix(String suffix){
		for(NoteType type : values())
		{
			if(type.getSuffix().equals(suffix))
				return type;
		}
		return TEXT;
	}

	/**
	 * Determines the NoteType of a given note instance.
	 * 
	 * @param note 	the note instance to be examined
	 * @return		the matching NoteType of the note instance. Defaults to TEXT if no match is found.
	 */
	public static NoteType fromNote(Note note)
	{
		for (NoteType type : values())
		{
			if(type.getNoteClass().equals(note.getClass()))
				return type;
		}
		return TEXT;
	}

}
