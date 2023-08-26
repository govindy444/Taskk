package com.example.taskk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class TagAdapter extends RecyclerView.Adapter<TagAdapter.MyView> {

   // List with String type
   private List<String> list;
   QuantityKistener quantityKistener;

   public class MyView extends RecyclerView.ViewHolder {

      // Text View
      TextView textView;
      ImageView clear;

      public MyView(View view)
      {
         super(view);
         textView = (TextView)view.findViewById(R.id.txt1);
         clear = view.findViewById(R.id.clear);
      }
   }


   public TagAdapter(List<String> horizontalList,QuantityKistener quantityKistener)
   {
      this.list = horizontalList;
      this.quantityKistener= quantityKistener;
   }


   @Override
   public MyView onCreateViewHolder(ViewGroup parent,
                                    int viewType)
   {

      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_item, parent, false);

      return new MyView(itemView);
   }

   @Override
   public void onBindViewHolder(final MyView holder,
                                final int position)
   {

      holder.textView.setText(list.get(position));
      holder.clear.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            int clickedPosition = holder.getAdapterPosition();
            quantityKistener.onDeleteData(list.get(position));

         }
      });
   }

   @Override
   public int getItemCount()
   {
      return list.size();
   }
}
