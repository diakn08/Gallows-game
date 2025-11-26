package com.example.dianagame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class  HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Виселица");
        stage.setScene(scene);
        stage.setResizable(false);  // Отключить изменение размера окна
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class GameLogic {
        private final String secretWord;
        private final StringBuilder guessedWord;
        private int attemptsLeft;

        public GameLogic(String word) {
            this.secretWord = word.toUpperCase();
            this.guessedWord = new StringBuilder("_".repeat(secretWord.length()));
            this.attemptsLeft = 6;
        }

        public String getGuessedWord() {
            return guessedWord.toString();
        }

        public String getSecretWord() {
            return secretWord;
        }

        public int getAttemptsLeft() {
            return attemptsLeft;
        }

        public boolean isGameOver() {
            return attemptsLeft <= 0 || guessedWord.toString().equals(secretWord);
        }

        public boolean isWordGuessed() {
            return guessedWord.toString().equals(secretWord);
        }

        public String processGuess(char guessedChar) {
            if (secretWord.contains(String.valueOf(guessedChar))) {
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guessedChar) {
                        guessedWord.setCharAt(i, guessedChar);
                    }
                }
                return "correct";
            } else {
                attemptsLeft--;
                return "incorrect";
            }
        }

        public boolean guessWholeWord(String word) {
            if (word.equals(secretWord)) {
                for (int i = 0; i < secretWord.length(); i++) {
                    guessedWord.setCharAt(i, secretWord.charAt(i));
                }
                return true;
            } else {
                attemptsLeft = 0;
                return false;
            }
        }
    }
}
