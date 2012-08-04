package com.thenewboston.terry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";

	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;

	public class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			ourContext = context;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_HOTNESS + " TEXT NOT NULL);"

			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE + " ;");
			onCreate(db);
		}

	}

	public HotOrNot(Context c) {
		ourContext = c;
	}

	public HotOrNot open() throws SQLiteException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;

	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String name, String hotness) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getInfo() {
		String[] columns = { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result += c.getString(iRow) + " " + c.getString(iName) + " "
					+ c.getString(iHotness) + "\n";

		}

		return result;
	}

	public String getName(long l) {
		String[] columns = { KEY_NAME };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null && !c.isAfterLast()) {
			c.moveToFirst();
			return c.getString(0);
		}
		return "Data Not Found";
	}

	public String getHotness(long l) {
		String[] columns = { KEY_HOTNESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null && !c.isAfterLast()) {
			c.moveToFirst();
			return c.getString(0);
		}
		return "Data Not Found";
	}

	public void modifyEntry(long mlRow, String mName, String mHotNess) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, mName);
		cv.put(KEY_HOTNESS, mHotNess);
		ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=" + mlRow, null);
	}

	public void deleteEntry(long mlRow) {
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + mlRow, null);
	}
}
