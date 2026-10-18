package in.varma.edutrack.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getInstance(){

        return new ModelMapper();
    }

    @Bean
    public RestTemplate getRestTamplate(){

        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
 /*       BCrypt is better than Base64 for storing passwords securely.
          Base64 is an encoding technique, and anyone can easily decode it to get the original password.
          But BCrypt converts the password into a hash that cannot be directly reversed.
          BCrypt also uses a random salt, which makes the hash different even when two users have the same password.
          This makes password attacks more difficult.
          So, we use BCrypt instead of Base64 to store passwords securely in the database.
*/
    }
}