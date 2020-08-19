package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByName(String name);

	Employee findById(long id);
	
	@Query("select count(*) from Employee")
	int findAllCount();
	
	@Query("select emp from Employee emp where emp.name=:name and emp.pass =:pass")
	Employee findByNamePass(@Param("name")String name, @Param("pass")String pass);
	
	@Modifying
    @Query(value = "delete from  Employee ud where ud.id=:id", nativeQuery = true)
    int deleteTenantReview(@Param ("id") Integer id);
	
	
	@Modifying
	@Transactional
    @Query(value = "TRUNCATE TABLE Employee", nativeQuery = true)
	int alterTable();
}
