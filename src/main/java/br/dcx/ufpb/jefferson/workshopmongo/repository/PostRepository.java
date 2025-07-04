package br.dcx.ufpb.jefferson.workshopmongo.repository;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
