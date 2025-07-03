package br.dcx.ufpb.jefferson.workshopmongo.services;

import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.repository.UserRepository;
import br.dcx.ufpb.jefferson.workshopmongo.services.exception.ObjectNotFoundExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //mecanismo de injeção de depedencia do spring boot
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundExeception(id));
    }
}
