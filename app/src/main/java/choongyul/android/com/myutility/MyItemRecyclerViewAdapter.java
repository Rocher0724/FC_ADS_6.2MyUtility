package choongyul.android.com.myutility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import choongyul.android.com.myutility.FiveFragment.OnListFragmentInteractionListener;
import choongyul.android.com.myutility.dummy.DummyContent;
import choongyul.android.com.myutility.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> mDatas = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public MyItemRecyclerViewAdapter(Context context, OnListFragmentInteractionListener listener) {
        mListener = listener;
        this.context = context;

        // 폰에서 이미지를 가져온 후 datas에 세팅한다.
        ContentResolver resolver = context.getContentResolver();
        // 1. 데이터 uri 정의
        Uri target = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        // 2. projection 정의
        String projection[] = {MediaStore.Images.Media.DATA}; // 이미지 경로가 있는 컬럼 명이 데이터가 됨.
        // 섬네일을 가져오는방법
//        String projection[] = {MediaStore.Images.Thumbnails.DATA};

        // 3. 데이터 가져오기
        Cursor cursor = resolver.query(target, projection, null,null,null);
        // 4. 데이터에 담아주기
        if(cursor != null) {
            while (cursor.moveToNext()) {
                // 데이터 컬럼이 0번인덱스에만 있는 것을 알기때문에 이렇게 하였음
                String uriString = cursor.getString(0);
                // 가져온 str을 uri로 파싱
                mDatas.add(uriString);
            }
            cursor.close();
        }
    }

//    private static Uri getImageSimple(String id) {
//        return Uri.parse("content://media/external/images/media/" + id);
//    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_five, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imageUri = mDatas.get(position);
//        holder.imageView.setImageURI(holder.imageUri);
        Glide.with(context).load(holder.imageUri).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public String imageUri;

        public ViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.gallImageView);
            imageUri = null;

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("adress", imageUri);
                    context.startActivity(intent);
                }
            });

        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
