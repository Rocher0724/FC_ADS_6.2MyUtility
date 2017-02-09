package choongyul.android.com.myutility;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // 탭 및 페이저 속성 정의
    final int TAB_COUNT = 4;
    OneFragment one;
    TwoFragment two;
    ThreeFragment three;
    FourFragment four;

    private final int REQ_CODE = 100;
    private int page_position = 0;
    Stack<Integer> pageStack = new Stack<>();
    ViewPager viewPager;
    Boolean backPressed = false;


    private LocationManager manager;
    public LocationManager getLoactionManager() {
        return manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메써드 추적 시작 - 마시멜로우 이상에서 다운
//        Debug.startMethodTracing("trace_result");

        // 프레그먼트 init
        init();

    }

    public void init() {
        // 프래그먼트 init
        one = new OneFragment();
        two = new TwoFragment();
        two.setContext(this);

        three = new ThreeFragment();
        four = new FourFragment();

        //탭 레이아웃 정의
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.addTab( tabLayout.newTab().setText("계산기"));
        tabLayout.addTab( tabLayout.newTab().setText("단위변환"));
        tabLayout.addTab( tabLayout.newTab().setText("검색"));
        tabLayout.addTab( tabLayout.newTab().setText("현재위치"));

        //프래그먼트 페이저 작성
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //아답터 생성
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        //아답터 세팅
        viewPager.setAdapter(adapter);

        // 1. 페이저가 변경 되었을때 탭을 바꿔주는 리스너
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // 2. 페이지의 변경사항을 체크한다.
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if ( !backPressed ){
                    pageStack.push(position);
                } else {
                    backPressed = false;
                }
                page_position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 3. 탭이 변경되었을 때 페이지를 바꿔주는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
    } else {
        gpsChecker();
    }

    }


    public void gpsChecker() {
        // LocationManager 객체를 얻어온다
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // GPS 센서가 켜져있는지 확인
        // 꺼져있다면 GPS를 켜는 페이지로 이동
        if(!gpsCheck()) {
            // 팝업창 만들기
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            // 1. 팝업창 제목
            alertDialog.setTitle("GPS 켜기");
            // 2. 팝업창 메시지
            alertDialog.setMessage("GPS 꺼져있습니다. \n 설정창으로 이동하시겠습니까?");
            // 3. yes 버튼 생성
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });

            // 4. no 버튼 생성
            alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();

        }
        // 메서드 추적 종료 - 마시멜로우 이상에서 다운.
//        Debug.stopMethodTracing();
    }

    // gps가 꺼져있는지 체크. 롤리팝 이하버전
    private boolean gpsCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            String gps = android.provider.Settings.Secure.getString(getContentResolver()
                    , Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if(gps.matches(".*gps.*")) {
                return true;
            } else {
                return false;
            }
        }
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0: fragment = one; break;
                case 1: fragment = two; break;
                case 2: fragment = three; break;
                case 3: fragment = four; break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }

    // 1. 권한체크
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        // 1.1 런타임 권한체크 (권한을 추가할때 1.2 목록작성과 2.1 권한체크에도 추가해야한다.)
        if ( checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // 1.2 요청할 권한 목록 작성
            String permArr[] = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            // 1.3 시스템에 권한요청
            requestPermissions(permArr, REQ_CODE);

        } else {
            gpsChecker();
        }
    }

    // 2. 권한체크 후 콜백 - 사용자가 확인 후 시스템이 호출하는 함수
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == REQ_CODE) {
            // 2.1 배열에 넘긴 런타임 권한을 체크해서 승인이 됐으면
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 2.2 프로그램 실행
                gpsChecker();
            } else {
                Toast.makeText(this, "권한을 허용하지 않으시면 프로그램을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
                // 선택 : 1 종료, 2 권한체크 다시물어보기 할수도 있다. 일단은 끝내기
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        switch (page_position) {
            case 2:
                // 뒤로가기가 가능하면 아무런 동작을 하지않는다.
                if (three.goBack()) {

                } else { // 뒤로가기가 안되면 앱을 닫는다.
                    goBackStack();
                }
                break;
            // 위 조건에 해당되지않는 케이스는 아래 로직을 처리한다.
            default:
                goBackStack();
                break;
        }
    }

    private void goBackStack() {
        if (pageStack.size()<1){
            super.onBackPressed();
        } else {
            backPressed = true;
            viewPager.setCurrentItem(pageStack.pop());
        }
    }
}

