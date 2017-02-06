# FC_ADS_6.2MyUtility
패스트캠퍼스 안드로이드 스튜디오, 프로젝트 6.2 Fragment를 이용하였다.<br/>
4개 탭중 2개는 이전에 하였던 프로젝트(계산기, 단위변환기) 를 이용할 수 있게 만들었고<br/>
나머지 2개의 탭은 웹뷰와 구글 지도를 사용할 수 있게 만들었다.


## activity 특이사항
* FragmentStatePagerAdapter를 상속받은 class 내부에 구현해야하는 getCount의 return값이 tablayout 개수가 된다.

* tabLayout 을 정할 때 아래와 같이 한다.

```
    // onCreate 내부 :
        //탭 레이아웃 정의
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.addTab( tabLayout.newTab().setText("계산기"));
        tabLayout.addTab( tabLayout.newTab().setText("단위변환"));
        tabLayout.addTab( tabLayout.newTab().setText("검색"));
        tabLayout.addTab( tabLayout.newTab().setText("현재위치"));

```

* 페이저 변경과 탭을 연동시키는 방법은 아래와 같다.

```
    // 1. 페이저가 변경 되었을때 탭을 바꿔주는 리스너
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    // 2. 탭이 변경되었을 때 페이지를 바꿔주는 리스너
    tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

```

## Google Maps 사용시 특이사항

* 생성 : new - Google - GoogleMaps Activity

* 키생성 : https://console.developers.google.com/ 로 시작하는 주소로 접속하여 키생성 -> YOUR KEY HERE 을 지우고 붙여넣기

```
public class FourFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    SupportMapFragment mapFragment;

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        // fragment에서 맵뷰를 호출하는 방법.
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);

        return view;
    }

    // MapsActivity에 있는 onMapReady 파일 그대로 Override
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(37.515696, 127.021345);
        map.addMarker(new MarkerOptions().position(sydney).title("대기빌딩"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
```
