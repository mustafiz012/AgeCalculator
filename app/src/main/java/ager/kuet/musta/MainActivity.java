package ager.kuet.musta;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Calendar calendar;
	private int currentDay, currentMonth, curretnYear;
	private TextView dateShow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		calendar = Calendar.getInstance();
		dateShow = (TextView) findViewById(R.id.show);
		curretnYear = calendar.get(Calendar.YEAR);
		currentMonth = calendar.get(Calendar.MONTH);
		currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		dateShow.setText(new StringBuilder().append(curretnYear).append(" Years ").append(currentMonth).append(" Months ").append(currentDay).append(" Days"));
		findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setDate();
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void setDate(){
		showDialog(999);
	}


	@SuppressWarnings("deprecation")
	@Override
	protected Dialog onCreateDialog(int id) {
		if (id==999){
			return new DatePickerDialog(this, onDateSetListener, curretnYear, currentMonth, currentDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
			//i = year;
			//i1 = month;
			//i2 = day;
			showDate(i, i1, i2);
		}
	};

	void ageCalculation(int year, int month, int day){
		int calY = Calendar.YEAR;
		int calM = Calendar.MONTH;
		int calD = Calendar.DAY_OF_MONTH;
		if(day>calD){
			calD +=30;
			calM -= 1;
			day = calD-day;
		}else{
			day = calD - day;
		}
		if(month>calM){
			calM += 12;
			calY -= 1;
			month = calM - month;
		}else{
			month = calM - month;
		}
		year = calY - year;
	}
	void showDate(int year, int month, int day) {
		//ageCalculation(year, month, day);
		int calY = calendar.get(Calendar.YEAR);
		int calM = calendar.get(Calendar.MONTH);
		int calD = calendar.get(Calendar.DAY_OF_MONTH);
		if (day > calD) {
			calD += 30;
			month += 1;
			day = calD - day;
		} else {
			day = calD - day;
			Log.d("B day:", calD + " " + day);
		}
		if (month > calM) {
			calM += 12;
			calY -= 1;
			month = calM - month;
		} else {
			month = calM - month;
		}
		year = calY - year;
		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
		alertDialog.setTitle("Your Age");
		alertDialog.setMessage(new StringBuilder().append(year).append(" Years ").append(month).append(" Months ").append(day).append(" Days"));
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		alertDialog.show();
	}
}
