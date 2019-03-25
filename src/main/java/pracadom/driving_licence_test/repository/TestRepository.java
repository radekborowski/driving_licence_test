package pracadom.driving_licence_test.repository;


import org.springframework.data.repository.CrudRepository;
import pracadom.driving_licence_test.model.Test;

public interface TestRepository extends CrudRepository<Test,Integer> {
}
