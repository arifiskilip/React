package techcareer.todoAppProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techcareer.todoAppProject.business.abstracts.ITodoService;
import techcareer.todoAppProject.core.utilities.results.*;
import techcareer.todoAppProject.dataAccess.abstracts.ITodoRepository;
import techcareer.todoAppProject.entities.concretes.Todo;
import techcareer.todoAppProject.entities.dtos.TodoWithUserDto;


import java.util.List;
@Service
public class TodoManager  implements ITodoService {
    private ITodoRepository todoRepository;

    @Autowired
    public TodoManager(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

   // Todo add
    @Override
    public Result Add(Todo todo) {
        try{
            todo.setCompleted(false);
            this.todoRepository.save(todo);
            return new SuccessResult("Ekleme işlemi başarılı!");
        }
        catch (Exception exc){
            return new ErrorResult(exc.getMessage());
        }

    }
  // Todo Remove
    @Override
    public Result Remove(int id) {
        try{
            Todo todo = this.todoRepository.findById(id).orElse(null);
            if (todo != null){
                this.todoRepository.delete(todo);
                return new SuccessResult("Silme işlemi başarılı!");
            }
            return new ErrorResult("id:"+id+" sahip veri bulunamadı!");
        }
        catch (Exception exc){
            return new ErrorResult(exc.getMessage());
        }
    }
 // Todo Update
    @Override
    public Result Update(int id,Todo todo) {
        try{
            Todo updatedTodo = this.todoRepository.findById(id).orElse(null);
            if (updatedTodo != null){
                updatedTodo.setTodoist(todo.getTodoist());
                this.todoRepository.save(updatedTodo);
                return new SuccessResult("Güncelleme işlemi başarılı!");
            }
            return new ErrorResult("id:"+todo.getTodoId()+" sahip veri bulunamadı!");
        }
        catch (Exception exc){
            return new ErrorResult(exc.getMessage());
        }
    }

    @Override
    public DataResult<Todo> GetById(int id) {
        try{
            Todo checkTodo = this.todoRepository.findById(id).orElse(null);
            if (checkTodo != null){
                return new SuccessDataResult<Todo>(checkTodo,"Listeleme işlemi başarılı!");
            }
            return new SuccessDataResult<Todo>(null,"id:"+id+" sahip veri bulunamadı!");
        }
        catch (Exception exc){
            return new ErrorDataResult<Todo>(null,exc.getMessage());
        }
    }

    // GetAll
    @Override
    public DataResult<List<Todo>> GetAll() {
        try{
            return new SuccessDataResult<List<Todo>>(this.todoRepository.findAllByOrderByTodoId(),"Listeleme" +
                    "işlemi başarılı!");
        }
        catch (Exception exc){
            return new ErrorDataResult<List<Todo>>(null,exc.getMessage());
        }
    }

    @Override
    public DataResult<List<Todo>> CompletedTodo() {
        try{
            return new SuccessDataResult<List<Todo>>(this.todoRepository.findByIsCompletedTrue(),"Listeleme" +
                    "işlemi başarılı!");
        }
        catch (Exception exc){
            return new ErrorDataResult<List<Todo>>(null,exc.getMessage());
        }
    }

    @Override
    public DataResult<List<Todo>> GetTodos() {
        try {
            return new SuccessDataResult<List<Todo>>(this.todoRepository.findByIsCompletedFalse(),"" +
                    "Listelem işlemi başarılı!");
        }
        catch (Exception exc){
            return new ErrorDataResult<List<Todo>>(null,exc.getMessage());
        }
    }

    @Override
    public Result DeleteDoneTask() {
        try{
            List<Todo> completedTodo = todoRepository.findByIsCompletedTrue();
            if (!completedTodo.isEmpty()){
                this.todoRepository.deleteAll(completedTodo);
                return new SuccessResult("Silme işlemi başarılı!");
            }
            return new ErrorResult("Silinecek veri bulunamadı!");
        }
        catch (Exception exc){
            return new ErrorResult(exc.getMessage());
        }

    }

    @Override
    public Result DeleteAllTask() {
        try{
            List<Todo> todos = todoRepository.findAll();
            if (!todos.isEmpty()){
                this.todoRepository.deleteAll(todos);
                return new SuccessResult("Silme işlemi başarılı!");
            }
            return new ErrorResult("Silinecek veri bulunamadı!");
        }
        catch (Exception exc){
            return new ErrorResult(exc.getMessage());
        }

    }

    @Override
    public DataResult<Todo> isCompletedUpdate(int todoId) {
        try{
            Todo checkTodo = this.todoRepository.findById(todoId).orElse(null);
            if (checkTodo != null){
                checkTodo.setCompleted(!checkTodo.isCompleted());
                this.todoRepository.save(checkTodo);
                return new SuccessDataResult<Todo>(checkTodo,"İşlem başarılı!");
            }
            return new ErrorDataResult<Todo>(null,"İşlem başarısız");
        }
        catch (Exception exc){
            return new ErrorDataResult<Todo>(null,exc.getMessage());
        }
}

}
