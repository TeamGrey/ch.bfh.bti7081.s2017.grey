package ch.bfh.bti7081.s2017.grey;

/**
 * Created by hannes on 5/17/17.
 */
public interface PatientView {
interface PatientViewListener{
    void editClick();
    void saveClick();
}
public void addListener(PatientViewListener listener);
}
