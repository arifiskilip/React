package techcareer.todoAppProject.business.abstracts;



import techcareer.todoAppProject.core.utilities.results.DataResult;
import techcareer.todoAppProject.core.utilities.results.Result;
import techcareer.todoAppProject.entities.concretes.Todo;
import techcareer.todoAppProject.entities.dtos.TodoWithUserDto;

import java.util.List;

public interface ITodoService {

    Result Add(Todo todo);
    Result Remove(int id);
    Result Update(int id,Todo todo);
    DataResult<Todo> GetById(int id);
    DataResult<List<Todo>> GetAll();
    DataResult<List<Todo>> CompletedTodo();
    DataResult<List<Todo>> GetTodos();
    Result DeleteDoneTask();
    Result DeleteAllTask();
    DataResult<Todo> isCompletedUpdate(int todoId);
}
