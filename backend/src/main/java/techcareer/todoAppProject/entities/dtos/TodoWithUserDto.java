package techcareer.todoAppProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoWithUserDto {
    private String name;
    private String surname;
    private int id;
    private String todoist;
    private boolean isCompleted;
}
