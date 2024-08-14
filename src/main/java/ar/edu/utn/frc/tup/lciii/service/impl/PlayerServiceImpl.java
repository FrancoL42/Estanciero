package ar.edu.utn.frc.tup.lciii.service.impl;

import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.converts.PlayerConvert;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.repositories.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.PlayerRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    PlayerRepository playerRepo = new PlayerRepositoryImpl();
    @Override
    public List<PlayerImplement> getAllPlayer(Long idGame) {
        List<PlayerEntity> playerEntities = playerRepo.getAllPlayersByGameId(idGame);
        List<PlayerImplement> players = new ArrayList<>();
        for (PlayerEntity entity : playerEntities){

            PlayerImplement player = PlayerConvert.ConvertToPlayer(entity);
            player.setSquare(Board.getInstance().getSquare(entity.getSquare().getNumber()));
            players.add(player);
        }
        return players;
    }
//    @Override
//    public void putBalance(PlayerEntity player,int balance) {
//        playerRepo.putBalance(player,balance);
//    }
//
//    @Override
//    public void postPlayer(PlayerEntity player) {
//        playerRepo.postPlayer(player);
//    }
}
