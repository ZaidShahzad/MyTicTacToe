package com.zsbc.mytictactoe.struct;

import com.zsbc.mytictactoe.enums.Icon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String name;
    private Icon icon;
    private int score;

    public Player(String name) {
        this.name = name;
        this.icon = null;
        this.score  = 0;
    }

    public void addScore(int number) {
        this.score += number;
    }


}
