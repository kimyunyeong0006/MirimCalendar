package kr.hs.emirim.gjy00727.mirimcalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KJY on 2017-10-22.
 */

public class DBHelper extends SQLiteOpenHelper {
    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE PROJECT (ORDER_NUM INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, START DATE, DEAD DATE);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String name, String start, String dead) {
        Log.v("DB 입력", "DB 입력");
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가

        db.execSQL("INSERT INTO PROJECT VALUES(null, '" + name + "', " +start+ ", " + dead + ");");

        db.close();
    }

//    public void update(String item, int price) {
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
//        db.close();
//    }
//
//    public void delete(String item) {
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행 삭제
//        db.execSQL("DELETE FROM MONEYBOOK WHERE item='" + item + "';");
//        db.close();
//    }

    public String[][] getResult() {


        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        Cursor num = db.rawQuery("SELECT count(*) FROM PROJECT", null);
        int count = 0;
        if (num.moveToFirst()) {

            count = Integer.parseInt(num.getString(0));
        }


        String[][] result = new String[count][4];


        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PROJECT", null);

        if(cursor.moveToFirst()) {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < 4; j++) {
                    result[i][j] = cursor.getString(j);
                }
                if (!cursor.moveToNext())
                    break;
            }
        }


        return result;
    }
}