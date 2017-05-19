package ch.bfh.bti7081.s2017.grey;

/**
 * Created by Nic on 15.05.17.
 */
public interface AppointmentView {
    interface AppontmentViewListener {
        void saveClick();
        void editClick();
    }
    public void addListener(AppontmentViewListener listener);
}
