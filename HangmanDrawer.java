package com.example.dianagame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

            public class HangmanDrawer {
                private final Canvas canvas;

                public HangmanDrawer(Canvas canvas) {
                    this.canvas = canvas;
                }

                public void drawHangman(int attemptsLeft) {
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                    if (attemptsLeft <= 5) gc.strokeLine(50, 180, 150, 180); // Основание
                    if (attemptsLeft <= 4) gc.strokeLine(100, 180, 100, 50); // Вертикальная стойка
                    if (attemptsLeft <= 3) gc.strokeLine(100, 50, 140, 50);  // Верхняя перекладина
                    if (attemptsLeft <= 2) gc.strokeLine(140, 50, 140, 70);  // Веревка
                    if (attemptsLeft <= 1) gc.strokeOval(125, 70, 30, 30);   // Голова
                    if (attemptsLeft == 0) {
                        gc.strokeLine(140, 100, 140, 140); // Тело
                        gc.strokeLine(140, 110, 130, 120); // Левая рука
                        gc.strokeLine(140, 110, 150, 120); // Правая рука
                        gc.strokeLine(140, 140, 130, 160); // Левая нога
                        gc.strokeLine(140, 140, 150, 160); // Правая нога
    }
}
            }

