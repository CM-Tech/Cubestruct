package org.cubestruct.game;

import cm.cubestruct.engine.GameEngine;
import cm.cubestruct.engine.IGameLogic;
 
public class Main {
	public static final String GAME_VERSION = "0.1 beta #1";
    public static final String GAME_NAME = "CubeStruct";
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new CubeStruct();
            GameEngine gameEng = new GameEngine(GAME_NAME, 600, 480, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}