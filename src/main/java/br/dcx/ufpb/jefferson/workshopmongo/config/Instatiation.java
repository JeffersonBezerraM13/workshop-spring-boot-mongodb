package br.dcx.ufpb.jefferson.workshopmongo.config;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.repository.PostRepository;
import br.dcx.ufpb.jefferson.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        User maria = new User(null, "Ana Pink", "maria@gmail.com");
        User alex = new User(null, "Alan Yellow", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        postRepository.deleteAll();
        Post post1 = new Post(null, sdf.parse("21/03/2018"),"Partiu Viagem!","Vou viajar para São Paulo",maria);
        Post post2 = new Post(null, sdf.parse("21/03/2018"),"Bom dia","Acordei feliz hoje!",maria);
        postRepository.saveAll(Arrays.asList(post1,post2));
    }
}
