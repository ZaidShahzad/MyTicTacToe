package com.zsbc.mytictactoe.enums;

import lombok.Getter;

import java.awt.*;

@Getter
public enum Icon {
    X("X", Color.GREEN),
    O("O", Color.CYAN);

    private String name;
    private Color color;

    Icon(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}
