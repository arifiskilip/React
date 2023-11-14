package techcareer.todoAppProject.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class AuthController {
    @GetMapping("/user")
    public Map<String, String> getCurrentUser() {
        // Oturumu açmış kullanıcının bilgilerini döndürün
        //var user = SecurityContextHolder.getContext().getAuthentication();
        //var userId = (User) user.getPrincipal();
        //return Collections.singletonMap("id", userId.getName());
        return null;
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Oturumu kapatma işlemi
        // new SecurityContextLogoutHandler().logout(request, null, null);
        // return ResponseEntity.ok("Oturum kapatıldı.");
        return null;
    }
}
