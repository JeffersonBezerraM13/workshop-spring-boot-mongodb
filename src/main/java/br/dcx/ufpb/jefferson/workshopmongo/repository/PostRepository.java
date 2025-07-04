package br.dcx.ufpb.jefferson.workshopmongo.repository;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.dto.AuthorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTitleContainingIgnoreCase(String text);
}
