package br.ada.americanas.moviebattle.battle;

import br.ada.americanas.moviebattle.movie.Movie;
import br.ada.americanas.moviebattle.player.Player;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battles")
public class BattleRestController {

    private BattleService service;

    public BattleRestController(BattleService service){
        this.service=service;
    }
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Battle create(@RequestBody Player player) {
        return this.service.create(player);
    }


    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Battle get(@PathVariable("id") Long id) {
        return this.service.find(id).get();
    }
}
