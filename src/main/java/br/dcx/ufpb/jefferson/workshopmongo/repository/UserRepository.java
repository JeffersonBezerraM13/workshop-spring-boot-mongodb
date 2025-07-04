package br.dcx.ufpb.jefferson.workshopmongo.repository;

import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
