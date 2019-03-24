package pracadom.driving_licence_test.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pracadom.driving_licence_test.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
