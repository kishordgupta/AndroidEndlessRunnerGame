package com.rhymes.client.mariobros.gamehistory;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHistory {
	private static final String DATABASE_CREATE = "create table if not exists GAME_HISTORY("
			+ "_id integer, "
			+ "highscore integer not null" + ");";

	private static final String DATABASE_NAME = "PIG_DB";
	private static final String DATABASE_TABLE = "GAME_HISTORY";

//	private int lvl_num = 18;

	private SQLiteDatabase db;
	private Context ctx;

	public DBHistory(Context ctx) {
		// System.out.println("Creating Database...");
		this.ctx = ctx;
		try {
			if (db == null)
				db = openDB();
			createDB(db);
		} catch (Exception e) {
		}
	}

	public SQLiteDatabase openDB() {
		int i = 10;
		while (i-- > 0) {
			try {
				// System.out.println("Opening database");
				return ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return null;
	}

	public void createDB(SQLiteDatabase dbl) {
		try {
			dbl.execSQL(DATABASE_CREATE);
			// System.out.println("Database created");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void close() {
		try {
			if (db != null) {
				db.close();
				db = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createRow() {

		ArrayList<GameHistory> rows = fetchAllRows();
		if (rows.size() > 0)
			return;

		try {
			if (db == null) {
				db = openDB();
			}
				ContentValues vals = new ContentValues();
				vals.put("_id", 1);
				vals.put("highscore", 0);
				db.insert(DATABASE_TABLE, null, vals);
				System.out.println("Row created");


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void resetTable() {

		// int lvl=18;

		try {
			if (db == null)
				db = openDB();
				deleteRow(1);
			

			createRow();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void deleteRow(int id) {

		try {
			if (db == null)
				db = openDB();
			db.delete(DATABASE_TABLE, "_id=" + "'" + id + "'", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public ArrayList<GameHistory> fetchAllRows() {

		Cursor c = null;
		ArrayList<GameHistory> ret = new ArrayList<GameHistory>();
		try {
			if (db == null)
				db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
			c = db.query(DATABASE_TABLE, new String[] { "_id", "highscore"}, null, null, null, null, null);
			int numRows = c.getCount();
			c.moveToFirst();
			for (int i = 0; i < numRows; ++i) {
				GameHistory row = new GameHistory(c.getInt(0), c.getInt(1));
				ret.add(row);
				c.moveToNext();
			}
		} catch (Exception e) {
			Log.e("Exception on query", e.toString());
		} finally {
			this.close();
			if (c != null)
				c.close();
		}
		return ret;
	}

	public void updatelevel(GameHistory gs) {

		try {
			if (db == null)
				db = openDB();
						
//			String updateScore ="update " + DATABASE_TABLE
//			+ " set score="+gs.score+" where _id=" + 1 + ";";
//			db.execSQL(updateScore);

			String updateHigScore ="update " + DATABASE_TABLE
			+ " set highscore="+gs.highScore+" where highscore<" + gs.highScore	+ ";";
			db.execSQL(updateHigScore);

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}


}