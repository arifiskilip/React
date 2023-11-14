package techcareer.todoAppProject.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import techcareer.todoAppProject.business.abstracts.ITodoService;
import techcareer.todoAppProject.core.utilities.results.DataResult;
import techcareer.todoAppProject.core.utilities.results.ErrorDataResult;
import techcareer.todoAppProject.core.utilities.results.Result;
import techcareer.todoAppProject.entities.concretes.Todo;
import techcareer.todoAppProject.entities.dtos.TodoWithUserDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodosController {
    private ITodoService todoService;

    @Autowired
    public TodosController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.todoService.GetAll());
    }
    @GetMapping("/completedtodos")
    public ResponseEntity<?> getCompletedTodos(){
        return ResponseEntity.ok(this.todoService.CompletedTodo());
    }
    @PostMapping("/deletedonetasks")
    public ResponseEntity<?> deleteDoneTodo()
    {
        return ResponseEntity.ok(this.todoService.DeleteDoneTask());
    }
    @PostMapping("/iscomletedupdate/{id}")
    public ResponseEntity<?> isCompletedUpdate(@RequestParam int todoId){
        return ResponseEntity.ok(this.todoService.isCompletedUpdate(todoId));
    }
    @GetMapping("/gettodos")
    public  ResponseEntity<?> getTodos(){
        return  ResponseEntity.ok(this.todoService.GetTodos());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@Valid @RequestBody Todo todo){
        return ResponseEntity.ok(this.todoService.Add(todo));
    }
    @PostMapping("/remove/{id}")
    public ResponseEntity<?> removeTodo(int id){
        return ResponseEntity.ok(this.todoService.Remove(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodo(@RequestParam int id,@RequestBody Todo todo){
        return ResponseEntity.ok(this.todoService.Update(id,todo));
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.todoService.GetById(id));
    }

    @PostMapping("/deletealltasks")
    public ResponseEntity<?> deletealltasks()
    {
        return ResponseEntity.ok(this.todoService.DeleteAllTask());
    }
    //Global exception handler
    //Sistem de oluşabilecek bütün doğrulama hatalarını yakala.
    @ExceptionHandler(MethodArgumentNotValidException.class) //SpringBoot.Validation
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Code : 400
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        //key, value
        // Hatanın oluştuğu alan, nedeni
        // password, şifre alanı boş geçilemez!
        Map<String,String> validationErrors = new HashMap<String,String>();
        for (FieldError fieldError:exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<Object>(validationErrors,"Doğrulama hatası");

    }
}
