package com.xuzj.personmanage;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by xuzj on 2016/3/24.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private int resourceId;
    public PersonAdapter(Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	Person person = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.age = (TextView) view.findViewById(R.id.age);
            viewHolder.sex = (TextView) view.findViewById(R.id.sex);
            view.setTag(viewHolder); //  将ViewHolder 存储在View
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); //  重新获取ViewHolder
        }
        viewHolder.name.setText(person.getName());
        viewHolder.age.setText(person.getAge()+"");
        viewHolder.sex.setText(person.getSex());
        return view;
    }

    class ViewHolder {
    	TextView name;
    	TextView age;
    	TextView sex;
    	}


}
