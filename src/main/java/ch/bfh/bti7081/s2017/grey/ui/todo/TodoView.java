package ch.bfh.bti7081.s2017.grey.ui.todo;

/**
 * @author Ken
 */
public interface TodoView{
	interface TodoViewListener{
		/**
		 * @param status Status to be set of this task
		 */
		void toggleStatus(boolean status);
		
		/**
		 * @param time Time to be added to this task
		 */
		void addToEstimate(int time);
		
		/**
		 * @param time Time to be removed from this task
		 */
		void removeFromEstimate(int time);
		
		/**
		 * Delete task
		 */
		void removeTask();
		
	}
	/**
	 * @param listener Listener to be added to the view
	 */
	void addListener(TodoViewListener listener);
	
	/**
	 * @param labelName Name to be set of the task
	 */
	void setName(String labelName);
	
	/**
	 * @param status Status to be set of the task
	 */
	void setStatus(boolean status);
	
	/**
	 * @param time Time to be set of the task
	 */
	void setEstimate(int time);
}
