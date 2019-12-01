package com.fullstackoasis.awsrdssearchdemo;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
// import com.mysql.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

// See
final class MySQLAsyncTask extends AsyncTask<String, Void, String> {
    private static final String url = "jdbc:mysql://healthdata-1.c84gpzpanfrn.us-east-1.rds.amazonaws.com:3306/healthdata-1";
    private static final String user = "MY_WONDERFUL_USER_NAME";
    private static final String pass = "MY_WONDERFUL_PASSWORD";
    private static String res;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connection success "  + params);

            String result = "Database Connection Successful\n";
            // Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("SELECT inspection_id FROM health_reports" +
                    " WHERE " +
                    "inspection_id = ? ");
            ps.setInt(0, Integer.valueOf(params[0]).intValue());

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                result += rs.getString(0).toString() + "\n";
                result += rs.getString(1).toString() + "\n";
            }
            res = result;
            System.out.println("Database Result success "  + res);
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }
    @Override
    protected void onPostExecute(String result) {
        this.res = result;
    }
}
