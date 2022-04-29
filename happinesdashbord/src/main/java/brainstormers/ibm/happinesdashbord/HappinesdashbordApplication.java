package brainstormers.ibm.happinesdashbord;

import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HappinesdashbordApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(HappinesdashbordApplication.class, args);
		UserRepository userRepository =
				configurableApplicationContext.getBean(UserRepository.class);
		User myUser = new User("admin", "admin", "admin@gmail.com");
		userRepository.save(myUser);

	}

}
