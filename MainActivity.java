package kr.hs.emirim.gjy00727.mirimcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Project_DB.db", null, 1);
        Button mShowDialog = (Button) findViewById(R.id.btn);

        final Intent intent = new Intent(this, ProjectList.class);


        mShowDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.project_insert, null);
                final EditText mProjectName = mView.findViewById(R.id.etProjectName);

                final DatePicker mdpStart = (DatePicker) mView.findViewById(R.id.dpstart);
                final DatePicker mdpDead = (DatePicker) mView.findViewById(R.id.dpdead);

                final Button mInsert = mView.findViewById(R.id.btn_insert);

                mInsert .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(!mProjectName.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,
                                    "Insert successful",
                                    Toast.LENGTH_SHORT).show();
                            //DBHelper
                            String start = getDateFromDatePicker(mdpStart);
                            String dead = getDateFromDatePicker(mdpDead);

                            // TextView tv = (TextView) findViewById(R.id.tv);
                            // tv.setText(tv.getText()+""+mdpStart.getYear()+"/"+(mdpStart.getMonth()+1)+"/"+mdpStart.getDayOfMonth());
                        //    dbHelper.insert(mProjectName.getText().toString(), start, dead);
                            String [][] result = dbHelper.getResult();
                            String [] final_result = new String[(result.length)*(result[0].length)];
//                            Log.v("length : ", String.valueOf(result.length));
//                            Log.v("length : ", String.valueOf(result[0].length));
//                            for(int i=0; i<result.length; i++){
//                                for(int j=0; j<result[0].length; j++) {
//                                    Log.v("결과 ["+i+"]["+j+"] : ", result[i][j]);
//                                }
//                            }
                            TextView tv = (TextView)mView.findViewById(R.id.tv);
                            int index=0;
                            for(int i=0; i<result.length; i++){
                                for(int j=0; j<result[0].length; j++){
                                    final_result[index++] = result[i][j];

                                }
                            }

//                            for(int i=0; i<final_result.length; i++){
//                                Log.v("결과 ["+i+"] : ", final_result[i]);
//                            }

//                            dbHelper.getReadableDatabase();
//                            dbHelper.close();
                            intent.putExtra("결과", final_result);
                            startActivity(intent);
                            // DB에 데이터 추가
                        }else{
                            Toast.makeText(MainActivity.this,
                                    "Please fill any empty fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

        private static String getDateFromDatePicker(DatePicker mdp) {
        int year = mdp.getYear();
        int month = mdp.getMonth();
        int day = mdp.getDayOfMonth();
        String date = year + "/" + month +"/" + day;
            Log.v("getDateFrom : ",date);
        return date;
    }
}
