package edu.cnm.deepdive.dicesolitaire;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.dicesolitaire.model.Roll;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private static final String PAIR_lABEL_ID_FORMAT = "pair_%d_label";
  private static final String PAIR_COUNT_ID_FORMAT = "pair_%d_count";

  private int minPairValue = 2;
  private int maxPairValue= 2 * Roll.NUM_FACES;
  private TextView[] pairLabels;
  private ProgressBar[] pairCounts;
  private Button roller;
  private TextView rollDisplay;
  private Random rng;

  private static final String SCRATCH_lABEL_ID_FORMAT = "pair_%d_label";
  private static final String SCRATCH_COUNT_ID_FORMAT = "pair_%d_count";

  private TextView[] scratchLabels;
  private ProgressBar[] scratchCounts;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pairLabels = new TextView[maxPairValue - minPairValue + 1];
    pairCounts = new ProgressBar[maxPairValue - minPairValue + 1];
    Resources res = getResources();
    rng = new Random();
    NumberFormat formatter = NumberFormat.getInstance();
    for (int i = minPairValue; i <= maxPairValue; i++) {
      String labelIdString = String.format(PAIR_lABEL_ID_FORMAT, i);
      int labelId = res.getIdentifier(labelIdString, "id", getPackageName());
      pairLabels[i - minPairValue] = findViewById(labelId);
      pairLabels[i - minPairValue].setText(formatter.format(i));
      String countIdString = String.format(PAIR_COUNT_ID_FORMAT, i);
      int countId = res.getIdentifier(countIdString, "id", getPackageName());
      pairCounts[i - minPairValue] = findViewById(countId);
      pairCounts[i - minPairValue].setProgress(1 + rng.nextInt(10));


    }
    roller = findViewById(R.id.roller);
    rollDisplay = findViewById(R.id.roll_display);
    roller.setOnClickListener(new RollerListener());

    scratchLabels = new TextView[Roll.NUM_FACES];
    scratchCounts = new ProgressBar[Roll.NUM_FACES];

    for (int i = Roll.NUM_FACES; i <= Roll.NUM_FACES; i++) {
      String labelIdString = String.format(SCRATCH_lABEL_ID_FORMAT, i);
      int labelId = res.getIdentifier(labelIdString, "id", getPackageName());
      scratchLabels[i - Roll.NUM_FACES] = findViewById(labelId);
      scratchLabels[i - Roll.NUM_FACES].setText(formatter.format(i));
      String countIdString = String.format(SCRATCH_COUNT_ID_FORMAT, i);
      int countId = res.getIdentifier(countIdString, "id", getPackageName());
      scratchCounts[i - Roll.NUM_FACES] = findViewById(countId);
      scratchCounts[i - Roll.NUM_FACES].setProgress(1 + rng.nextInt(7));}


  }
  private class RollerListener implements OnClickListener {

    @Override
    public void onClick(View v) {
      Roll roll  = new Roll(rng);
      rollDisplay.setText(Arrays.toString(roll.getDice()));
    }

  }

}

