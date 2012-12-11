package csci498.csmyth.encouragework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AssignmentHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "encouragework.db";
	private static final int SCHEMA_VERSION = 1; 
	
	public AssignmentHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE assignments (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year INTEGER, month INTEGER, day_of_month INTEGER, notes TEXT, complete INTEGER);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// no-op, only one version of the database currently exists
	}
	
	public Cursor getAll(String orderBy) {
		return getReadableDatabase().rawQuery("SELECT _id, name, year, month, day_of_month, notes, complete FROM assignments ORDER BY " + orderBy, null);
	}
	
	public Cursor getById(String id) {
		String[] args = {id};
		return getReadableDatabase().rawQuery("SELECT _id, name, year, month, day_of_month, notes, complete FROM assignments WHERE _ID=?", args);
	}
	
	public void insert(String name, Integer year, Integer month, Integer day_of_month, String notes, Integer complete) {
		ContentValues cv = new ContentValues();
		
		cv.put("name", name);
		cv.put("year", year);
		cv.put("month", month);
		cv.put("day_of_month", day_of_month);
		cv.put("notes", notes);
		cv.put("complete", complete);
		
		getWritableDatabase().insert("assignments", "name", cv);
	}
	
	public void update(String id, String name, Integer year, Integer month, Integer day_of_month, String notes, Integer complete) {
		ContentValues cv = new ContentValues();
		String[] args = {id};
		
		cv.put("name", name);
		cv.put("year", year);
		cv.put("month", month);
		cv.put("day_of_month", day_of_month);
		cv.put("notes", notes);
		cv.put("complete", complete);
		
		getWritableDatabase().update("assignments", cv, "_ID=?", args);
	}
	
	public void updateComplete(String id, boolean complete) {
		ContentValues cv = new ContentValues();
		String[] args = {id};
		
		if (complete) cv.put("complete", 1);
		else cv.put("complete", 0);
		
		getWritableDatabase().update("assignments", cv, "_ID=?", args);
	}
	
	// Note! Column ints in below getters refer to (1) name, (2) year, (3) month, (4) day_of_month, (5) notes, and (6) complete
	public String getName(Cursor c) {
		return c.getString(1);
	}
	
	public int getYear(Cursor c) {
		return c.getInt(2);
	}
	
	public int getMonth(Cursor c) {
		return c.getInt(3);
	}
	
	public int getDayOfMonth(Cursor c) {
		return c.getInt(4);
	}
	
	public String getNotes(Cursor c) {
		return c.getString(5);
	}
	
	public boolean getComplete(Cursor c) {
		int temp = c.getInt(6);
		if (temp == 1) return true;
		else return false;
	}
}
