package br.dcx.ufpb.jefferson.workshopmongo.resources;

import br.dcx.ufpb.jefferson.workshopmongo.domain.Post;
import br.dcx.ufpb.jefferson.workshopmongo.domain.User;
import br.dcx.ufpb.jefferson.workshopmongo.dto.UserDTO;
import br.dcx.ufpb.jefferson.workshopmongo.resources.util.URL;
import br.dcx.ufpb.jefferson.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{id}",method= RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        return ResponseEntity.ok().body(postService.findById(id));
    }

    @RequestMapping(value="/titlesearch",method= RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text",defaultValue="") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitleContaining(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/fullsearch",method= RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text",defaultValue="") String text,
            @RequestParam(value="minDate",defaultValue="") String minDate,
            @RequestParam(value="maxDate",defaultValue="") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
