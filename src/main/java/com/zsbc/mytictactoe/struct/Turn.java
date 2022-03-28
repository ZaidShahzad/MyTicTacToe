package com.zsbc.mytictactoe.struct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Turn {

    private Player player;
    private int timer = 5;

    public Turn(Player player) {
        this.player = player;
    }
}
