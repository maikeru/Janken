package maikeru.janken;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Janken extends Activity implements OnClickListener {
	private String opponentWeapon = "nothing";
	private String result = "DRAW";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button rockButton = (Button)findViewById(R.id.rock);
        rockButton.setOnClickListener(this);
        Button paperButton = (Button)findViewById(R.id.paper);
        paperButton.setOnClickListener(this);
        Button scissorsButton = (Button)findViewById(R.id.scissors);
        scissorsButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        // do something when the button is clicked
    	TextView label = (TextView)findViewById(R.id.label);
    	Button button = (Button)v;
    	String weapon = "thin air";
    	switch (button.getId())
    	{
    		case R.id.rock:
    			weapon = "ROCK";
    			break;
    		
    		case R.id.paper:
    			weapon = "PAPER";
    			break;
    		
    		case R.id.scissors:
    			weapon = "SCISSORS";
    			break;
    	}
    	label.setText("You selected " + weapon + "!");
    	fight(weapon);
    	//setContentView(R.layout.result);
    	showDialog(1);
    }
    
    protected Dialog onCreateDialog(int id) {
    	// Only have one type of dialog right now so ignore id
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(result + "\nOpponent used " + this.opponentWeapon)
    	       .setCancelable(false)
    	       .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                Janken.this.reset();
    	           }
    	       })
    	       .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
    	    	   public void onClick(DialogInterface dialog, int which) {
    	    		    Janken.this.finish();
    	    	   }					
    	       });
    	
    	AlertDialog alert = builder.create();
    	return alert;
    }
    
    public void onPrepareDialog(int id, Dialog dialog){
    	AlertDialog alert = (AlertDialog)dialog;
    	alert.setMessage(result + "\nOpponent used " + opponentWeapon);
    }
    
    private void reset() {
    	opponentWeapon = "NOTHING";
    	result = "DRAW";
    	TextView label = (TextView)findViewById(R.id.label);
    	label.setText("Choose your weapon!");
    }
    
    private void fight(String userWeapon) {
    	this.opponentWeapon = generateOpponentWeapon();
    	//opponentWeapon.setText("Your opponent used " + opponentWeapon + "!");
    	if (userWeapon == opponentWeapon) {
    		this.result = "DRAW";
    	}
    	else if (userWeapon == "ROCK") {
    		this.result = opponentWeapon == "SCISSORS" ? "WIN" : "LOSE";
    	}
    	else if (userWeapon == "PAPER") {
    		this.result = opponentWeapon == "ROCK" ? "WIN" : "LOSE";
    	}
    	else if (userWeapon == "SCISSORS") {
    		this.result = opponentWeapon == "PAPER" ? "WIN" : "LOSE";
    	}
    }

	private String generateOpponentWeapon() {
		String weapon = "NOTHING";
		Random rnd = new Random();
		int weaponIndex = rnd.nextInt(3);
		switch (weaponIndex) {
			case 0:
				weapon = "ROCK";
				break;
			case 1:
				weapon = "PAPER";
				break;
			case 2:
				weapon = "SCISSORS";
				break;				
			default:
				weapon = "NOTHING";					
		}	
		
		return weapon;
	}
}