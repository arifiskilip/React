package techcareer.todoAppProject.business.abstracts;

import techcareer.todoAppProject.core.utilities.results.DataResult;
import techcareer.todoAppProject.core.utilities.results.Result;
import techcareer.todoAppProject.entities.concretes.User;

import java.util.List;

public interface IUserService {
    DataResult<List<User>> getAll();
    Result Add(User user);
    DataResult<User> getByEmail(String email);
    DataResult<User> login(String email, String password);
}
