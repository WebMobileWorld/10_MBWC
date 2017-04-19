package com.mbwc.e.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.text.util.Linkify;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.mbwc.e.myapplication.Global.GD;
import com.mbwc.e.myapplication.Models.CategoryModel;
import com.mbwc.e.myapplication.Models.DataModel;
import com.mbwc.e.myapplication.R;

import java.util.ArrayList;


/**
 * Created by E on 10/13/2016.
 */

public class ExpandableListExersizesAdapter extends BaseExpandableListAdapter {
    private Activity context;
    private ArrayList<CategoryModel> categoryModelArrayList ;
    public static VideoView videoView ;
    TextView textView ;
    ScrollView scrollView ;
    MediaController mediaControls;
    ImageView movie, note ;
    LinearLayout load_type ;
    public ExpandableListExersizesAdapter(Activity context, ArrayList<CategoryModel> categoryModelArrayList) {
        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList ;

    }
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<DataModel> dataModelArrayList = categoryModelArrayList.get(groupPosition).getDataModelArrayList();
        return dataModelArrayList.get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final DataModel dataModel = (DataModel)getChild(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_exerciseschild, null);
        }
        load_type = (LinearLayout)convertView.findViewById(R.id.load_type);
        movie = (ImageView)convertView.findViewById(R.id.iv_movie);
        note = (ImageView)convertView.findViewById(R.id.iv_note);
        load_type.removeView(videoView);
        load_type.removeView(scrollView);
        movie.setBackgroundResource(R.mipmap.movie_orange);
        note.setBackgroundResource(R.mipmap.note_f_orange);
        scrollView = new ScrollView(context);
        videoView = new VideoView(context);
        videoView.setLayoutParams(new ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        videoView.setZOrderOnTop(true);
        videoView.setPadding(10,0,10,0);
        if (mediaControls == null) {
            mediaControls = new MediaController(context);
            mediaControls.setAnchorView(videoView);
        }
        try {
            int resID= context.getResources().getIdentifier(dataModel.getDataname().trim(), "raw", context.getPackageName());
            videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + resID));
        }catch (Exception e){
            e.printStackTrace();
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaControls.setAnchorView(videoView);
                    }
                });
            }

        });
        load_type.addView(videoView);

        if (GD.play_flag == false) {

        } else  {
            videoView.stopPlayback();
        }
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setBackgroundResource(R.mipmap.note_f_orange);
                movie.setBackgroundResource(R.mipmap.movie_orange);
                load_type.removeView(scrollView);
                load_type.removeView(videoView);
                videoView.setLayoutParams(new ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                videoView.setPadding(10,0,10,0);
                videoView.setZOrderOnTop(true);
                if (mediaControls == null) {
                    mediaControls = new MediaController(context);
                    mediaControls.setAnchorView(videoView);
                }
                try {
                    int resID= context.getResources().getIdentifier(dataModel.getDataname().trim(), "raw", context.getPackageName());
                    videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + resID));
                }catch (Exception e){
                    e.printStackTrace();
                }
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                        videoView.start();
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                mediaControls.setAnchorView(videoView);
                            }
                        });
                    }

                });
                load_type.addView(videoView);
                GD.play_flag = true ;
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display display = context.getWindowManager().getDefaultDisplay();
                int width = display.getWidth();
                int height = display.getHeight();

                note.setBackgroundResource(R.mipmap.note_orange);
                movie.setBackgroundResource(R.mipmap.movie_f_orange);
                if (GD.play_flag == false) {

                } else {
                    videoView.stopPlayback();
                }
                load_type.removeView(videoView);
                load_type.removeView(scrollView);
                scrollView = new ScrollView(context);
                scrollView.setLayoutParams(new ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                textView = new TextView(context);
                textView.setLayoutParams(new ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#E88706"));
                if (width == 320 || width < 480){
                    textView.setTextSize(11);
                } else if (width == 480 || width < 600){
                    textView.setTextSize(14);
                } else if (width >= 600 ){
                    textView.setTextSize(15);
                } else {
                    textView.setTextSize(10);
                }
                int resId = context.getResources().getIdentifier("text_content","string",context.getPackageName());
                String getText = context.getString(resId);
                textView.setText(getText);
                Linkify.addLinks(textView, Linkify.WEB_URLS);
                scrollView.addView(textView);
                load_type.addView(scrollView);
            }
        });
        return convertView;
    }
    public void setGroup(boolean flag){
        load_type.removeView(videoView);
    }
    public int getChildrenCount(int groupPosition) {
        ArrayList<DataModel> dataModelArrayList = categoryModelArrayList.get(groupPosition).getDataModelArrayList();
        return dataModelArrayList.size();
    }

    public Object getGroup(int groupPosition) {
        return categoryModelArrayList.get(groupPosition);
    }
    public int getGroupCount() {
        return categoryModelArrayList.size();
    }
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        CategoryModel categoryModel = (CategoryModel)getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_exercisesparent,
                    null);
        }
        TextView number = (TextView) convertView.findViewById(R.id.parent_number);
        TextView listname = (TextView) convertView.findViewById(R.id.list_name);
        number.setText("" + (groupPosition+1));
        listname.setText(categoryModel.getCategory_name().trim());

        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
