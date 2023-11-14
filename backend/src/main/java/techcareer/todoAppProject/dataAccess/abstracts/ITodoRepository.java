package techcareer.todoAppProject.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import techcareer.todoAppProject.entities.concretes.Todo;
import techcareer.todoAppProject.entities.dtos.TodoWithUserDto;

import java.util.List;

public interface ITodoRepository extends JpaRepository<Todo,Integer> {

    //Tamamlananlar
    List<Todo> findByIsCompletedTrue();
    //Tamamlanmayanlar
    List<Todo> findByIsCompletedFalse();
    // Order By
    List<Todo> findAllByOrderByTodoId();
    // select ... from Todos t
    // inner join users u on
    // t.userId = u.id
    // @Query("Select new techcareer.todoAppProject.entities.dtos.TodoWithUserDto" +
    //        "(u.name,u.surname,t.todoId,t.todoist,t.isCompleted) " +
    //        "From User u Inner Join u.todos t")
    //List<TodoWithUserDto> getTodoWithCategoryDetails();
}
