package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

/**
 * @author Ken
 */
public interface TodoListView{
    interface TodoListViewListener{
    	void saveNewTask(String taskName, Window window);
    }
    public void addListener(TodoListViewListener listener);
    void addTask(Task task);
    void addNewTaskButton();
    void closeWindow(Window window);
}
