package ru.hightechnologiescenter.company_employees;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.hightechnologiescenter.company_employees.Adapter.employeeListAdapter;
import ru.hightechnologiescenter.company_employees.Model.Company;
import ru.hightechnologiescenter.company_employees.Model.Employee;
import ru.hightechnologiescenter.company_employees.Utils.JSONParser;
import ru.hightechnologiescenter.company_employees.Utils.Utils;

public class ListActivity extends AppCompatActivity {

    private static final String URL = "http://www.mocky.io/v2/56fa31e0110000f920a72134";
    ArrayList<Employee> employeeList = new ArrayList<>();
    ;
    ListView lv;
    String TAG = "Activity";
    private TextView companyName, companyAge, companyCompetit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        companyName = findViewById(R.id.companyNameDynamical);
        companyAge = findViewById(R.id.companyAgeDynamical);
        companyCompetit = findViewById(R.id.companyCompetencesDynamical);
        lv = findViewById(R.id.employeesList);
        if (Utils.isNetworkAvailable(this)) {
            new GetData().execute(URL);
        } else {
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class GetData extends AsyncTask<String, Void, Company> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ListActivity.this, "Загрузка", "Пожалуйста, подождите");
        }

        @Override
        protected Company doInBackground(String... params) {
            Company company = new JSONParser().getData(URL);
            return company;
        }

        protected void onPostExecute(Company result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (null == result) {
                Toast.makeText(getApplicationContext(), "No data found from web", Toast.LENGTH_LONG).show();
            } else {
                companyName.setText(result.getName());
                companyAge.setText(result.getAge());
                StringBuilder companyCompetenceItem = new StringBuilder();
                for (int i = 0; i < result.getCompetences().size(); i++) {
                    companyCompetenceItem.append(result.getCompetences().get(i));
                    if (i != result.getCompetences().size() - 1)
                        companyCompetenceItem.append(", ");
                }
                companyCompetit.setText(companyCompetenceItem.toString());

                for (int i = 1; i < result.getEmployee().size(); i++) {
                    Log.d(TAG, "EMPLOYEE NAME:" + result.getEmployee().get(i) + ", i is: " + i);
                    employeeList.add(result.getEmployee().get(i));
                }
                employeeListAdapter lvAdapter = new employeeListAdapter(getApplicationContext(),
                        R.layout.employees_list_item, employeeList);
                lv.setAdapter(lvAdapter);
            }
        }
    }
}
