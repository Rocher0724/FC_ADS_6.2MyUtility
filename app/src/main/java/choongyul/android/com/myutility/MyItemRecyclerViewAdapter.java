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

    private List<String> mDatas = new ArrayList<>();
    private final Context context;

    public MyItemRecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        mDatas = datas;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_five, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 최신자료부터 집어넣도록 순서 역순으로 세팅
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
