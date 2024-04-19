package com.develhope.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
//@Repository
//public interface RentRepository extends JpaRepository<Rent,Long> {
//	@Query(value = "SELECT * FROM RENT WHERE ID= :id", nativeQuery = true)
//	List<Rent> rentList(@Param("id")Long id);
//}