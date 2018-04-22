package ru.hightechnologiescenter.company_employees.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.hightechnologiescenter.company_employees.Model.Employee;
import ru.hightechnologiescenter.company_employees.R;

public class employeeListAdapter extends ArrayAdapter<Employee> {

    private List<Employee> employeeList;

    public employeeListAdapter(Context mContext, int layoutResourceId, List<Employee> employeeList) {
        super(mContext, layoutResourceId, employeeList);
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.employees_list_item, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if ((employeeList == null) || ((position + 1) > employeeList.size()))
            return view;

        Employee employee = employeeList.get(position);

        /*holder.companyName = view.findViewById(R.id.companyNameDynamical);
        holder.companyAge = view.findViewById(R.id.companyAgeDynamical);*/
        holder.emplName = view.findViewById(R.id.empl_name);
        holder.avatarImg = view.findViewById(R.id.empl_avatar);

       /* if (holder.companyName != null && null != company.getName()
                && company.getName().trim().length() > 0) {
            holder.companyName.setText(Html.fromHtml(company.getName()));
        }*/

        if (holder.emplName != null && null != employee.getName()
                && employee.getName().trim().length() > 0) {
            holder.emplName.setText(Html.fromHtml(employee.getName()));
        }
        if (holder.avatarImg != null) {
            Picasso.get()
                    .load(R.mipmap.ic_avatar)
                    .resize(250, 250)
                    .centerCrop()
                    .into(holder.avatarImg);
        }/* else {
            holder.avatarImg.setImageResource(R.mipmap.ic_launcher);
        }*/
        return view;
    }

    private class ViewHolder {
        private TextView emplName, emplPhone, emplSkills;
        private ImageView avatarImg;
    }
}