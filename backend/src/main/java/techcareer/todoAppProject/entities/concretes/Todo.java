package techcareer.todoAppProject.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todos") //table name

// Lombok
@Data // getter and setter
@AllArgsConstructor  //all parameter constructor
@NoArgsConstructor   //no parameter constructor
public class Todo {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity (1,1)
    @Column(name = "todo_id") //column name
    private int todoId;
    // @Column(name = "userId")
    // private int userId;
    @Column(name = "todoist")
    @NotNull(message = "Lütfen geçerli değer giriniz.")
    @NotEmpty(message = "Lütfen geçerli değer giriniz.")
    @Size(min = 3,message = "3 karakterden büyük olmalı!")
    @Size(max = 50,message = "50 karakterden küçük olmalı!")
    private String todoist;
    @Column(name = "is_completed")
    private boolean isCompleted;

    // @ManyToOne()
    // @JoinColumn(name = "user_id")
    // private User user;
}
