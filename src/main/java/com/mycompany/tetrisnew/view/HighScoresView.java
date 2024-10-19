package com.mycompany.tetrisnew.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.mycompany.tetrisnew.model.HighScores;

public class HighScoresView extends JPanel {
    private final HighScores highScores;
    private DefaultTableModel tableModel;

    public HighScoresView(HighScores highScores) {
        this.highScores = highScores;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("High Scores", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(createScoresData(), new String[]{"Rank", "Player", "Score"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        JTable scoresTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] createScoresData() {
        List<HighScores.ScoreEntry> scores = highScores.getScores();
        Object[][] data = new Object[scores.size()][3];
        for (int i = 0; i < scores.size(); i++) {
            HighScores.ScoreEntry entry = scores.get(i);
            data[i][0] = i + 1;
            data[i][1] = entry.getPlayerName();
            data[i][2] = entry.getScore();
        }
        return data;
    }

    public void updateScores() {
        // Remove all rows
        tableModel.setRowCount(0);

        // Add updated rows
        List<HighScores.ScoreEntry> scores = highScores.getScores();
        for (int i = 0; i < scores.size(); i++) {
            HighScores.ScoreEntry entry = scores.get(i);
            tableModel.addRow(new Object[]{i + 1, entry.getPlayerName(), entry.getScore()});
        }
    }
}
