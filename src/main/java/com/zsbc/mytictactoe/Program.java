package com.zsbc.mytictactoe;

import com.zsbc.mytictactoe.struct.MainMenu;
import com.zsbc.zsbcutils.Debug;

public class Program {

    /*
    Project Description:
    A very simple tic tac toe game written in Java.
     */

    private static Debug debug;

    public static void main(String[] args) {
        debug = new Debug(false);
        new MainMenu();
    }

    public static Debug getDebug() {
        return debug;
    }
}
