package br.ada.americanas.moviebattle.player;

import br.ada.americanas.moviebattle.config.ConstraintViolationToErrorsConverter;
import br.ada.americanas.moviebattle.movie.Movie;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/app/players")
public class PlayerAppController {

    private PlayerService service;
    private ConstraintViolationToErrorsConverter exceptionConverter;

    @Autowired
    public PlayerAppController (PlayerService service, ConstraintViolationToErrorsConverter exceptionConverter){
        this.service=service;
        this.exceptionConverter=exceptionConverter;
    }
    @GetMapping
    public String get(Model model){
        List<Player> players = service.list();
        //Ordenada a lista baseado no nome do jogador
        players.sort(Comparator.comparing(Player::getName));
        model.addAttribute("players",players);
        return "players/list_players";
    }
    @GetMapping("/create")
    public String create(Model model) {
        // Fornece acesso a página html que representa o formulário. O cadastro acontece pelo POST
        model.addAttribute("player", new Player());
        return "players/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    ) {
        Player player = service.findById(id).get();
        model.addAttribute("player", player);
        return "players/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            Model model
    ) {
        service.delete(id);
        return "redirect:/app/players";
    }

    @PostMapping
    public String save(
            @ModelAttribute Player player,
            BindingResult result
    ) {
        try {
            service.add(player);
        } catch(ConstraintViolationException ex) {
            exceptionConverter.convert(ex.getConstraintViolations(), result);
            return "players/form";
        }
        return "redirect:/app/players";
    }

}
