package com.cookandroid.rockscissorspaper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView playerNameTextView;
    private TextView playerChoiceTextView;
    private TextView computerChoiceTextView;
    private TextView scoreTextView;
    private TextView resultTextView;

    private int playerScore = 0;
    private int computerScore = 0;

    private ImageView playerChoiceImageView;
    private ImageView computerChoiceImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoiceImageView = findViewById(R.id.playerChoiceImageView);
        playerChoiceTextView = findViewById(R.id.playerChoiceTextView);
        computerChoiceImageView = findViewById(R.id.computerChoiceImageView);
        computerChoiceTextView = findViewById(R.id.computerChoiceTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);

        updateScore();

        Button rockButton = findViewById(R.id.rockButton);
        Button paperButton = findViewById(R.id.paperButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("Rock");
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("Paper");
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("Scissors");
            }
        });
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        String computerChoice = choices[random.nextInt(choices.length)];

        setChoice(playerChoiceImageView, playerChoice, playerChoiceTextView);
        setChoice(computerChoiceImageView, computerChoice, computerChoiceTextView);

        if (playerChoice.equals(computerChoice)) {
            resultTextView.setText("It's a tie!");
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerScore += 3;
            resultTextView.setText("You win!");
        } else {
            computerScore += 3;
            resultTextView.setText("Computer wins!");
        }

        updateScore();
    }

    private void setChoice(ImageView imageView, String choice, TextView textView) {
        switch (choice) {
            case "Rock":
                imageView.setImageResource(R.drawable.rock_image);
                break;
            case "Paper":
                imageView.setImageResource(R.drawable.paper_image);
                break;
            case "Scissors":
                imageView.setImageResource(R.drawable.scissors_image);
                break;
            default:
                imageView.setImageDrawable(null);
                break;
        }

        textView.setText(choice);
    }

    private void updateScore() {
        scoreTextView.setText("Score: " + (playerScore - computerScore));
    }
}
