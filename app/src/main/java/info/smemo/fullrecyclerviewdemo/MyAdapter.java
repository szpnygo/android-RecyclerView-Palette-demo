package info.smemo.fullrecyclerviewdemo;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by suzhenpeng on 2015/6/4.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private ArrayList<ImageItem> imageItemArrayList;
    private Context context;
    private OnItemClickListener onClickListener;

    public MyAdapter(Context context,@NonNull ArrayList<ImageItem> imageItemArrayList) {
        this.imageItemArrayList = imageItemArrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        //判断类型
        switch (viewType) {
            case ImageItem.LEFT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_item, null);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return new HolderLeft(view);
            case ImageItem.RIGHT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_item, null);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return new HolderRight(view);
        }
        return null;
    }

//    Vibrant （有活力的）
//    Vibrant dark（有活力的 暗色）
//    Vibrant light（有活力的 亮色）
//    Muted （柔和的）
//    Muted dark（柔和的 暗色）
//    Muted light（柔和的 亮色）

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ImageItem imageItem = getItem(position);
        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null)
                    onClickListener.onClick(imageItem, position);
            }
        });
        switch (getItemViewType(position)) {
            case ImageItem.LEFT:
                //获取Holder
                HolderLeft left = (HolderLeft) holder;
                left.textView.setText(imageItem.title);
                left.imageView.setImageResource(imageItem.img);
                //获取图片中的颜色
                BitmapDrawable drawable = (BitmapDrawable) left.imageView.getDrawable();
                left.textView.setBackgroundColor(Palette.from(drawable.getBitmap()).generate().getVibrantColor(0xfff));
                left.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "adapeter内部注册:" + imageItem.title, Toast.LENGTH_SHORT).show();
                    }
                });
                left.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = imageItemArrayList.indexOf(imageItem);
                        if (index != -1) {
                            synchronized (imageItemArrayList) {
                                imageItemArrayList.remove(index);
                            }
                            remove(index);
                        }
                    }
                });

                break;
            case ImageItem.RIGHT:
                HolderRight right = (HolderRight) holder;
                right.textView.setText(imageItem.title);
                right.imageView.setImageResource(imageItem.img);
                BitmapDrawable drawableright = (BitmapDrawable) right.imageView.getDrawable();
                right.textView.setBackgroundColor(Palette.from(drawableright.getBitmap()).generate().getDarkVibrantColor(0xfff));
                right.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "adapeter内部注册:" + imageItem.title, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }

    public synchronized void remove(int position) {
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public synchronized void add(int position) {
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return imageItemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return imageItemArrayList.get(position).type;
    }

    public ImageItem getItem(int position) {
        return imageItemArrayList.get(position);
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnItemClickListener {
        public void onClick(Object object, int position);
    }

    class HolderLeft extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public TextView delete;

        public HolderLeft(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.title);
            delete = (TextView) itemView.findViewById(R.id.delete);

        }
    }

    class HolderRight extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public HolderRight(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

