package br.dcx.ufpb.jefferson.workshopmongo.services;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.repository.PostRepository;
import br.dcx.ufpb.jefferson.workshopmongo.services.exception.ObjectNotFoundExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    //mecanismo de injeção de depedencia do spring boot
    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundExeception(id));
    }
}
