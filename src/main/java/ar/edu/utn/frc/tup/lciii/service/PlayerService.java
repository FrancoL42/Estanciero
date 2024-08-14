package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;

import java.util.List;

public interface PlayerService {
    List<PlayerImplement> getAllPlayer(Long idGame);

//    void putBalance(PlayerEntity player, int balance);
//    void postPlayer(PlayerEntity player);
}
