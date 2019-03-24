package pracadom.driving_licence_test.auth.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pracadom.driving_licence_test.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
