package com.xuzj.personmanage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.xuzj.personmanage.data.MySQLiteOpenHelper;

public class AddActivity extends Activity {

	EditText name;
	EditText summary;
	RadioGroup radioGroup;
	String sex = "男";
	int age;
	Button submit;

	private MySQLiteOpenHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		dbHelper = new MySQLiteOpenHelper(this, "Person.do", null, 1);
		db = dbHelper.getWritableDatabase();

		name = (EditText) findViewById(R.id.name);
		summary = (EditText) findViewById(R.id.summary);
		radioGroup = (RadioGroup) findViewById(R.id.sex);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				// 获取变更后的选中项的ID
				int radioButtonId = group.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) AddActivity.this
						.findViewById(radioButtonId);
				// 更新文本内容，以符合选中项
				sex = rb.getText().toString();
			}
		});

		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues values = new ContentValues();
				// 开始组装第一条数据
				values.put("name", name.getText().toString());
				values.put("sex", sex);
				values.put("age", 23);
				values.put("summary", summary.getText().toString());
				db.insert("Person", null, values);
				Intent intent = new Intent(AddActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
