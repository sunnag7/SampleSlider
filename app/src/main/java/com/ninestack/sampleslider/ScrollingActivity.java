package com.ninestack.sampleslider;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    ViewPager _pager;
    ImageButton leftBtn,rightBtn;
    RecyclerView myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        _pager = (ViewPager) findViewById(R.id.pager);
        leftBtn = (ImageButton)findViewById(R.id.left_nav);
        rightBtn =  (ImageButton) findViewById(R.id.right_nav);
        myList = (RecyclerView) findViewById(R.id.recyclerviewFrag);
        _images = Arrays.asList( getResources().getStringArray(R.array.user_photos));
        Assert.assertNotNull(_images);
        GalleryPagerAdapter _adapter = new GalleryPagerAdapter( this);
        _pager.setAdapter(_adapter);
        //_pagerThumb.setAdapter(_adapter);
        _pager.setOffscreenPageLimit(4); // how many images to load into memory
        _adapter.notifyDataSetChanged();
        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(_images);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false);
        myList.setLayoutManager(horizontalLayoutManagaer);
        myList.setAdapter(horizontalAdapter);
        horizontalAdapter.notifyDataSetChanged();
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = _pager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    _pager.setCurrentItem(tab);
                } else if (tab == 0) {
                    _pager.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = _pager.getCurrentItem();
                tab++;
                _pager.setCurrentItem(tab);
            }
        });
        // MenuScroll.setSmoothScrollingEnabled(true);

        _pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //MenuScroll.smoothScrollTo(position*302, 0);
                /*int offset=(position*300)+(position+1)*4;
                if(offset<(304*_adapter.getCount()-2))
                MenuScroll.smoothScrollBy(offset, 0);*/

            }

            @Override
            public void onPageSelected(int position) {
                /*  int offset=(position*300)+(position+1)*4;
                if(offset<(304*_adapter.getCount()-4))
                MenuScroll.smoothScrollTo((position*300)+(position+1)*4, 0);
                */
                myList.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GalleryPagerAdapter extends PagerAdapter {
        Context _context;
        LayoutInflater _inflater;

        GalleryPagerAdapter(Context context) {
            _context = context;
            _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return _images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = _inflater.inflate(R.layout.pager_gallery_item, container, false);
            container.addView(itemView);

            // Get the border size to show around each image
            //int borderSize = _thumbnails.getPaddingTop();

            // Get the size of the actual thumbnail image
            // int thumbnailSize = ((RelativeLayout.LayoutParams)
            //        _pager.getLayoutParams()).bottomMargin - (borderSize*2);

            // Set the thumbnail layout parameters. Adjust as required
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,260);
            params.setMargins(3,3,3,3);

            // You could also set like so to remove borders
           /* ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                   ViewGroup.LayoutParams.WRAP_CONTENT,
                   ViewGroup.LayoutParams.WRAP_CONTENT);*/
            //   bmArray = new ArrayList<Bitmap>();
            final ImageView thumbView = new ImageView(_context);
            thumbView.setTag(position);
            thumbView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            thumbView.setLayoutParams(params);
            thumbView.setMinimumHeight(260);

            thumbView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    //Log.d(TAG, "Thumbnail clicked");
                    // Set the pager position when thumbnail clicked
                    //v.setSelected(true);
                   /* for (int j=0;j<_thumbnails.getChildCount();j++)
                    {
                        if (_thumbnails.getChildAt(j).getBackground()!=null) {
                            _thumbnails.getChildAt(j).setBackground(null);
                        }
                    }

                    Drawable highlight = getResources().getDrawable( R.drawable.border);
                    _thumbnails.getChildAt(position).setPadding(4,4,4,4);
                    _thumbnails.getChildAt(position).setBackground(highlight);*/
                    _pager.setCurrentItem(position);
                    //int offset=(position*300)+(position+1)*4;
                    //if(offset<(304*_adapter.getCount()-2))
                    //MenuScroll.smoothScrollTo(offset, 0);
                }
            });

            //  _thumbnails.addView(thumbView);

            final ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
            /*final ImageView myCustomIcon = (ImageView) LayoutInflater.
                    from(tabL.getContext()).inflate(R.layout.my_custom_tab, null);
            myCustomIcon.setLayoutParams(params);
            myCustomIcon.setPadding(0,0,0,0);*/
            // Asynchronously load the image and set the thumbnail and pager view
            // final View view = getLayoutInflater().inflate(R.layout.my_custom_tab,null);
            // _thumbnails.addView(imageView);
            Glide.with( ScrollingActivity.this).load("" + _images.get(position))
                    .placeholder( R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageView);

            imageView.setOnClickListener(new OnImageClickListener(position));

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
    private List<String> _images;
    private class OnImageClickListener implements View.OnClickListener {
        int _postion;
        // constructor
        OnImageClickListener(int position) {
            this._postion = position;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            ArrayList<String> list = new ArrayList<>( _images);
           /* Intent i = new Intent(BoligerDetailsActivity.this, FullScreenViewActivity.class);
            i.putExtra("position", _postion);
            i.putStringArrayListExtra("stock_list",list);
            startActivity(i);*/
        }
    }

    class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {
        private List<String> horizontalList;

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imgCards;
            MyViewHolder(View view) {
                super(view);
                imgCards = (ImageView) view.findViewById(R.id.imgDisplay);
            }
        }

        HorizontalAdapter(List<String> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public HorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_thumb_image , parent, false);

            /*LinearLayout l2 = (LinearLayout) itemView.findViewById(R.id.mainLin);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth-90, ViewGroup.LayoutParams.WRAP_CONTENT );
            //  layoutParams.gravity = Gravity.CENTER;
            layoutParams.setMargins(8,4,0,0);
            l2.setLayoutParams(layoutParams);*/
            return new HorizontalAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final HorizontalAdapter.MyViewHolder holder, final int position) {

            Glide.with( ScrollingActivity.this).load("" + horizontalList.get(position))
                    .placeholder( R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.imgCards);

            holder.imgCards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {_pager.setCurrentItem(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size() ;
        }
    }
}
