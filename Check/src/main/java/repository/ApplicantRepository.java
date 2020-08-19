package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import model.Applicant;


public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	List<Applicant> findByName(String name);

	
	Applicant findById(int id);
	
	@Query("select app from Applicant app where app.name=:name and app.pass =:pass")
	Applicant findByNamePass(@Param("name")String name, @Param("pass")String pass);
	
	@Query("select count(*) from Applicant")
	int findAllCount();
	
	@Modifying
    @Query(value = "delete from  Applicant ud where ud.id=:id", nativeQuery = true)
    void deleteById(@Param ("id") Integer id);
	
	
	@Modifying
	@Transactional
    @Query(value = "TRUNCATE TABLE Applicant", nativeQuery = true)
	int alterTable();
	
	@Query("select app from Applicant app where app.level=:level")
	List<Applicant> findAllOnlevel(@Param("level")Integer level);
	
	@Modifying
	@Transactional
	@Query("update Applicant app set app.level=:level where app.id=:id")
	void changeLevel(@Param("id")int id,@Param("level")int level);
}

