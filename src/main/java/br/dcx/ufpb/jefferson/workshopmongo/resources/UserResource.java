package br.dcx.ufpb.jefferson.workshopmongo.resources;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO userDto){
        User obj = userService.fromDTO(userDto);
        obj.setId(id); //garantir que o objeto terá o id da requisição
        userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        //operação que retorna nada. O código HTTP para isso é o 204, o .noContent() do ResponseEntity já volta um 204
        return ResponseEntity.noContent().build();
    }

    //esse metodo, mesmo que tenha a ver com os posts, o modelo diz que os posts estão relacionados com o usuario com referenia e o seu carregamento é lazy (tardío)
    @RequestMapping(value ="/{id}/posts",method= RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        //retornando a lista de posts que está associaodo aos usuarios
        //usuarios tem post (uma referencia e o carregamento é tardio - lazy = true) mas quando puxar o .getPost() no usuario ele carrega os posts
        return ResponseEntity.ok().body(userService.findById(id).getPosts());
    }
}
