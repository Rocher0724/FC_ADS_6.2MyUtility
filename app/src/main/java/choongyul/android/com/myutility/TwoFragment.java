package choongyul.android.com.myutility;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    Button btnLength, btnArea, btnWeight;
    LinearLayout loLength, loArea, loWeight;

    Spinner lengthSp1_1, lengthSp1_2;
    Spinner lengthSp2_1, lengthSp2_2;
    Spinner lengthSp3_1, lengthSp3_2;
    TextView unit1, unit2;

    ArrayList<String> length1 = new ArrayList<String>();
    ArrayList<String> length2 = new ArrayList<String>();

    String change1 = "", change2 = "";

    EditText edit1_1, edit2_1, edit3_1;
    TextView edit1_2, edit2_2, edit3_2;
    TextView ans1_1, ans1_2, ans1_3, ans1_4, ans1_5, ans1_6, ans1_7, ans1_8;
    TextView ans2_1, ans2_2, ans2_3, ans2_4, ans2_5, ans2_6, ans2_7, ans2_8;
    TextView ans3_1, ans3_2, ans3_3, ans3_4, ans3_5, ans3_6, ans3_7, ans3_8;

    View view;
    Context context;

    public TwoFragment() {
        // Required empty public constructor
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);

        btnLength = (Button) view.findViewById(R.id.bx1);
        btnArea = (Button) view.findViewById(R.id.bx2);
        btnWeight = (Button) view.findViewById(R.id.bx3);

        loLength = (LinearLayout) view.findViewById(R.id.layoutLength);
        loArea = (LinearLayout) view.findViewById(R.id.layoutArea);
        loWeight = (LinearLayout) view.findViewById(R.id.layoutWeight);

        btnLength.setOnClickListener(clickListener);
        btnArea.setOnClickListener(clickListener);
        btnWeight.setOnClickListener(clickListener);

        setLengthSpinner();


        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            loLength.setVisibility(View.GONE);
            loArea.setVisibility(View.GONE);
            loWeight.setVisibility(View.GONE);
            switch (view.getId()) {
                case R.id.bx1:
                    length1.clear();
                    length2.clear();
                    loLength.setVisibility(View.VISIBLE);
                    setLengthSpinner();
                    break;
                case R.id.bx2:
                    length1.clear();
                    length2.clear();
                    loArea.setVisibility(View.VISIBLE);
                    setAreaSpinner();
                    break;
                case R.id.bx3:
                    length1.clear();
                    length2.clear();
                    loWeight.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    public void setLengthSpinner() {
        edit1_1 = (EditText) view.findViewById(R.id.edit1_1);
        edit1_2 = (TextView) view.findViewById(R.id.edit1_2);

        ans1_1 = (TextView) view.findViewById(R.id.answer1_1);
        ans1_2 = (TextView) view.findViewById(R.id.answer1_2);
        ans1_3 = (TextView) view.findViewById(R.id.answer1_3);
        ans1_4 = (TextView) view.findViewById(R.id.answer1_4);
        ans1_5 = (TextView) view.findViewById(R.id.answer1_5);
        ans1_6 = (TextView) view.findViewById(R.id.answer1_6);
        ans1_7 = (TextView) view.findViewById(R.id.answer1_7);
        ans1_8 = (TextView) view.findViewById(R.id.answer1_8);

        lengthSp1_1 = (Spinner) view.findViewById(R.id.sp1_1);
        lengthSp1_2 = (Spinner) view.findViewById(R.id.sp1_2);
        unit1 = (TextView) view.findViewById(R.id.unit1_1);
        unit2 = (TextView) view.findViewById(R.id.unit1_2);

        String length[] = {"밀리미터(mm)", "센치미터(cm)", "미터(m)", "킬로미터(km)", "인치(in)", "피트(ft)", "야드(yd)", "마일(mile)"};
        for (int i = 0; i < length.length; i++) {
            length1.add(length[i]);
        }
        for (int i = 0; i < length.length; i++) {
            length2.add(length[i]);
        }

        ArrayAdapter<String> lengthAdapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, length1);
        lengthSp1_1.setAdapter(lengthAdapter1);
        ArrayAdapter<String> lengthAdapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, length2);
        lengthSp1_2.setAdapter(lengthAdapter2);

        lengthSp1_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change1 = length1.get(i);
                unit1.setText(change1);
                calculateLength(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change1 = length1.get(0);
                unit1.setText(change1);
                calculateLength(change1, change2);
            }
        });

        lengthSp1_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change2 = length2.get(i);
                unit2.setText(change2);
                calculateLength(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change2 = length2.get(0);
                unit2.setText(change2);
                calculateLength(change1, change2);
            }
        });
    }

    public void calculateLength(String str1, String str2) {
        edit1_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateLength(change1, change2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        double get = 0;
        if (!(edit1_1.getText().toString().equals(""))) {
            get = Double.parseDouble(edit1_1.getText().toString());
        }

        if (str1.equals("밀리미터(mm)")) {
            ans1_1.setText(String.valueOf(get));
            ans1_2.setText(String.valueOf(get * 0.1));
            ans1_3.setText(String.valueOf(get * 0.001));
            ans1_4.setText(String.valueOf(get * 0.0000001));
            ans1_5.setText(String.valueOf(get / 25.4));
            ans1_6.setText(String.valueOf(get / 304.8));
            ans1_7.setText(String.valueOf(get / 914.4));
            ans1_8.setText(String.valueOf(get / 1609344));
        } else if (str1.equals("센치미터(cm)")) {
            ans1_1.setText(String.valueOf(get * 10));
            ans1_2.setText(String.valueOf(get));
            ans1_3.setText(String.valueOf(get / 100));
            ans1_4.setText(String.valueOf(get / 100000));
            ans1_5.setText(String.valueOf(get / 254));
            ans1_6.setText(String.valueOf(get / 3048));
            ans1_7.setText(String.valueOf(get / 9144));
            ans1_8.setText(String.valueOf(get / 16093440));
        } else if (str1.equals("미터(m)")) {
            ans1_1.setText(String.valueOf(get * 1000));
            ans1_2.setText(String.valueOf(get * 100));
            ans1_3.setText(String.valueOf(get));
            ans1_4.setText(String.valueOf(get / 1000));
            ans1_5.setText(String.valueOf(get / 0.0254));
            ans1_6.setText(String.valueOf(get / 0.3048));
            ans1_7.setText(String.valueOf(get / 0.9144));
            ans1_8.setText(String.valueOf(get * 0.000621));
        } else if (str1.equals("킬로미터(km)")) {
            ans1_1.setText(String.valueOf(get * 1000000));
            ans1_2.setText(String.valueOf(get * 100000));
            ans1_3.setText(String.valueOf(get * 1000));
            ans1_4.setText(String.valueOf(get));
            ans1_5.setText(String.valueOf(get * 39370.0787));
            ans1_6.setText(String.valueOf(get * 3280.8399));
            ans1_7.setText(String.valueOf(get * 1093.6133));
            ans1_8.setText(String.valueOf(get * 0.621371));
        } else if (str1.equals("인치(in)")) {
            ans1_1.setText(String.valueOf(get * 25.4));
            ans1_2.setText(String.valueOf(get * 2.54));
            ans1_3.setText(String.valueOf(get * 0.0254));
            ans1_4.setText(String.valueOf(get * 0.000025));
            ans1_5.setText(String.valueOf(get));
            ans1_6.setText(String.valueOf(get * 0.083333));
            ans1_7.setText(String.valueOf(get * 0.027778));
            ans1_8.setText(String.valueOf(get * 0.000016));
        } else if (str1.equals("피트(ft)")) {
            ans1_1.setText(String.valueOf(get * 304.8));
            ans1_2.setText(String.valueOf(get * 30.48));
            ans1_3.setText(String.valueOf(get * 0.3048));
            ans1_4.setText(String.valueOf(get * 0.000305));
            ans1_5.setText(String.valueOf(get * 12));
            ans1_6.setText(String.valueOf(get));
            ans1_7.setText(String.valueOf(get * 0.333333));
            ans1_8.setText(String.valueOf(get * 0.000189));
        } else if (str1.equals("야드(yd)")) {
            ans1_1.setText(String.valueOf(get * 914.4));
            ans1_2.setText(String.valueOf(get * 91.44));
            ans1_3.setText(String.valueOf(get * 0.9144));
            ans1_4.setText(String.valueOf(get * 0.000914));
            ans1_5.setText(String.valueOf(get * 36));
            ans1_6.setText(String.valueOf(get * 3));
            ans1_7.setText(String.valueOf(get));
            ans1_8.setText(String.valueOf(get * 0.000568));
        } else if (str1.equals("마일(mile)")) {
            ans1_1.setText(String.valueOf(get * 1609344));
            ans1_2.setText(String.valueOf(get * 160934.4));
            ans1_3.setText(String.valueOf(get * 1609.344));
            ans1_4.setText(String.valueOf(get * 1.609344));
            ans1_5.setText(String.valueOf(get * 63360));
            ans1_6.setText(String.valueOf(get * 5280));
            ans1_7.setText(String.valueOf(get * 1760));
            ans1_8.setText(String.valueOf(get));
        }

        if (str2.equals("밀리미터(mm)")) {
            edit1_2.setText(ans1_1.getText());
        } else if (str2.equals("센치미터(cm)")) {
            edit1_2.setText(ans1_2.getText());
        } else if (str2.equals("미터(m)")) {
            edit1_2.setText(ans1_3.getText());
        } else if (str2.equals("킬로미터(km)")) {
            edit1_2.setText(ans1_4.getText());
        } else if (str2.equals("인치(in)")) {
            edit1_2.setText(ans1_5.getText());
        } else if (str2.equals("피트(ft)")) {
            edit1_2.setText(ans1_6.getText());
        } else if (str2.equals("야드(yd)")) {
            edit1_2.setText(ans1_7.getText());
        } else if (str2.equals("마일(mile)")) {
            edit1_2.setText(ans1_8.getText());
        }

    }



    public void setAreaSpinner() {
        edit2_1 = (EditText)view.findViewById(R.id.edit2_1);
        edit2_2 = (TextView)view.findViewById(R.id.edit2_2);

        ans2_1 = (TextView)view.findViewById(R.id.answer2_1);
        ans2_2 = (TextView)view.findViewById(R.id.answer2_2);
        ans2_3 = (TextView)view.findViewById(R.id.answer2_3);
        ans2_4 = (TextView)view.findViewById(R.id.answer2_4);
        ans2_5 = (TextView)view.findViewById(R.id.answer2_5);
        ans2_6 = (TextView)view.findViewById(R.id.answer2_6);
        ans2_7 = (TextView)view.findViewById(R.id.answer2_7);
        ans2_8 = (TextView)view.findViewById(R.id.answer2_8);

        lengthSp2_1 = (Spinner)view.findViewById(R.id.sp2_1);
        lengthSp2_2 = (Spinner)view.findViewById(R.id.sp2_2);
        unit1 = (TextView)view.findViewById(R.id.unit2_1);
        unit2 = (TextView)view.findViewById(R.id.unit2_2);

        String length[] = {"제곱미터(m^2)", "아르(a)", "헥타르(ha)", "제곱킬로미터(km^2)", "제곱피트(ft^2)", "제곱야드(yd^2)", "에이커(ac)", "평방자"};
        for(int i=0;i<length.length;i++) {
            length1.add(length[i]);
        }
        for(int i=0;i<length.length;i++) {
            length2.add(length[i]);
        }

        ArrayAdapter<String> lengthAdapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, length1);
        lengthSp2_1.setAdapter(lengthAdapter1);
        ArrayAdapter<String> lengthAdapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, length2);
        lengthSp2_2.setAdapter(lengthAdapter2);

        lengthSp2_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change1 = length1.get(i);
                unit1.setText(change1);
                calculateArea(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change1 = length1.get(0);
                unit1.setText(change1);
                calculateArea(change1, change2);
            }
        });

        lengthSp2_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change2 = length2.get(i);
                unit2.setText(change2);
                calculateArea(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change2 = length2.get(0);
                unit2.setText(change2);
                calculateArea(change1, change2);
            }
        });
    }

    public void calculateArea(String str1, String str2) {
        edit2_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateArea(change1, change2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        double get = 0;
        if(!(edit2_1.getText().toString().equals(""))) {
            get = Double.parseDouble(edit2_1.getText().toString());
        }

        if(str1.equals("제곱미터(m^2)")) {
            ans2_1.setText(String.valueOf(get));
            ans2_2.setText(String.valueOf(get*0.01));
            ans2_3.setText(String.valueOf(get*0.0001));
            ans2_4.setText(String.valueOf(get*0.0000001));
            ans2_5.setText(String.valueOf(get*10.76391));
            ans2_6.setText(String.valueOf(get*1.19599));
            ans2_7.setText(String.valueOf(get*0.000247));
            ans2_8.setText(String.valueOf(get*10.89));
        } else if(str1.equals("아르(a)")) {
            ans2_1.setText(String.valueOf(get*100));
            ans2_2.setText(String.valueOf(get));
            ans2_3.setText(String.valueOf(get*0.01));
            ans2_4.setText(String.valueOf(get*0.0001));
            ans2_5.setText(String.valueOf(get*1076.39104));
            ans2_6.setText(String.valueOf(get*119.599005));
            ans2_7.setText(String.valueOf(get*0.024711));
            ans2_8.setText(String.valueOf(get*1089));
        } else if(str1.equals("헥타르(ha)")) {
            ans2_1.setText(String.valueOf(get*10000));
            ans2_2.setText(String.valueOf(get*100));
            ans2_3.setText(String.valueOf(get));
            ans2_4.setText(String.valueOf(get*0.01));
            ans2_5.setText(String.valueOf(get*107639.104));
            ans2_6.setText(String.valueOf(get*11959.9005));
            ans2_7.setText(String.valueOf(get*2.471054));
            ans2_8.setText(String.valueOf(get*108900));
        } else if(str1.equals("제곱킬로미터(km^2)")) {
            ans2_1.setText(String.valueOf(get*1000000));
            ans2_2.setText(String.valueOf(get*10000));
            ans2_3.setText(String.valueOf(get*100));
            ans2_4.setText(String.valueOf(get));
            ans2_5.setText(String.valueOf(get*10763910.4));
            ans2_6.setText(String.valueOf(get*1195990.05));
            ans2_7.setText(String.valueOf(get*247.105381));
            ans2_8.setText(String.valueOf(get*10890000));
        } else if(str1.equals("제곱피트(ft^2)")) {
            ans2_1.setText(String.valueOf(get*0.092903));
            ans2_2.setText(String.valueOf(get*0.000929));
            ans2_3.setText(String.valueOf(get*0.0000092903));
            ans2_4.setText(String.valueOf(get*0.000000092903));
            ans2_5.setText(String.valueOf(get));
            ans2_6.setText(String.valueOf(get*0.111111));
            ans2_7.setText(String.valueOf(get*0.000023));
            ans2_8.setText(String.valueOf(get*1.011714));
        } else if(str1.equals("제곱야드(yd^2)")) {
            ans2_1.setText(String.valueOf(get*0.836127));
            ans2_2.setText(String.valueOf(get*0.008361));
            ans2_3.setText(String.valueOf(get*0.000084));
            ans2_4.setText(String.valueOf(get*8.3613e-7));
            ans2_5.setText(String.valueOf(get*9));
            ans2_6.setText(String.valueOf(get));
            ans2_7.setText(String.valueOf(get*0.000207));
            ans2_8.setText(String.valueOf(get*9.105427));
        } else if(str1.equals("에이커(ac)")) {
            ans2_1.setText(String.valueOf(get*4046.85642));
            ans2_2.setText(String.valueOf(get*40.468564));
            ans2_3.setText(String.valueOf(get*0.404686));
            ans2_4.setText(String.valueOf(get*0.004047));
            ans2_5.setText(String.valueOf(get*43560));
            ans2_6.setText(String.valueOf(get*4840));
            ans2_7.setText(String.valueOf(get));
            ans2_8.setText(String.valueOf(get*44070.2664));
        } else if(str1.equals("평방자")) {
            ans2_1.setText(String.valueOf(get*0.091827));
            ans2_2.setText(String.valueOf(get*0.000918));
            ans2_3.setText(String.valueOf(get*9.1827e-6));
            ans2_4.setText(String.valueOf(get*9.1827e-8));
            ans2_5.setText(String.valueOf(get*0.988422));
            ans2_6.setText(String.valueOf(get*0.109825));
            ans2_7.setText(String.valueOf(get*0.000023));
            ans2_8.setText(String.valueOf(get));
        }

        if(str2.equals("제곱미터(m^2)")) {
            edit2_2.setText(ans2_1.getText());
        } else if(str2.equals("아르(a)")) {
            edit2_2.setText(ans2_2.getText());
        } else if(str2.equals("헥타르(ha)")) {
            edit2_2.setText(ans2_3.getText());
        } else if(str2.equals("제곱킬로미터(km^2)")) {
            edit2_2.setText(ans2_4.getText());
        } else if(str2.equals("제곱피트(ft^2)")) {
            edit2_2.setText(ans2_5.getText());
        } else if(str2.equals("제곱야드(yd^2)")) {
            edit2_2.setText(ans2_6.getText());
        } else if(str2.equals("에이커(ac)")) {
            edit2_2.setText(ans2_7.getText());
        } else if(str2.equals("평방자")) {
            edit2_2.setText(ans2_8.getText());
        }
    }
}
