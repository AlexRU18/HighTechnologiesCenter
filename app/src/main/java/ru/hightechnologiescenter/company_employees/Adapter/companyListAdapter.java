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

import ru.hightechnologiescenter.company_employees.Model.Company;
import ru.hightechnologiescenter.company_employees.R;

public class companyListAdapter extends ArrayAdapter<Company> {

    private List<Company> company;

    public companyListAdapter(Context mContext, int layoutResourceId, List<Company> company) {
        super(mContext, layoutResourceId, company);
        this.company = company;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            view = inflater.inflate(position, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if ((company == null) || ((position + 1) > company.size()))
            return view;

        Company employee = company.get(position);

        holder.emplName = view.findViewById(R.id.empl_name);
        holder.avatarImg = view.findViewById(R.id.empl_avatar);

        if (holder.emplName != null && null != employee.getName()
                && employee.getName().trim().length() > 0) {
            holder.emplName.setText(Html.fromHtml(employee.getName()));
        }
        if (holder.avatarImg != null) {
                Picasso.get()
                        .load(R.mipmap.ic_launcher)
                        .resize(250, 190)
                        .centerCrop()
                        .into(holder.avatarImg);
            }else {
            holder.avatarImg.setImageResource(R.mipmap.ic_launcher);
        }
        return view;
    }

    private class ViewHolder {
        private TextView emplName, emplPhone, emplSkills;
        private ImageView avatarImg;
    }
}