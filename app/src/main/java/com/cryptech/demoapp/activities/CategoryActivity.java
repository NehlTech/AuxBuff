package com.cryptech.demoapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.HomePageAdapter;
import com.cryptech.demoapp.model.HomePageModel;
import com.cryptech.demoapp.model.HorizontalScrollProductModel;

import java.util.ArrayList;
import java.util.List;

import static com.cryptech.demoapp.db.Dbqueries.lists;
import static com.cryptech.demoapp.db.Dbqueries.loadedCategoriesNames;
import static com.cryptech.demoapp.db.Dbqueries.loadFragmentData;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private  HomePageAdapter homePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);

        /********  BANNER SLIDER STARTS ******/

//        bannerSliderViewPager = view.findViewById(R.id.banner_slider_viewPager);
//        List<SliderModel>sliderModelList = new ArrayList<SliderModel>();
//
//        sliderModelList.add(new SliderModel(R.drawable.banner3, "#077AE4"));
//        sliderModelList.add(new SliderModel(R.drawable.banner4, "#077AE4"));
//
//        sliderModelList.add(new SliderModel(R.drawable.banner, "#077AE4"));
//        sliderModelList.add(new SliderModel(R.drawable.banner2, "#077AE4"));
//        sliderModelList.add(new SliderModel(R.drawable.banner3, "#077AE4"));
//        sliderModelList.add(new SliderModel(R.drawable.banner4, "#077AE4"));
//
//        sliderModelList.add(new SliderModel(R.drawable.banner, "#077AE4"));
//        sliderModelList.add(new SliderModel(R.drawable.banner2, "#077AE4"));
//


//        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
//        bannerSliderViewPager.setAdapter(sliderAdapter);
//        bannerSliderViewPager.setClipToPadding(false);
//        bannerSliderViewPager.setPageMargin(20);
//
//        bannerSliderViewPager.setCurrentItem(currentPage);
//
//        //pager listener
//
//        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentPage = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    pageLooper();
//                }
//            }
//        };
//        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
//
//        startBannerSlideShow();
//
//        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                pageLooper();
//                stopBannerSlideShow();
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    startBannerSlideShow();
//                }
//                return false;
//            }
//        });
//
//        /********  BANNER SLIDER ENDS ******/

//
//        /********  STRIP AD SLIDER STARTS ******/
//
//        stripAdImage = view.findViewById(R.id.strip_ad_banner);
//        stripAdImageContainer = view.findViewById(R.id.strip_ad_container);
//
//        stripAdImage.setImageResource(R.drawable.banner);
//        stripAdImageContainer.setBackgroundColor(Color.parseColor("#000000"));
//
//        /********  STRIP AD SLIDER ENDS ******/

        /********  HORIZONTAL PRODUCT STARTS ******/


        List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Huawei",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone1, "T-Mobile 1",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Nokia",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone3, "Samsung",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Huawei",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Nokia 2",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone3, "Samsung 1",
//                "SD 445 processor", "Gh 650/-"));
//        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone1, "T-Mobile 2",
//                "SD 445 processor", "Gh 650/-"));

//        HorizontalScrollProductAdapter horizontalScrollProductAdapter = new HorizontalScrollProductAdapter(horizontalScrollProductModelList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        h_recyclerView.setLayoutManager(linearLayoutManager);
//        h_recyclerView.setAdapter(horizontalScrollProductAdapter);
//        horizontalScrollProductAdapter.notifyDataSetChanged();
        /********  HORIZONTAL PRODUCT ENDS ******/



        /********  TESTING STARTS ******/


        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

//        List<HomePageModel> homePageModelList = new ArrayList<>();
//        homePageModelList.add(new HomePageModel(0,sliderModelList));
//        homePageModelList.add(new HomePageModel(1, R.drawable.banner,"#ff0000"));
//        homePageModelList.add(new HomePageModel(2,"Deals of the Day", horizontalScrollProductModelList));
//        homePageModelList.add(new HomePageModel(3,"Deals of the Day", horizontalScrollProductModelList));
//        homePageModelList.add(new HomePageModel(1, R.drawable.banner,"#000000"));
//        homePageModelList.add(new HomePageModel(3,"Deals of the Day", horizontalScrollProductModelList));
//        homePageModelList.add(new HomePageModel(2,"Deals of the Day", horizontalScrollProductModelList));
//        homePageModelList.add(new HomePageModel(1, R.drawable.banner2,"#ffff00"));
//        homePageModelList.add(new HomePageModel(0, sliderModelList));

        int listPosition = 0;
        for (int x = 0; x < loadedCategoriesNames.size(); x++) {
            if (loadedCategoriesNames.get(x).equals(title.toUpperCase())) {
                listPosition = x;
            }
        }
        if (listPosition == 0) {
            loadedCategoriesNames.add(title.toUpperCase());
            lists.add(new ArrayList<HomePageModel>());
            homePageAdapter = new HomePageAdapter(lists.get(loadedCategoriesNames.size() -1));
            loadFragmentData(homePageAdapter, this, loadedCategoriesNames.size() -1, title);
        } else {
            homePageAdapter = new HomePageAdapter(lists.get(listPosition));
        }

        categoryRecyclerView.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();


        /********  TESTING ENDS ******/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        } else if (id == android.R.id.home) {

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
