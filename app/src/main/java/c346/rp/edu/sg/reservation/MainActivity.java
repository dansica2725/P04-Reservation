package c346.rp.edu.sg.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button reserveBtn, resetBtn;

    EditText nameEt, mobileEt, paxEt;

    CheckBox smokeCB;

    DatePicker dp;

    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reserveBtn = findViewById(R.id.reserveBtn);
        resetBtn  =findViewById(R.id.resetBtn);

        nameEt = findViewById(R.id.nameEt);
        mobileEt = findViewById(R.id.mobileEt);
        paxEt = findViewById(R.id.paxEt);

        smokeCB = findViewById(R.id.smokeCB);

        dp = findViewById(R.id.dp);

        tp = findViewById(R.id.tp);

        dp.updateDate(2019, 5, 1);

        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        smokeCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (smokeCB.isChecked()) {
                    smokeCB.setText("Smoking Area");
                }
                else {
                    smokeCB.setText("Non-Smoking Area");
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEt.setText(null);
                mobileEt.setText(null);
                paxEt.setText(null);

                smokeCB.setChecked(false);

                dp.updateDate(2019, 5, 1);

                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkEtInput() == true) {
                    String name = nameEt.getText().toString().trim();
                    String mobile = mobileEt.getText().toString().trim();
                    String pax = paxEt.getText().toString().trim();

                    int day = dp.getDayOfMonth();
                    int mth = dp.getMonth();
                    int year = dp.getYear();

                    int hour = tp.getCurrentHour();
                    int min = tp.getCurrentMinute();

                    String date = day + "/" + mth +"/" + year;
                    String time = hour + ":" + min;


                    if (!smokeCB.isChecked()) {
                        String smokeArea = "No";
                        addToast("Customer Info: \nName: " + name + "\n Mobile: " + mobile + "\n Pax: " + pax + "\n Smoking Area: " + smokeArea + "\n Date: " + date + "\n Time: " + time);
                    }
                    else {
                        String smokeArea = "Yes";
                        addToast("Customer Info: \n Name: " + name + "\n Mobile: " + mobile + "\n Pax: " + pax + "\n Smoking Area: " + smokeArea + "\n Date: " + date + "\n Time: " + time);
                    }

                }
                else {
                    addToast("Inputs are empty!");
                }
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay >= 21) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(0);
                }
                else if (hourOfDay < 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                }
            }
        });

    }
    public boolean checkEtInput() {

        if ((nameEt.getText().length() > 0) && (mobileEt.getText().length() > 0) && (paxEt.getText().length() > 0)) {
            return true;
        }
        return false;
    }

    public void addToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
