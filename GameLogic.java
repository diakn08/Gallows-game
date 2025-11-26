package com.example.dianagame;

public class GameLogic {
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
            // Если угадали слово, показываем его полностью
            for (int i = 0; i < secretWord.length(); i++) {
                guessedWord.setCharAt(i, secretWord.charAt(i));
            }
            return true;
        } else {
            // Если не угадали слово, уменьшаем попытки до 0
            attemptsLeft = 0;
            return false;
        }
    }
}

