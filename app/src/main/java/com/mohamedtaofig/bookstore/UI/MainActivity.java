package com.mohamedtaofig.bookstore.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.mohamedtaofig.bookstore.Adapters.BookAdapter;
import com.mohamedtaofig.bookstore.Model.Book;
import com.mohamedtaofig.bookstore.R;
import com.mohamedtaofig.bookstore.Utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView bookItem;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookArrayList;
    private static final String MYKEY = "secret";
    private ImageView logout;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookArrayList = new ArrayList<>();
        bookAdapter = new BookAdapter(bookArrayList,this);
        bookItem = findViewById(R.id.bookItem);
        userName = findViewById(R.id.userName);
        logout  = findViewById(R.id.logout);

        getBook();

        userData();


    }

    private void userData() {
        SharedPreferences preferences = getSharedPreferences(MYKEY,0);
        String myname = preferences.getString("name","");
        userName.setText(myname);
        if (myname.equals("")){
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            finish();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });
    }


    private void getBook() {
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        bookItem.setLayoutManager(layoutManager);
        addBook();
        bookItem.setAdapter(bookAdapter);

    }

    private void addBook(){
            RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, SERVER.GET_BOOK_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    Log.w("err",response);
                    JSONArray books = mainObj.getJSONArray("book");
                    for (int i = 0; i < books.length(); i++) {
                        JSONObject object = books.getJSONObject(i);

                        Book book = new Book(
                                object.getString("title"),
                                object.getString("description"),
                                SERVER.GET_BOOK_IMAGE + object.getString("image"),
                                object.getString("pdf_link"),
                                object.getString("date")
                        );
                        bookArrayList.add(book);

                    }
                    bookAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "الرجاء الاتصال بالانترنت", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}