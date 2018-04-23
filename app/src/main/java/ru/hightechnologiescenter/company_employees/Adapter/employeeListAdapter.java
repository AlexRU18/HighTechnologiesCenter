package ru.hightechnologiescenter.company_employees.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.hightechnologiescenter.company_employees.Model.Employee;
import ru.hightechnologiescenter.company_employees.R;

public class employeeListAdapter extends ArrayAdapter<Employee> {

    private ArrayList<Employee> employeeList;

    public employeeListAdapter(Context mContext, int layoutResourceId, ArrayList<Employee> employeeList) {
        super(mContext, layoutResourceId, employeeList);
        this.employeeList = employeeList;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            view = inflater.inflate(R.layout.employees_list_item, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Employee employee = employeeList.get(position);

        holder.emplName = view.findViewById(R.id.empl_name);
        holder.emplPhone = view.findViewById(R.id.empl_phone_number);
        holder.emplSkills = view.findViewById(R.id.empl_skills);
        holder.avatarImg = view.findViewById(R.id.empl_avatar);

        if (holder.emplName != null && null != employee.getName()
                && employee.getName().trim().length() > 0) {
            holder.emplName.setText(employee.getName());
        }
        if (holder.emplPhone != null && null != employee.getPhone_number()) {
            holder.emplPhone.setText(employee.getPhone_number());
        }
        StringBuilder employeeSkillsItem = new StringBuilder();
        for (int i = 0; i < employee.getSkills().size(); i++) {
            employeeSkillsItem.append(employee.getSkills().get(i));
            if (i != employee.getSkills().size() - 1)
                employeeSkillsItem.append(", ");
        }
        if (holder.emplSkills != null && null != employee.getSkills()) {
            holder.emplSkills.setText(employeeSkillsItem);
        }
        if (holder.avatarImg != null) {
            Picasso.get()
                    .load(R.mipmap.ic_avatar)
                    .resize(250, 250)
                    .centerCrop()
                    .into(holder.avatarImg);
        } else {
            holder.avatarImg.setImageResource(R.mipmap.ic_launcher);
        }
        return view;
    }

    private class ViewHolder {
        private TextView emplName, emplPhone, emplSkills;
        private ImageView avatarImg;
    }
}