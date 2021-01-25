package android.wagday.com.coffie_shop_endproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.wagday.com.coffie_shop_endproject.model.MySQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class create_account extends AppCompatActivity {
    Button btn;
    EditText txt_fname,txt_lname,txt_email,txt_phne,txt_pass1,txt_pass2,txt_username;
    MySQLiteOpenHelper sql;
    ProgressDialog pro;
    ImageView img;
    String img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // creat_acc_local
        btn = (Button)findViewById(R.id.creat_acc_btn);
        txt_fname = (EditText)findViewById(R.id.txtfname);
        txt_lname = (EditText)findViewById(R.id.txtlname);
        txt_email = (EditText)findViewById(R.id.txtemail);
        txt_phne = (EditText)findViewById(R.id.txtphone);
        txt_pass1 = (EditText)findViewById(R.id.txtpass1);
        txt_pass2 = (EditText)findViewById(R.id.txtpass2);
        txt_username = (EditText)findViewById(R.id.txtUsername);
        img=findViewById(R.id.img);
        sql = new MySQLiteOpenHelper(create_account.this);
        pro=new ProgressDialog(create_account.this);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_img_gallrey();
            }
        });

        //create_user_servers
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View view) {

                add_new_user();

            }
        });

    }
    private void get_img_gallrey() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    1888);
        }
        else {
            Intent camera = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(camera, 1551);
        }
    }
    public String ConvertImageToBase64(Bitmap bitmap){
        String img_str = "";
        try{
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] image_bytes=stream.toByteArray();

            img_str = Base64.encodeToString(image_bytes, Base64.DEFAULT);
        }catch (Exception e){
            System.out.println("The exception from Converting is :::"+e.getMessage());
        }

        return img_str;
    }

    public Bitmap ConvertBase64Toimages(String img){

        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        System.out.println("The size of bitmap image agter low converting is ::::::::::::::::::::::::::::::::::::::::"+decodedByte.getByteCount());

        return decodedByte;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //   Bitmap imgg = (Bitmap) data.getExtras().get("data");
        //   imageView.setImageBitmap(imgg);

        if (requestCode==1441) {
            Bitmap imgh = (Bitmap) data.getExtras().get("data");
            System.out.println("====================The image in String :-    ");
            System.out.println(ConvertImageToBase64(imgh));

            img.setImageBitmap(imgh);
        }

        if (requestCode==1551) {

            Uri uri=data.getData();
//            Bitmap img = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(img);
            img.setImageURI(uri);

        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void add_new_user() {
        if (IsConnectedInternet())
        {
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
        pro.setTitle("جاري عملية التسجيل");
        pro.show();
      //  String url="https://712051643.000webhostapp.com/connection.php";
        String url="https://investigatory-boile.000webhostapp.com/log.php";
        System.out.println("1111111111111111111111111111111111111111111111");


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pro.dismiss();

                System.out.println("_________________IN REspons_______________________"+response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);

                    JSONArray result = jsonObject.getJSONArray("result");

                    if (result.getString(0).equals("1"))
                    {
                        StyleableToast.makeText(create_account.this,result.getString(1), Toast.LENGTH_LONG, R.style.Eror_connection).show();
                       // Toast.makeText(create_account.this,result.getString(1),Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(create_account.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        StyleableToast.makeText(create_account.this,result.getString(1), Toast.LENGTH_LONG, R.style.Eror_connection).show();

                       // Toast.makeText(create_account.this,result.getString(1),Toast.LENGTH_LONG).show();


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }  }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pro.dismiss();
                Toast.makeText(create_account.this,"eroor respones",Toast.LENGTH_LONG).show();

                System.out.println("_______________________ERROR______________________"+error);
            }


        }
        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parems=new HashMap<String, String>();

                parems.put("f_name", txt_fname.getText().toString());
                parems.put("l_name", txt_lname.getText().toString());
                parems.put("u_tell", txt_phne.getText().toString());
                parems.put("u_email", txt_email.getText().toString());
                parems.put("u_pass", txt_pass1.getText().toString());
                parems.put("username", txt_username.getText().toString());
               // parems.put("profil", img.getImag);
//                 parems.put("u_notes", u.getU_notes());

//                parems.put("isadmin", u.getIsAdmin()+"");
//                parems.put("admin_id",id_owner+"");
                parems.put("op","reg");


                return parems;
            }
        };

        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 30000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 30000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



}