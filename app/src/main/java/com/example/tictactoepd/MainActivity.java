package com.example.tictactoepd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount = 0;
    //score board information
    private int player1WinCount = 0;
    private int player2WinCount = 0;

    private TextView messagesViewMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messagesViewMessage = findViewById(R.id.messagesView);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + (i * 3 + j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);

                //display whose turn it is
                updateTurnMessage();

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!((Button) v).getText().toString().isEmpty()) {
                            return;
                        }

                        if (player1Turn) {
                            ((Button) v).setText("X");
                        } else {
                            ((Button) v).setText("O");
                        }

                        roundCount++;

                        if (checkForWin()) {
                            if (player1Turn) {
                                player1Wins();
                            } else {
                                player2Wins();
                            }
                            updateScores();
                        } else if (roundCount == 9) {
                            draw();
                        } else {
                            player1Turn = !player1Turn;
                            updateTurnMessage();
                        }
                    }
                });
            }
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].isEmpty()) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].isEmpty()) {
            return true;
        }

        return field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].isEmpty();
    }

    private void player1Wins() {
        player1WinCount++;

        String msg = "Player 1 wins!";
        showPopupWindow("",msg);
        resetBoard();
    }

    private void player2Wins() {
        player2WinCount++;
        String msg = "Player 2 wins!";
        showPopupWindow("",msg);
        resetBoard();
    }

    private void draw() {
        String msg = "It's a Draw!";
        showPopupWindow("",msg);
        resetBoard();
    }

    private void updateTurnMessage() {
        String msg;
        if (player1Turn) {
            msg = "Player 1's turn";
        } else {
            msg = "Player 2's turn";
        }
        messagesViewMessage.setText(msg);
    }

    private void updateScores(){
        //set score board details
        TextView playerOneScore = findViewById(R.id.player1Score);
        TextView playerTwoScore = findViewById(R.id.player2Score);
        playerOneScore.setText(String.valueOf(player1WinCount));
        playerTwoScore.setText(String.valueOf(player2WinCount));
    }

    private void showPopupWindow(String header, String message ) {
        PopUpWindow popupWindow = new PopUpWindow(this,header,message);
        popupWindow.showAtLocation(findViewById(R.id.winMessage), Gravity.CENTER, 0, 0);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
        updateTurnMessage();
    }
}
