package avater.appscomm.com.mywanandroids;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private SportDataView sport_view;
    private Button day;
    private Button day_cal;
    private Button day_dis;
    private Button day_active;

    private Button week;
    private Button week_cal;
    private Button week_dis;
    private Button week_act;
    private Button week_sleep;

    private Button month;
    private Button month_cal;
    private Button month_dis;
    private Button month_act;
    private Button month_sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sport_view = findViewById(R.id.sport_view);
        sport_view.setTittle(SportDataView.TYPE_DAY, SportDataView.UNIT_STEP);
        day = findViewById(R.id.day);
        day_cal = findViewById(R.id.day_cal);
        day_active = findViewById(R.id.day_active);
        day_dis = findViewById(R.id.day_dis);
        week = findViewById(R.id.week);
        week_cal = findViewById(R.id.week_cal);
        week_dis = findViewById(R.id.week_dis);
        week_act = findViewById(R.id.week_act);
        week_sleep = findViewById(R.id.week_sleep);
        month = findViewById(R.id.month);
        month_cal = findViewById(R.id.month_cal);
        month_dis = findViewById(R.id.month_dis);
        month_act = findViewById(R.id.month_act);
        month_sleep = findViewById(R.id.month_sleep);
        day.setOnClickListener(this);
        day_cal.setOnClickListener(this);
        day_active.setOnClickListener(this);
        day_dis.setOnClickListener(this);
        week.setOnClickListener(this);
        week_cal.setOnClickListener(this);
        week_dis.setOnClickListener(this);
        week_act.setOnClickListener(this);
        week_sleep.setOnClickListener(this);
        month.setOnClickListener(this);
        month_cal.setOnClickListener(this);
        month_dis.setOnClickListener(this);
        month_act.setOnClickListener(this);
        month_sleep.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.day:
                sport_view.setMax(10000);
                sport_view.setProgress(5000);
                sport_view.setTittle(SportDataView.TYPE_DAY, SportDataView.UNIT_STEP);
                break;
            case R.id.day_cal:
                sport_view.setMax(1200);
                sport_view.setProgress(500);
                sport_view.setTittle(SportDataView.TYPE_DAY, SportDataView.UNIT_CAL);
                break;
            case R.id.day_dis:
                sport_view.setMax(5);
                sport_view.setProgress(3);
                sport_view.setTittle(SportDataView.TYPE_DAY, SportDataView.UNIT_DIS_KM);
                break;
            case R.id.day_active:
                sport_view.setMax(60);
                sport_view.setProgress(0);
                sport_view.setTittle(SportDataView.TYPE_DAY, SportDataView.UNIT_ACTIVE);
                break;
            case R.id.week:
                sport_view.setProgress(10000);
                sport_view.setTittle(SportDataView.TYPE_WEEK, SportDataView.UNIT_STEP, 555);
                break;
            case R.id.week_cal:
                sport_view.setProgress(1200);
                sport_view.setTittle(SportDataView.TYPE_WEEK, SportDataView.UNIT_CAL, 666);
                break;
            case R.id.week_dis:
                sport_view.setProgress(5);
                sport_view.setTittle(SportDataView.TYPE_WEEK, SportDataView.UNIT_DIS_KM, 1200);
                break;
            case R.id.week_act:
                sport_view.setProgress(60);
                sport_view.setTittle(SportDataView.TYPE_WEEK, SportDataView.UNIT_ACTIVE, 1300);
                break;
            case R.id.week_sleep:
                sport_view.setProgress(8);
                sport_view.setTittle(SportDataView.TYPE_WEEK, SportDataView.UNIT_SLEEP, 14000);
                break;
            case R.id.month:
                sport_view.setProgress(10000);
                sport_view.setTittle(SportDataView.TYPE_MONTH, SportDataView.UNIT_STEP);
                break;
            case R.id.month_cal:
                sport_view.setProgress(1200);
                sport_view.setTittle(SportDataView.TYPE_MONTH, SportDataView.UNIT_CAL);
                break;
            case R.id.month_dis:
                sport_view.setProgress(5);
                sport_view.setTittle(SportDataView.TYPE_MONTH, SportDataView.UNIT_DIS_KM);
                break;
            case R.id.month_act:
                sport_view.setProgress(60);
                sport_view.setTittle(SportDataView.TYPE_MONTH, SportDataView.UNIT_ACTIVE);
                break;
            case R.id.month_sleep:
                sport_view.setProgress(8);
                sport_view.setTittle(SportDataView.TYPE_MONTH, SportDataView.UNIT_SLEEP);
                break;
        }
    }
}
