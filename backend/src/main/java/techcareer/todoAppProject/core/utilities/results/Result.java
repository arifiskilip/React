package techcareer.todoAppProject.core.utilities.results;

import lombok.Getter;

@Getter
public abstract class Result {
    private boolean success;
    private String message;


    public Result(boolean success){
        this.success = success;
    }

    public Result(boolean success, String message){
        this(success); //DRY principle
        this.message = message;
    }
}
