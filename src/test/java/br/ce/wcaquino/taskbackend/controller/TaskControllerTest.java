package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController taskController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoSalvarTarefaSemDescricao() {
        Task task = new Task();
        task.setDueDate(LocalDate.now());
//        task.setTask("description");
        try {
            taskController.save(task);
            Assert.fail("Não deve chegar nesse ponto");
        } catch (Exception e){
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoSalvarTarefaSemData() {
        Task task = new Task();
//        task.setDueDate(LocalDate.now());
        task.setTask("description");
        try {
            taskController.save(task);
            Assert.fail("Não deve chegar nesse ponto");
        } catch (Exception e){
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoSalvarTarefaComdDataPassada() {
        Task task = new Task();
        task.setDueDate(LocalDate.of(2010, 01, 01));
        task.setTask("description");
        try {
            taskController.save(task);
            Assert.fail("Não deve chegar nesse ponto");
        } catch (Exception e){
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void salvarTarefaComSucesso() throws ValidationException {
        Task task = new Task();
        task.setDueDate(LocalDate.of(2030, 01, 01));
        task.setTask("description");
        taskController.save(task);

        Mockito.verify(taskRepo).save(task);
    }
}
