package com.example.dianagame;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private Label wordLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private TextField inputField;

    @FXML
    private Canvas hangmanCanvas;

    private HelloApplication.GameLogic gameLogic;
    private HangmanDrawer hangmanDrawer;

    private final List<String> wordPool = Arrays.asList("КНИГА", "КОТ", "СЛОН", "ТИР", "ЖУРАВЛЬ", "ДОМ", "ЛЮБОВЬ");

    @FXML
    public void initialize() {
        hangmanDrawer = new HangmanDrawer(hangmanCanvas);
        startNewGame();

        // Обработчик клавиши Enter
        inputField.setOnAction(event -> onGuess());
    }

    @FXML
    private void onGuess() {
        String input = inputField.getText().toUpperCase().trim();
        inputField.clear();

        // Проверка на ввод одной буквы
        if (input.isEmpty() || input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            // Если введено больше одной буквы или не буква, ничего не делаем
            return;
        }

        // Обработка одиночной буквы
        String result = gameLogic.processGuess(input.charAt(0));

        if (result.equals("correct")) {
            wordLabel.setText(gameLogic.getGuessedWord());
            wordLabel.setTextFill(Color.GREEN); // Успешный ввод
        } else {
            attemptsLabel.setText("Осталось попыток: " + gameLogic.getAttemptsLeft());
            attemptsLabel.setTextFill(Color.RED); // Неправильный ввод
            hangmanDrawer.drawHangman(gameLogic.getAttemptsLeft());
        }

        if (gameLogic.isGameOver()) {
            if (gameLogic.isWordGuessed()) {
                attemptsLabel.setText("Поздравляем! Вы угадали слово!");
            } else {
                attemptsLabel.setText("Вы проиграли. Загаданное слово: " + gameLogic.getSecretWord());
            }

            inputField.setDisable(true); // Блокируем ввод

            // Задержка перед началом новой игры
            startNewGameAfterDelay();
        }
    }

    private void updateUI() {
        wordLabel.setText(gameLogic.getGuessedWord());
        attemptsLabel.setText("Осталось попыток: " + gameLogic.getAttemptsLeft());
        hangmanDrawer.drawHangman(gameLogic.getAttemptsLeft());
    }

    private void startNewGame() {
        String newWord = getRandomWord();
        gameLogic = new HelloApplication.GameLogic(newWord);
        inputField.setDisable(false);
        updateUI();
    }

    private void startNewGameAfterDelay() {
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Задержка 2 секунды
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            javafx.application.Platform.runLater(this::startNewGame);
        }).start();
    }

    private String getRandomWord() {
        Random random = new Random();
        return wordPool.get(random.nextInt(wordPool.size()));
    }
}

