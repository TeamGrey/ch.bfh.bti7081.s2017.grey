package ch.bfh.bti7081.s2017.grey.service;

import static org.junit.Assert.assertEquals;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.database.util.JPAHibernateTest;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Quentin
 */
public class TaskServiceTest extends JPAHibernateTest {

  private TaskService taskService;
  private AppointmentService appointmentService;
  private Appointment appointment;

  @Before
  public void setup() {
    taskService = new TaskServiceImpl(em);
    appointmentService = new AppointmentServiceImpl(em);
    appointment = appointmentService.getAppointmentById(1);
    taskService.createTask("Putzen", appointment);
  }

  @Test
  public void getTaskByAppointment() {
    Task task = taskService.getTasksByAppointment(appointment).get(0);
    assertEquals("Putzen", task.getName());
  }

  @Test
  public void testTaskDuration() {
    taskService.setDuration(taskService.getAllTasks().get(0), 10);
    assertEquals(10, taskService.getAllTasks().get(0).getDuration());
    taskService.addToDuration(taskService.getAllTasks().get(0), 10);
    assertEquals(20, taskService.getAllTasks().get(0).getDuration());
    taskService.removeFromDuration(taskService.getAllTasks().get(0), 5);
    assertEquals(15, taskService.getAllTasks().get(0).getDuration());
  }


}
