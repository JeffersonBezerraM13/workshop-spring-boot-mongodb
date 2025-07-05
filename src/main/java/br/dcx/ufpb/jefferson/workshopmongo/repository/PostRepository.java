package br.dcx.ufpb.jefferson.workshopmongo.repository;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.dto.AuthorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Consulta simples com @Query
 * Referências:
 * https://docs.spring.io/spring-data/mongodb/reference/
 * https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
 * https://www.mongodb.com/docs/manual/reference/operator/query/regex/
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTitleContainingIgnoreCase(String text);

    //mesma consulta de cima mas com um Query feito manualmente (personalizado)
    //o que ta dentro da query é um json
    //vamos usar o regex
    //tem a documentação do mongodb sobre essas querys
    //"Buscar posts contendo um dado string no título"
    @Query("{ 'title': { $regex: ?0, $options: 'i'} }")
    List<Post> searchTitle(String text);

    //"Buscar posts contendo um dado String em qualquer lugar(no titulo, no corpo ou comentarios) e em um dado intervalo de datas
    @Query("{ $and: [ " +
            "{ date: { $gte: ?1 } }, " +
            "{ date: { $lte: ?2 } }, " +
            "{ $or: [ " +
            "{ 'title': { $regex: ?0, $options: 'i' } }, " +
            "{ 'body': { $regex: ?0, $options: 'i' } }, " +
            "{ 'comments.text': { $regex: ?0, $options: 'i' } } " +
            "] } " +
            "] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
