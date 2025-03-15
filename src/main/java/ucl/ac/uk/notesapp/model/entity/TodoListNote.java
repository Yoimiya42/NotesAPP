/**
 * Entity class representing a to to-do list note extending the Abstract Note class.
 * 
 * @author: Luan Fangming
 * @version: 1.2
 * @since 2025-03-10
 */


package ucl.ac.uk.notesapp.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoListNote extends Note{

	@JsonProperty("todoItems")
	private List<TodoItem> todoItems;

	public TodoListNote() {
		super();
		this.todoItems = new ArrayList<>();
	}

	public void addTodoItem(String task) {
		this.todoItems.add(new TodoItem(task));
	}

	// Getter and Setter
	public List<TodoItem> getTodoItems() {
		return todoItems;
	}

	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}
}



