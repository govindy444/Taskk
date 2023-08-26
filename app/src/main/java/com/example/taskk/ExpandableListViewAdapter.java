package com.example.taskk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ExpandableListViewAdapter extends BaseExpandableListAdapter {
   private Context context;
   // group titles
   private List<String> listDataGroup;
   // child data
   private HashMap<String, List<String>> listDataChild;
   private ArrayList<String> chkBoxDta = new ArrayList<>();

   QuantityKistener quantityKistener;

   public ExpandableListViewAdapter(Context context, List<String> listDataGroup,
                                    HashMap<String, List<String>> listChildData, QuantityKistener quantityKistener) {
      this.context = context;
      this.listDataGroup = listDataGroup;
      this.listDataChild = listChildData;
      this.quantityKistener = quantityKistener;
   }

   @Override
   public Object getChild(int groupPosition, int childPosititon) {
      return this.listDataChild.get(this.listDataGroup.get(groupPosition))
              .get(childPosititon);
   }

   @Override
   public long getChildId(int groupPosition, int childPosition) {
      return childPosition;
   }

   @Override
   public View getChildView(int groupPosition, final int childPosition,
                            boolean isLastChild, View convertView, ViewGroup parent) {
      final String childText = (String) getChild(groupPosition, childPosition);
      if (convertView == null) {
         LayoutInflater layoutInflater = (LayoutInflater) this.context
                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = layoutInflater.inflate(R.layout.list_row_child, null);
      }
      CheckBox chkChild = convertView.findViewById(R.id.checkBox);

      chkChild.setText(childText);

      chkChild.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            if (chkChild.isChecked()) {

               chkBoxDta.add(childText);
            } else {

               chkBoxDta.remove(childText);
            }
            quantityKistener.onQuantityChange(chkBoxDta);
         }
      });
       chkChild.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent motionEvent) {

             showEditTextDialog2(chkChild);
             return false;
          }
       });

      return convertView;
   }

   @Override
   public int getChildrenCount(int groupPosition) {
      return this.listDataChild.get(this.listDataGroup.get(groupPosition))
              .size();
   }

   @Override
   public Object getGroup(int groupPosition) {
      return this.listDataGroup.get(groupPosition);
   }

   @Override
   public int getGroupCount() {
      return this.listDataGroup.size();
   }

   @Override
   public long getGroupId(int groupPosition) {
      return groupPosition;
   }

   @Override
   public View getGroupView(int groupPosition, boolean isExpanded,
                            View convertView, ViewGroup parent) {
      String headerTitle = (String) getGroup(groupPosition);
      if (convertView == null) {
         LayoutInflater layoutInflater = (LayoutInflater) this.context
                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = layoutInflater.inflate(R.layout.list_row_group, null);
      }
      TextView textViewGroup = convertView.findViewById(R.id.textViewGroup);
      textViewGroup.setTypeface(null, Typeface.BOLD);
      textViewGroup.setText(headerTitle);

      textViewGroup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            showEditTextDialog(textViewGroup);
         }
      });
      return convertView;
   }

   @Override
   public boolean hasStableIds() {
      return false;
   }

   @Override
   public boolean isChildSelectable(int groupPosition, int childPosition) {
      return true;
   }

   private void showEditTextDialog2(CheckBox checkBox) {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setTitle("Change Text");

      final EditText editText = new EditText(context);
      editText.setInputType(InputType.TYPE_CLASS_TEXT);
      builder.setView(editText);

      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
            String enteredText = editText.getText().toString();
             checkBox.setText(enteredText);
             dialog.cancel();
         }
      });
      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
         }
      });

      // Show the AlertDialog
      builder.show();
   }

   private void showEditTextDialog(TextView textView) {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setTitle("Change Text");

      final EditText editText = new EditText(context);
      editText.setInputType(InputType.TYPE_CLASS_TEXT);
      builder.setView(editText);

      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
            String enteredText = editText.getText().toString();
           textView.setText(enteredText);
         }
      });
      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
         }
      });

      // Show the AlertDialog
      builder.show();
   }
}

