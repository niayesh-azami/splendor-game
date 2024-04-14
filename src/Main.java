// in the name of god

import GameState.gameState;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // if new game starts
        gameState parkGameState = new gameState();

        String player1name, player2name;
        Scanner input = new Scanner(System.in);
        player1name = input.nextLine();
        player2name = input.nextLine();

        parkGameState.startTheGame(player1name, player2name);

    }
}