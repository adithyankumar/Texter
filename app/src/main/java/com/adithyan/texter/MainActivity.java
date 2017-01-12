package com.adithyan.texter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.techiedeveloper.texter.OnHyperLinkClickListener;
import com.techiedeveloper.texter.Texter;


public class MainActivity extends AppCompatActivity {
TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);

        Texter.into(tv,"This is my first lib. In my life. first fg")
             //   .background(Color.RED,0,4)
             //   .foreground(Color.BLUE,0,4)
             //   .underLine(8,10)
                .textStyle(Typeface.BOLD,0,4)
               // .underLine("is","my")
                //.background("#0000ff",1.5f,"my","fg")
                .subScript(8,10)
              //  .imageSpan(R.mipmap.ic_launcher,7,8,0.1f)
              //  .background("first","#008000")
                .hyperLink(new OnHyperLinkClickListener() {
                    @Override
                    public void onClick(View var1) {
                        Toast.makeText(MainActivity.this, "Hai", Toast.LENGTH_SHORT).show();
                    }
                },0,4,true)
               /* .hyperLink(new OnHyperLinkClickListener() {
                    @Override
                    public void onClick(View var1) {
                        Toast.makeText(MainActivity.this, "list", Toast.LENGTH_SHORT).show();
                    }
                },true,"first","life")*/


                .build();
    }
}
