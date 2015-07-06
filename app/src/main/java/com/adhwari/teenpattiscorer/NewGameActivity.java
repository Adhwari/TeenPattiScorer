package com.adhwari.teenpattiscorer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NewGameActivity extends ActionBarActivity {

    private List<EditText> nameList = new ArrayList<EditText>();
    public List<PlayerInformation> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        showAddedPlayers();
    }

    @Override
    protected void onResume() {
        if(playersList.size() > 0)
        {
            Button startNewGameButton = (Button)findViewById(R.id.startGame);
            if(startNewGameButton != null)
                startNewGameButton.setText("Continue");

        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        PlayerInfoList.clearPlayerData();
        nameList.clear();
        super.onBackPressed();
    }

    private void showAddedPlayers() {
        TableLayout tl = (TableLayout) findViewById(R.id.addPlayerTable);
        playersList = PlayerInfoList.getPlayerInfoList();
        if(playersList.size() == 0)
            return;
        for(PlayerInformation thisPlayer: playersList){
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            EditText thisName = new EditText(this);
            thisName.setText(thisPlayer.getPlayerName());
            tr.addView(thisName);

    /* Add row to TableLayout. */
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
        return true;
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

    public void onClickStartNewGame (View view) {

        Intent intent = new Intent(this, StartGameActivity.class);
        playersList = PlayerInfoList.getPlayerInfoList();
        int playersListCount = playersList.size();
        int namesCount = nameList.size();
        if(namesCount > 0 && playersListCount < namesCount) {
            EditText lastNameBox = nameList.get(namesCount - 1);
            Editable lastName = lastNameBox.getText();
            if (lastName != null && !(lastName.toString().trim()).equals("")) {
                PlayerInformation thisPlayer = new PlayerInformation(lastName.toString(), 0);

                playersList.add(thisPlayer);
                lastNameBox.setEnabled(false);
            }
        }
        startActivity(intent);

    }

    private void handleLastPlayerName()
    {
        int namesCount = nameList.size();
        if(namesCount > 0) {
            EditText lastNameBox = nameList.get(namesCount - 1);
            Editable lastName = lastNameBox.getText();
            if (lastName == null || (lastName.toString().trim()).equals("")){
                Toast.makeText(getApplicationContext(),
                        "Enter the name first", Toast.LENGTH_LONG).show();
                return;
            }
            else{

                PlayerInformation thisPlayer = new PlayerInformation(lastName.toString(), 0);
                playersList = PlayerInfoList.getPlayerInfoList();
                if(namesCount == playersList.size() && namesCount > 0)
                    return;
                playersList.add(thisPlayer);
                lastNameBox.setEnabled(false);
            }
        }
    }


    public void onClickAddPlayer(View view) {
        handleLastPlayerName();
        playersList = PlayerInfoList.getPlayerInfoList();
        int playersListCount = playersList.size();
        int namesCount = nameList.size();
//        if((playersListCount == namesCount) && (playersListCount > 0))
//            return;

        createTextToAddPlayer();
    }

    private void createTextToAddPlayer() {
        TableLayout tableLayoutPlayerList = (TableLayout) findViewById(R.id.addPlayerTable);
        /* Create a new row to be added. */
        TableRow tableRowOnePlayer = new TableRow(this);
        tableRowOnePlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    /* Create a Button to be the row-content. */
        EditText playerName = new EditText(this);

        playerName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        playerName.setLines(1);
        playerName.setSelected(true);
    /* Add Button to row. */
        tableRowOnePlayer.addView(playerName);
        nameList.add(playerName);
    /* Add row to TableLayout. */
        tableLayoutPlayerList.addView(tableRowOnePlayer,
                new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }
}
