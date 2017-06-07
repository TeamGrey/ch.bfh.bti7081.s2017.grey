package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import java.util.List;

/**
 * Created by gabor on 07/06/17.
 */
public interface FinishAppointmentView {
    interface FinishAppointmentViewListener {
        void addDelayClick(int time);
        void subtractDelayClick(int time);
        void finishClick();
    }
    void addListener(FinishAppointmentViewListener listener);
    void setAppointment(Appointment appointment);
    void setTasks(List<Task> tasks);
    void setDelay(int delay);
}
