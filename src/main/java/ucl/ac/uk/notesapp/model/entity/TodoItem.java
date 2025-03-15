/**
 * Entity class representing a single todo item within a todoListNote.
 * Includes a task description and a completion status.
 * @author Luan Fangming
 * @version 2.0
 * @since 2025-03-10
 */

package ucl.ac.uk.notesapp.model.entity;

public class TodoItem{

	private String task;
	private boolean isCompleted;

	// No-args constructor for Jackson
	public TodoItem(){
	}

	public TodoItem(String task)
	{
		this.task = task;
		this.isCompleted = false;
	}

	// Getters and Setters
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean completed) {
		isCompleted = completed;
	}
}