package ch.bfh.bti7081.s2017.grey.ui.todo;

import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

/**
 * @author Ken
 */
public interface TodoListView{
    interface TodoListViewListener{
    	/**
    	 * Create a new task
    	 * @param taskName Name of the new task
    	 * @param window Current opened window
    	 */
    	void saveNewTask(String taskName, Window window);
    }
    
    /**
     * @param listener Listener to be added to the view
     */
    void addListener(TodoListViewListener listener);
    
    /**
     * @param task Task to be added to the current list
     */
    void addTask(Task task);
    
    /**
     * Creation of the new task-button
     */
    void addNewTaskButton();
    
    /**
     * @param window Wondow to be closed
     */
    void closeWindow(Window window);
}
