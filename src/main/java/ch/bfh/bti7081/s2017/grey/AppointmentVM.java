package ch.bfh.bti7081.s2017.grey;

import java.time.LocalDate;
/**
 * Created by hannes on 07.05.17.
 */
public class AppointmentVM {
	private LocalDate startTime;
	private LocalDate endTime;
	public AppointmentVM(LocalDate startTime , LocalDate endTime ){
		this.startTime =startTime;
		this.endTime=endTime;

	}
	public LocalDate getStartTime(){
		return this.startTime;
	}

	public LocalDate getEndTime(){
		return this.endTime;
	}
	public void setStartTime(LocalDate newStartTime ){
		this.startTime=newStartTime;
	}
	public void setEndTime(LocalDate newEndTime ){
		this.endTime=newEndTime;
	}
	public static void loggedIn() {
		MyUI.loggedIn();
	}
}
