package choongyul.android.com.myutility;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {
    TextView result;
    TextView testSideView;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnPlus;
    Button btnMinus;
    Button btnMulti;
    Button btnDivide;
    Button btnRun;
    Button btnCancel;

    double previous = 0;
    double nextNum = 0;
    String strarr[];
    double realResult = 0;

    ArrayList<String> list = new ArrayList<>();

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        // 2. 정의된 위젯변수에 xml의 위젯 id를 가져와서 담아준다.
        result = (TextView) view.findViewById(R.id.result);
        testSideView = (TextView) view.findViewById(R.id.testSide);


        btn0 = (Button) view.findViewById(R.id.btn0);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        btn5 = (Button) view.findViewById(R.id.btn5);
        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        btn9 = (Button) view.findViewById(R.id.btn9);
        btnPlus = (Button) view.findViewById(R.id.btnPlus);
        btnMinus = (Button) view.findViewById(R.id.btnMinus);
        btnMulti = (Button) view.findViewById(R.id.btnMul);
        btnDivide = (Button) view.findViewById(R.id.btnDivide);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnRun = (Button) view.findViewById(R.id.btnRun);
        // 3. 위젯변수를 리스너에 달아준다.
        btn0.setOnClickListener(clickListener);
        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);
        btn3.setOnClickListener(clickListener);
        btn4.setOnClickListener(clickListener);
        btn5.setOnClickListener(clickListener);
        btn6.setOnClickListener(clickListener);
        btn7.setOnClickListener(clickListener);
        btn8.setOnClickListener(clickListener);
        btn9.setOnClickListener(clickListener);
        btnPlus.setOnClickListener(clickListener);
        btnMinus.setOnClickListener(clickListener);
        btnMulti.setOnClickListener(clickListener);
        btnDivide.setOnClickListener(clickListener);
        btnRun.setOnClickListener(clickListener);
        btnCancel.setOnClickListener(clickListener);

        return view;
    }

    private double evaluate(ArrayList<String> list) {
        int index;
        double one = 0;
        double two = 0;
        double sum = 0;

        // 곱셈 나눗셈 연산 먼저
        for( index = 0 ; index < list.size() ; index++) {
            String item = list.get(index);

            if ( item.equals("X")) {
                one = Double.parseDouble(list.get(index-1));
                two = Double.parseDouble(list.get(index+1));
                sum = one*two;
                list.set(index, sum+"");
                list.remove(index+1);
                list.remove(index-1);
                index = index - 1;

            } else if (item.equals("/")) {
                one = Double.parseDouble(list.get(index-1));
                two = Double.parseDouble(list.get(index+1));
                sum = one/two;
                list.set(index, sum+"");
                list.remove(index+1);
                list.remove(index-1);
                index = index - 1;
            }
        }

        // 덧셈 뺄셈 연산
        for( index = 0 ; index < list.size() ; index++) {
            String item = list.get(index);
            if ( item.equals("+")) {
                one = Double.parseDouble(list.get(index-1));
                two = Double.parseDouble(list.get(index+1));
                sum = one + two;
                list.set(index, sum+"");
                list.remove(index+1);
                list.remove(index-1);
                index = index - 1;

            } else if (item.equals("-")) {
                one = Double.parseDouble(list.get(index-1));
                two = Double.parseDouble(list.get(index+1));
                sum = one - two;
                list.set(index, sum+"");
                list.remove(index+1);
                list.remove(index-1);
                index = index - 1;
            }
        }

        realResult = Double.parseDouble(list.get(0));
        return realResult;
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn0:
                    testSideView.setText(testSideView.getText() + "0");
                    break;
                case R.id.btn1:
                    testSideView.setText(testSideView.getText() + "1");
                    break;
                case R.id.btn2:
                    testSideView.setText(testSideView.getText() + "2");
                    break;
                case R.id.btn3:
                    testSideView.setText(testSideView.getText() + "3");
                    break;
                case R.id.btn4:
                    testSideView.setText(testSideView.getText() + "4");
                    break;
                case R.id.btn5:
                    testSideView.setText(testSideView.getText() + "5");
                    break;
                case R.id.btn6:
                    testSideView.setText(testSideView.getText() + "6");
                    break;
                case R.id.btn7:
                    testSideView.setText(testSideView.getText() + "7");
                    break;
                case R.id.btn8:
                    testSideView.setText(testSideView.getText() + "8");
                    break;
                case R.id.btn9:
                    testSideView.setText(testSideView.getText() + "9");
                    break;


                case R.id.btnPlus:
                    if (previous > 0) {
                        testSideView.setText(previous + "+");

                    } else {
                        testSideView.setText(testSideView.getText() + "+");
                    }
                    break;

                case R.id.btnMinus:
                    if (previous > 0) {
                        testSideView.setText(previous + "-");
                    } else {
                        testSideView.setText(testSideView.getText() + "-");
                    }
                    break;

                case R.id.btnMul:
                    if (previous > 0) {
                        testSideView.setText(previous + "X");
                    } else {
                        testSideView.setText(testSideView.getText() + "X");
                    }
                    break;

                case R.id.btnDivide:
                    if (previous > 0) {
                        testSideView.setText(previous + "/");
                    } else {
                        testSideView.setText(testSideView.getText() + "/");
                    }
                    break;


                case R.id.btnRun:
                    strarr = testSideView.getText().toString().split("(?<=[X/+-])|(?=[X/+-])");

                    for (String item : strarr) {
                        list.add(item);
                    }

                    String reresult = evaluate(list) + "";
                    result.setText(reresult);
                    previous = realResult;
                    list.clear();
                    break;
                case R.id.btnCancel:
                    previous = 0;
                    nextNum = 0;
                    result.setText("");
                    testSideView.setText("");
                    break;
            }
        }
    };
}
