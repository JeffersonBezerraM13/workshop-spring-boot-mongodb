package br.dcx.ufpb.jefferson.workshopmongo.services;

import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.dto.UserDTO;
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

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public User update(User obj) {
        User newObj = userRepository.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundExeception(obj.getId()));
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(User user, User userUpdate) {
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
    }

    public void delete(String id){
        this.findById(id); //verificando se já existe, e já aproveitando o código com tratamento de exceções
        userRepository.deleteById(id);
    }

    public User fromDTO(UserDTO userDto) {
        return new User(userDto.getId(),userDto.getName(),userDto.getEmail());

    }
}
