package br.dcx.ufpb.jefferson.workshopmongo.repository;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.dto.AuthorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTitleContainingIgnoreCase(String text);

    //mesma consulta de cima mas com um Query feito manualmente (personalizado)
    //o que ta dentro da query é um json
    //vamos usar o regex
    //tem a documentação do mongodb sobre essas querys
    @Query("{ 'title': { $regex: ?0, $options: 'i'}}")
    List<Post> searchTitle(String text);
}
