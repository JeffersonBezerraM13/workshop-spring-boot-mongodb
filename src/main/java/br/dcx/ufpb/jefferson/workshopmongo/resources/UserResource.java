package br.dcx.ufpb.jefferson.workshopmongo.resources;

import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.dto.UserDTO;
import br.dcx.ufpb.jefferson.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    //@GetMapping -> mesma coisa que o de cima
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(userService.findAll().stream()
                .map(x -> new UserDTO(x))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}",method= RequestMethod.GET)
    public UserDTO findById(@PathVariable String id){
        return new UserDTO(userService.findById(id));
    }

    //@RequestMapping(method= RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
        User user = userService.fromDTO(userDto);
        user = userService.insert(user);
        //boa prática: cabeçalho com o URL do novo obj criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        //created retorna 201, que é o codigo de resposta HTTP para novo recurso
        return ResponseEntity.created(uri).build();
    }
}
