package com.xu.fastindexing.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xu.fastindexing.R;
import com.xu.fastindexing.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     许鹏飞
 * 创建时间   2017/3/21 0:29
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class ListViewAdapter extends BaseAdapter {
    private List<Person> persons = new ArrayList<>();

    public ListViewAdapter(List<Person> persons) {
        this.persons=persons;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        if(convertView!=null){
            view=convertView;
        }else{
            view = View.inflate(parent.getContext(), R.layout.item_listview,null);
        }
        ViewHolder viewHolder=ViewHolder.getHolder(view);
        Person person = persons.get(position);

        String letter =null;
        if(position==0){
            letter = person.getLetter();//首字母
        }else{
            String letter1 = persons.get(position - 1).getLetter();
            if(!TextUtils.equals(letter1,person.getLetter())){

                letter = person.getLetter();
            }

        }
        viewHolder.index.setText(person.getLetter());
        viewHolder.index.setVisibility(letter==null?View.GONE:View.VISIBLE);
        viewHolder.name.setText(person.getName());
        return view;
    }
    static class ViewHolder{
        TextView name;
        TextView index;

        public static ViewHolder getHolder(View view) {
            ViewHolder tag = (ViewHolder) view.getTag();
            if(tag==null){
                tag = new ViewHolder();
                tag.index= (TextView) view.findViewById(R.id.tv_index);
                tag.name= (TextView) view.findViewById(R.id.tv_name);
                view.setTag(tag);
            }

            return tag;
        }
    }
}
