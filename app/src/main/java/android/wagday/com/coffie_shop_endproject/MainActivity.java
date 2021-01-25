package android.wagday.com.coffie_shop_endproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.wagday.com.coffie_shop_endproject.model.MySQLiteOpenHelper;
import android.wagday.com.coffie_shop_endproject.model.Users;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button log_btn;
    TextView txt_create;
    EditText userName,pass;
    ProgressDialog pro;
    public static Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log_btn = findViewById(R.id.loin_btn);
        txt_create = findViewById(R.id.creat_lbl);
        final MySQLiteOpenHelper sql=new MySQLiteOpenHelper(this);
        userName=findViewById(R.id.txtUsername);
        pass=findViewById(R.id.pass);
        pro=new ProgressDialog(MainActivity.this);
        log_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
              /*  if(!userName.getText().toString().equals("")&&!pass.getText().toString().equals("")) {
                    DataBes d = new DataBes(sql);
                    users=d.p(pass.getText().toString(), userName.getText().toString());
                    if (users!=null) {
                        Toast.makeText(MainActivity.this, "مرحبا بك "+users.getFirst_name()+" "+users.getLast_name(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, home_type_pro.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "اسم المستخدم او كلمةالمرور غير صحيحة", Toast.LENGTH_LONG).show();
                        userName.requestFocus();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "ادخل اسم المستخدم و كلمةالمرور", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(MainActivity.this, home_type_pro.class);
                   startActivity(intent);
                    finish();
                }
               Intent intent = new Intent(MainActivity.this,home_type_pro.class);
                startActivity(intent);
           */
                loginUsers();
            }
        });

        txt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,create_account.class);
                startActivity(intent);
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loginUsers(){
        if (IsConnectedInternet())
        {
            pro.setTitle("جاري عملية التسجيل");
            pro.show();
            sendDataToWebServiceADI_Register();
        }
        else {
            // Toast.makeText(create_account.this,"يرجى التاكد من الاتصال بالانترنت",Toast.LENGTH_LONG).show();
            StyleableToast.makeText(this, "يرجى التاكد من الاتصال بالانترنت", Toast.LENGTH_LONG, R.style.Eror_connection).show();
        }

    }

    // check internet

    public boolean IsConnectedInternet(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            return connected;
        }
        else{
            connected = false;
            return connected;
        }
    }

    //send data to server

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendDataToWebServiceADI_Register() {

        //  String url="https://712051643.000webhostapp.com/connection.php";
        String url="https://investigatory-boile.000webhostapp.com/log.php";
        System.out.println("1111111111111111111111111111111111111111111111");


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pro.dismiss();

                System.out.println("_________________IN REspons_______________________"+response);
              //  JSONObject jsonObject = null;
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String success=jsonObject.getString("sucess");
                    JSONArray jsonArray=jsonObject.getJSONArray("user_data");
                   // jsonObject = new JSONObject(response);

                 //   String sucess=jsonObject.getString("sucess");
                  //  JSONArray result = jsonObject.getJSONArray("user_data");

                    if (success.equals("1"))
                    {
                        JSONObject object=jsonArray.getJSONObject(0);
                        StyleableToast.makeText(MainActivity.this,"تمت عملية التسجيل بنجاح", Toast.LENGTH_LONG, R.style.Eror_connection).show();
                        // Toast.makeText(create_account.this,result.getString(1),Toast.LENGTH_LONG).show();
                        users=new Users();
                        users.setId(Integer.parseInt(object.getString("id")));
                        users.setFirst_name(object.getString("fname"));
                        users.setLast_name(object.getString("lname"));
                        users.setEmail(object.getString("email"));
                        users.setPhone(object.getString("phone"));
                        users.setNameLog(object.getString("user"));
                        users.setPassword(object.getString("pass"));
                        Intent intent=new Intent(MainActivity.this,home_type_pro.class);
                        startActivity(intent);

                    }
                    else if (success.equals("0"))
                    {
                        StyleableToast.makeText(MainActivity.this,"يرجى التأكد من البيانات المدخلة...",Toast.LENGTH_LONG, R.style.Eror_connection).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }  }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pro.dismiss();
                Toast.makeText(MainActivity.this,"eroor respones",Toast.LENGTH_LONG).show();

                System.out.println("_______________________ERROR______________________"+error);
            }


        }
        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parems=new HashMap<String, String>();

                parems.put("u_pass", pass.getText().toString());
                parems.put("username", userName.getText().toString());
    /*            parems.put("u_tell", txt_phne.getText().toString());
                parems.put("u_email", txt_email.getText().toString());
                parems.put("u_pass", txt_pass1.getText().toString());
                parems.put("username", txt_username.getText().toString());
*/
//                 parems.put("u_notes", u.getU_notes());

//                parems.put("isadmin", u.getIsAdmin()+"");
//                parems.put("admin_id",id_owner+"");
                parems.put("op","login");


                return parems;
            }
        };

        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    }
