package br.ada.americanas.moviebattle.player;

import br.ada.americanas.moviebattle.movie.Movie;
import br.ada.americanas.moviebattle.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private PlayerService service;

    @Autowired
    public PlayerRestController(PlayerService service) {
        this.service = service;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Player create(@RequestBody Player player) {
        return this.service.add(player);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Player update(
            @PathVariable("id") Long id,
            @RequestBody Player player
    ) {
        player.setId(id);
        return this.service.update(player);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Player> list() {
        return service.list();
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Player get(@PathVariable("id") Long id) {
        return this.service.findById(id).get();
    }

    @DeleteMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Player delete(@PathVariable("id") Long id) {
        return service.delete(id).get();
    }
}


