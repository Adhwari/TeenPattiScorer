package com.adhwari.teenpattiscorer;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;


public class StartGameActivity extends ActionBarActivity {

    public List<PlayerInformation> playersList;
    private TableLayout gameScorePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        playersList = PlayerInfoList.getPlayerInfoList();
        gameScorePanel = (TableLayout) findViewById(R.id.scoreCard);

        addHeaderToTheTableField();
    }

    private void setHeaderText(TableRow headerRow){
        headerRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        headerRow.setBackgroundColor(Color.LTGRAY);
        Iterator<PlayerInformation>iter = playersList.iterator();
        for(; iter.hasNext();){
            PlayerInformation thisPlayerInfo = iter.next();
            String playerName = thisPlayerInfo.getPlayerName() + "(" + thisPlayerInfo.getCurrentScore() + ")";
            TextView playerNameText = new TextView(this);
            playerNameText.setText(playerName);
            playerNameText.setTextIsSelectable(false);
            playerNameText.setTextColor(Color.CYAN);
            headerRow.addView(playerNameText, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    private void updateHeaderText(TableRow headerRow) {
        headerRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        headerRow.setBackgroundColor(Color.LTGRAY);
        for(int i = 0;i <  headerRow.getVirtualChildCount(); i++){
            PlayerInformation thisPlayerInfo = playersList.get(i);
            String playerName = thisPlayerInfo.getPlayerName() + "(" + thisPlayerInfo.getCurrentScore() + ")";
            TextView playerNameText = (TextView)headerRow.getVirtualChildAt(i);
            playerNameText.setText(playerName);
            playerNameText.setTextIsSelectable(false);
            playerNameText.setTextColor(Color.RED);
        }
    }
    private void addHeaderToTheTableField() {

        TableRow headerRow = new TableRow(this);
        gameScorePanel.setStretchAllColumns(true);
        gameScorePanel.setShrinkAllColumns(true);
        setHeaderText(headerRow);
        gameScorePanel.addView(headerRow,
                new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_game, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startNewGame(View view){
        addLastGameScores();
        TableRow newGame = new TableRow(this);
        TableRow headerRow = (TableRow)gameScorePanel.getChildAt(0);
        newGame.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        newGame.setBackgroundColor(Color.LTGRAY);
        for(int i = 0; i < headerRow.getVirtualChildCount(); i++){
            int width = headerRow.getVirtualChildAt(i).getWidth();
            EditText score = new EditText(this);

            score.setWidth(width);
            score.setEnabled(true);
            score.setText(Integer.toString(PlayerInfoList.getTableValue()));
            score.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            newGame.addView(score, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
        gameScorePanel.addView(newGame,
                new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        updateHeaderText(headerRow);

    }

    private void addLastGameScores() {
        int gameCount = gameScorePanel.getChildCount()-1;
        if(gameCount == 0)
            return;
        TableRow lastGameRow = (TableRow)gameScorePanel.getChildAt(gameCount);
        for(int i = 0; i < lastGameRow.getVirtualChildCount(); i++) {
            EditText scoreText = (EditText)lastGameRow.getVirtualChildAt(i);
            int score = Integer.parseInt(scoreText.getText().toString());
            PlayerInformation thisPlayer = playersList.get(i);
            thisPlayer.addCurrentScore(score);
        }
    }
}
