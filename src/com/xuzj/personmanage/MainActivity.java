package com.xuzj.personmanage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.xuzj.personmanage.data.MySQLiteOpenHelper;

public class MainActivity extends Activity {
	private MySQLiteOpenHelper dbHelper;
	Button add;
	SQLiteDatabase db;

	ListView listView;
	PersonAdapter adapter;
	List<Person> list =new ArrayList<Person>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建数据库
		dbHelper = new MySQLiteOpenHelper(this, "Person.do", null, 1);
		db = dbHelper.getWritableDatabase();
		
		
		//查询数据库
		Cursor cursor = db.query("Person", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Person person = new Person();
				// 遍历Cursor 对象，取出数据并打印
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				int age = cursor.getInt(cursor.getColumnIndex("age"));
				String summary =cursor.getString(cursor.getColumnIndex("summary"));
				person.setId(id);
				person.setName(name);
				person.setAge(age);
				person.setSex(sex);
				person.setSummary(summary);
				list.add(person);
			} while (cursor.moveToNext());
		}
		cursor.close();

		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new PersonAdapter(MainActivity.this,R.layout.item,list);
		listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("ssssss");
				Person person = list.get(position);
				db = dbHelper.getWritableDatabase();
				db.execSQL("delete from Person where id = ?", new String[] { person.getId()+"" });
				list.remove(person);
				adapter.notifyDataSetChanged();  
			}
		});

	}

}
