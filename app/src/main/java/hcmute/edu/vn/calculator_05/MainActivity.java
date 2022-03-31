package hcmute.edu.vn.calculator_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
    private TextView Screen;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,
            btn7,btn8,btn9,btnPlus,btnSub,btnMulti,btnDiv,btnDot,btnCancel,btnEqual;
    private  String Input,Answer;

    private String temp="";

    String workings="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Screen= findViewById(R.id.tv);
        btn0=findViewById(R.id.bt0);
        btn1=findViewById(R.id.bt1);
        btn2=findViewById(R.id.bt2);
        btn3=findViewById(R.id.bt3);
        btn4=findViewById(R.id.bt4);
        btn5=findViewById(R.id.bt5);
        btn6=findViewById(R.id.bt6);
        btn7=findViewById(R.id.bt7);
        btn8=findViewById(R.id.bt8);
        btn9=findViewById(R.id.bt9);
        btnPlus=findViewById(R.id.btcong);
        btnSub=findViewById(R.id.bttru);
        btnMulti=findViewById(R.id.btnhan);
        btnDiv=findViewById(R.id.btchia);
        btnDot=findViewById(R.id.btcham);
        btnEqual=findViewById(R.id.btbang);
        btnCancel=findViewById(R.id.btC);

    }

    public void ButtonClick(View view){
        Button button=(Button) view;
        String data= button.getText().toString();
        String chuoi=Screen.getText().toString();

        if(chuoi.contains("*")||chuoi.contains("/")) {
            if(chuoi.contains("+")||chuoi.contains("-")){
                if(data.equals("+")||data.equals("-")||data.equals("X")|| data.equals("/")) return;
            }
        }
        if(Screen.getText().toString().equals("")){
            if(data.equals("+")||data.equals("-")||data.equals("X")|| data.equals("/")) return;
        }
        if(temp.equals("+")||temp.equals("-")||temp.equals("X")|| temp.equals("/")){
            if(temp!="" && temp.equals(data)) return;
        }
        if(data.equals("/")&& temp.equals("X")){
            String[] a=Input.split("\\*");
            Input=a[0]+"/";
            Screen.setText(Input);
            temp="/";
            return;
        }

        if(data.equals("X")&& temp.equals("/")){
            String[] a=Input.split("/");
            Input=a[0]+"*";
            Screen.setText(Input);
            temp="X";
            return;
        }
        temp=data;
        switch(data){

            case "C":
                Input="";
                break;

            case "X":
                Sovle();
                Input+="*";
                break;
            case "=":
                Sovle();
                Answer+=Input;
                break;
            case "Del":
                String newText=Input.substring(0,Input.length()-1);
                Input=newText;
                break;


             default:
                 if(Input==null){
                     Input="";
                 }
                 if(data.equals("+")||data.equals("-")||data.equals("/")){
                     Sovle();
                 }
                 Input+=data;

        }
        Screen.setText(Input);
    }

    private void Sovle(){
        if(Input.split("\\*").length==2){
            String number[]= Input.split("\\*");
            try{
                Double mul= Double.parseDouble(number[0])*Double.parseDouble(number[1]);
                Input=mul +"";
            }
            catch(Exception e){

            }
        }else if(Input.split("/").length==2){
            String number[]= Input.split("/");
            try{
                Double div= Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                Input=div+"";
            }
            catch(Exception e){

            }
        }else if(Input.split("\\+").length==2){
            String number[]= Input.split("\\+");
            try{
                Double sum= Double.parseDouble(number[0])+Double.parseDouble(number[1]);
                Input=sum+"";
            }
            catch(Exception e){

            }
        }
        else if(Input.split("-").length>1){
            System.out.println("Handle Sub");
            System.out.println(Input);
            String number[]= Input.split("-");
            //to substract from negative number
            if(number[0]=="" && number.length==2) {
                number[0]=0+"";
            }
            try{
                double sub=0;
                if(number.length==2){
                    sub= Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                }
                else if(number.length==3){
                    sub= (-Double.parseDouble(number[1])) - Double.parseDouble(number[2]);
                }


                Input=sub +"";
            }
            catch(Exception e){

            }
        }

        String n[]=Input.split("\\." );
        if(n.length>1){
            if(n[1].equals("0")){
                Input=n[0];
            }
        }

        Screen.setText(Input);

    }

}