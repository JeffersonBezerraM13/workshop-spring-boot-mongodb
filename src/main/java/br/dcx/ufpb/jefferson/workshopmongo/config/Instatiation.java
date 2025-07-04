package br.dcx.ufpb.jefferson.workshopmongo.config;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.dto.AuthorDTO;
import br.dcx.ufpb.jefferson.workshopmongo.dto.CommentDTO;
import br.dcx.ufpb.jefferson.workshopmongo.repository.PostRepository;
import br.dcx.ufpb.jefferson.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

/**
 * Carga inicial do banco
 */
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
        postRepository.deleteAll();

        User ana = new User(null, "Ana Pink", "ana@gmail.com");
        User alex = new User(null, "Alan Yellow", "alan@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(ana,alex,bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"),"Partiu Viagem!","Vou viajar para São Paulo",new AuthorDTO(ana));
        Post post2 = new Post(null, sdf.parse("21/03/2018"),"Bom dia","Acordei feliz hoje!", new AuthorDTO(ana));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!",sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite",sdf.parse("21/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",sdf.parse("21/03/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);
        postRepository.saveAll(Arrays.asList(post1,post2));

        ana.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(ana);
    }
}
