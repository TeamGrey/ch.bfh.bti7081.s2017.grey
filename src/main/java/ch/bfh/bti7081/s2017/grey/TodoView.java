package ch.bfh.bti7081.s2017.grey;

/**
 * @author Ken
 */
public interface TodoView{
	interface TodoViewListener{
		void toggleStatus(boolean status);
		void addToEstimate(int time);
		void removeFromEstimate(int time);
		void removeTask();
		
	}
	public void addListener(TodoViewListener listener);
	public void setName(String labelName);
	public void setStatus(boolean status);
	public void setEstimate(int time);
}
