package com.leo104.lastmemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.leo104.lastmemo.R;
import com.leo104.lastmemo.UpdateActivity;
import com.leo104.lastmemo.model.Memo;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    Context context;
    ArrayList<Memo> memoList;

    public MemoAdapter(Context context, ArrayList<Memo> memoList) {

        this.context = context;
        this.memoList = memoList;
    }


    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // xml 파일을 연결하는 작업
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_row, parent, false);
        return new MemoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder holder, int position) {
        // 뷰에 데이터를 셋팅한다!
        Memo memo = memoList.get(position);

        holder.txtTitle.setText( memo.getTitle() );
        holder.txtContent.setText( memo.getContent() );
    }

    @Override
    public int getItemCount() {
        // 전체 데이터의 갯수를 적어준다.
        return memoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;
        TextView txtContent;
        ImageView imgDelete;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 1. 인텐트에 유저가 어떤 행을 눌렀는지 파악하여
                    //    누른 이름과 전화번호를 담아서
                    int index = getAdapterPosition();

                    Memo memo = memoList.get(index);

                    // 2. 수정 액티비티를 띄운다.
                    //    어떤 액티비티가 어떤 액티비티를 띄운다!! => 인텐트에 있어야 한다.
                    Intent intent = new Intent(context, UpdateActivity.class);

                    intent.putExtra("memo", memo);

//                    intent.putExtra("id", contact.id);
//                    intent.putExtra("name", contact.name);
//                    intent.putExtra("phone", contact.phone);


                    context.startActivity(intent);
                }
            });

        }
    }
}
