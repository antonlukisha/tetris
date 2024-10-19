package com.mycompany.tetrisnew.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {
    private static final int MAX_SCORES = 10;
    private final List<ScoreEntry> scores;

    public HighScores() {
        scores = new ArrayList<>();
    }

    public void addScore(String playerName, int score) {
        scores.add(new ScoreEntry(playerName, score));
        Collections.sort(scores);
        if (scores.size() > MAX_SCORES) {
            scores.remove(MAX_SCORES);
        }
    }

    public List<ScoreEntry> getScores() {
        return scores;
    }
    
    public static class ScoreEntry implements Comparable<ScoreEntry> {
        private final String playerName;
        private final int score;

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(ScoreEntry other) {
            return Integer.compare(other.score, this.score);
        }
    }
}
