package techcareer.todoAppProject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


// @Entity
// @Table(name = "users")
// @JsonIgnoreProperties({"hibernateLazyInitializer","handler","todos"}) //lazzy loading
//Getter and setter
@Data
//Constructor
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "user_id")
    private int userId;
    // @Column(name = "name")
    // @Size(min = 3,message = "İsim alanı en az 3 karakter olmak zorunda!")
    // @Size(max = 50,message = "İsim alanı geçerli bir değer giriniz!")
    // @NotEmpty(message = "İsim alanı boş geçilemez!")
    // @NotNull(message = "İsim alanı boş geçilemez!")
    private String name;
    // @Column(name = "surname")
    // @Size(min = 3,message = "Soyisim alanı en az 3 karakter olmak zorunda.")
    // @Size(max =50,message = "Soyisim alanı geçerli bir değer giriniz!")
    // @NotEmpty(message = "Soyisim alanı boş geçilemez!")
    // @NotNull(message = "Soyisim alanı boş geçilemez!")
    private String surname;
    // @Column(name = "email")
    //Validation
    //@UniqueElements(message = "Bu e-mail adresi zaten kullanılıyor!")
    // @Email(message = "Lütfen e-mail adresinin formatını kontrol ediniz.")
    // @NotNull(message = "E-mail alanı boş geçilemez!")
    // @NotEmpty(message = "E-mail alanı boş geçilemez!")
    private String email;

    //@Column(name = "password")
    //@NotNull(message = "Şifre alanı boş geçilemez!")
    //@NotEmpty(message = "Şifre alanı boş geçilemez!")
    private String password;
    //Relations
    // @OneToMany(mappedBy = "user")
    // private List<dTodo> todos;
}
