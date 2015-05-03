package com.cb.warcraft3;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyELVAdapter extends BaseExpandableListAdapter {
    private Context  context;
    private String[] groupArray;
    private String[][] childArray;
    private Bitmap[][] image;
    private LayoutInflater layoutInflater;


    public MyELVAdapter(Context context, String[] groupArray, String[][] childArray, Bitmap[][] image) {
        this.context = context;
        this.groupArray = groupArray;
        this.childArray = childArray;
        this.image = image;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private class ViewHolderChild{
        ImageView imageViewChild;
        TextView textViewChild;
    }

    private class ViewHolderGroup{
        TextView textViewGroup;
    }

    @Override
    public int getGroupCount() {
        return groupArray.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.expandablelistview_group,null);
            viewHolderGroup = new ViewHolderGroup();
            viewHolderGroup.textViewGroup = (TextView)convertView.findViewById(R.id.expandablelistview_group_text);
            convertView.setTag(viewHolderGroup);
        }else{
            viewHolderGroup = (ViewHolderGroup)convertView.getTag();
        }
        viewHolderGroup.textViewGroup.setText(groupArray[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild viewHolderChild;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.expandablelistview_item,null);
            viewHolderChild = new ViewHolderChild();
            viewHolderChild.imageViewChild = (ImageView) convertView.findViewById(R.id.expandablelistview_item_image);
            viewHolderChild.textViewChild = (TextView) convertView.findViewById(R.id.expandablelistview_item_text);
            convertView.setTag(viewHolderChild);
        }else {
            viewHolderChild = (ViewHolderChild)convertView.getTag();
        }
        viewHolderChild.imageViewChild.setImageBitmap(image[groupPosition][childPosition]);
        String string = childArray[groupPosition][childPosition];
        viewHolderChild.textViewChild.setText(string);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
