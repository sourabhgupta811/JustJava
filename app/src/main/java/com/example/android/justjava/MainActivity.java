package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity=2;
    boolean whippedCreamState=false;
    boolean chocolateState=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view)
    {
        EditText nameView=findViewById(R.id.name);
        String name=nameView.getText().toString();
        if(name.equals("")){
            name="Guest";
        }
        int price=calculatePrice();
        String emailBody=createOrderSummary(price,name);
        String emailSubject="JustJava order for "+name;

        String uriText =
                "mailto:youremail@gmail.com" +
                        "?subject=" + Uri.encode(emailSubject) +
                        "&body=" + Uri.encode(emailBody);

        Uri uri = Uri.parse(uriText);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(uri);
        startActivity(Intent.createChooser(sendIntent, "Send email"));


    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(int price,String name){
        String priceMessage = "Name = "+name+"\nAdd whipped cream? "+whippedCreamState +
                "\nAdd chocolate? "+chocolateState+"\nquantity = "+quantity +"\nTotal: $"+price;
        priceMessage=priceMessage+"\nThank You!";
        return priceMessage;

    }

    public void increment(View v){
        if(quantity<100){
        quantity=quantity+1;
        display(quantity);
        }
        else{
            Toast.makeText(this,"you can't have more than 100 coffee.",Toast.LENGTH_SHORT).show();
        }

    }
    public void decrement(View v) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Toast.makeText(this, "you can't have less than 1 coffee.", Toast.LENGTH_SHORT).show();
        }
    }


    private int calculatePrice(){
        int price=5;
        if(whippedCreamState)
         price=price+1;
        if(chocolateState)
            price=price+2;
        return price*quantity;


    }

    public void addCream(View v){
        CheckBox x=(CheckBox)v;
        if(v.getTag().toString().equals("0"))
        whippedCreamState=x.isChecked();
        else if(v.getTag().toString().equals("1"))
        chocolateState=x.isChecked();
        }

    }