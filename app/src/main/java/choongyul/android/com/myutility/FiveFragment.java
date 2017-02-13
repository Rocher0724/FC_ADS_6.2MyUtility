package choongyul.android.com.myutility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import choongyul.android.com.myutility.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FiveFragment extends Fragment {

    public final static int REQ_CAMERA = 101;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final int REQ_DETAIL = 102;
    private int mColumnCount = 2;
    Button btnCamera;
    View view;
    List<String> datas;
    MyItemRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    public FiveFragment() {
    }

    public static FiveFragment newInstance(int columnCount) {
        FiveFragment fragment = new FiveFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if ( view != null) {
            return view;
        }


        view = inflater.inflate(R.layout.fragment_item_list, container, false);
        btnCamera = (Button) view.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQ_CAMERA);
            }
        });
        loadData();

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

        adapter = new MyItemRecyclerViewAdapter(context, datas);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadData() {
        datas = new ArrayList<>();

        // 폰에서 이미지를 가져온 후 datas에 세팅한다.
        ContentResolver resolver = getContext().getContentResolver();
        // 1. 데이터 uri 정의
        Uri target = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // 2. projection 정의
//        String projection[] = {MediaStore.Images.Media.DATA}; // 이미지 경로가 있는 컬럼 명이 데이터가 됨.
        // 2.01 projection 정의시 데이터가 정렬되게 하기
        String projection[] = {MediaStore.Images.ImageColumns.DATA};
        // 2.02 projection 정의시 섬네일을 가져오는방법
//        String projection[] = {MediaStore.Images.Thumbnails.DATA};

        // 3. 데이터 가져올 때 정렬하기 - 2.01와 같이 해야함
        String order = MediaStore.Images.ImageColumns.DATE_ADDED +" DESC";
        // 3. 데이터 가져오기
        Cursor cursor = resolver.query(target, projection, null,null, order);
        // 4. 데이터에 담아주기
        if(cursor != null) {
            while (cursor.moveToNext()) {
                // 데이터 컬럼이 0번인덱스에만 있는 것을 알기때문에 이렇게 하였음
                String uriString = cursor.getString(0);
                // 가져온 str을 uri로 파싱
                datas.add(uriString);
            }
            cursor.close();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CAMERA:
                if (resultCode == RESULT_OK) {

                    // 사진을 찍었을 때 Fragment를 다시 세팅하기 위한 데이터로드
                    loadData();
                    // 아답터에 변경된 데이터 반영하기
                    adapter = new MyItemRecyclerViewAdapter(getContext(), datas);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "사진찍음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    String dataAdress = data.getData().toString();
                    intent.putExtra("adress", dataAdress);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "사진 안찍음", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 프래그먼트가 필요로하는 인터페이스를 구현했는지 검사
        // 프래그먼트가 실행되는 액티비티에 implement 해줘야한다.
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}

