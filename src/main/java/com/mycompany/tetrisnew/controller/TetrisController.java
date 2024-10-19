package com.mycompany.tetrisnew.controller;

import com.mycompany.tetrisnew.model.TetrisGame;
import com.mycompany.tetrisnew.view.TetrisView;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.LineReader;
import org.jline.reader.Parser;

import java.io.IOException;

public class TetrisController extends Thread {
    private final TetrisView view;
    private final TetrisGame game;
    private Terminal terminal;

    public TetrisController(TetrisView view, TetrisGame game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void run() {
        try {
            terminal = TerminalBuilder.builder().system(true).build();
            game.startGame();
            new InputThread().start();
            while (true) {
                game.update();
                view.updateView();
                Thread.sleep(350);
                if (game.isGameOver()) {
                    view.showGameOverDialog();
                }
            }
        } catch (IOException | InterruptedException e) {
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private class InputThread extends Thread {
        @Override
        public void run() {
            Parser parser = LineReaderBuilder.builder().terminal(terminal).build().getParser();
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .parser(parser)
                    .build();
            while (true) {
                String key = reader.readLine();
                switch (key) {
                    case "\033[A" -> // LEFT
                        game.moveLeft();
                    case "\033[D" -> // RIGHT
                        game.moveRight();
                    case "\033[W" -> // UP
                        game.rotateClockwise();
                    case "\033[S" -> // DOWN
                        game.moveAbsDown();
                    case " " -> // SPACE
                        game.rotateCounterClockwise();
                    default -> {
                    }
                }
                view.updateView();
            }
        }
    }
}
