package csci498.csmyth.encouragework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "encouragework.db";
	private static final int SCHEMA_VERSION = 1;
	
	public RestaurantHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE assignments (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, due_date DATE, notes TEXT, complete BOOLEAN);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// no-op, since will note be called until 2nd schema version exists
	}
}
