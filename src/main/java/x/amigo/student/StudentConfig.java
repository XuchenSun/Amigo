package x.amigo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
                Student x= new Student(

                        "Xman",
                        "xxxxx@email.com",
                        LocalDate.of(1990, Month.APRIL,4)

                );
            Student x1= new Student(

                    "Alex",
                    "Alex@email.com",
                    LocalDate.of(2010, Month.APRIL,4)

            );
            repository.saveAll(
                    List.of(x,x1)
            );
        };
    }
}
