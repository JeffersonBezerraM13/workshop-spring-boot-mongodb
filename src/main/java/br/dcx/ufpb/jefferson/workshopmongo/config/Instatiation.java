package br.dcx.ufpb.jefferson.workshopmongo.config;

import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        User maria = new User(null, "Ana Pink", "maria@gmail.com");
        User alex = new User(null, "Alan Yellow", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria,alex,bob));
    }
}
