package com.cryptech.demoapp.adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.activities.ProductDetailsActivity;
import com.cryptech.demoapp.activities.ViewAllActivity;
import com.cryptech.demoapp.model.HomePageModel;
import com.cryptech.demoapp.model.HorizontalScrollProductModel;
import com.cryptech.demoapp.model.SliderModel;
import com.cryptech.demoapp.model.WishListModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }


    @Override
    public int getItemViewType(int position) {

        switch (homePageModelList.get(position).getType()) {
            case 0:

                return HomePageModel.BANNER_SLIDER;

            case 1:

                return HomePageModel.STRIP_AD_BANNER;

            case 2:

                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;

            case 3:

                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout_banner, parent, false);

                return new BannerSliderViewHolder(bannerSliderView);

            case HomePageModel.STRIP_AD_BANNER:
                View stripAdView = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);

                return new StripAdSliderViewHolder(stripAdView);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout_parent_container, parent, false);

                return new HorizontalProductViewHolder(horizontalProductView);

            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);

                return new GridProductViewHolder(gridProductView);

            default:
                return null;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);
                break;

            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundColor();
                ((StripAdSliderViewHolder) holder).setStripAd(resource, color);
                break;

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String layoutColor = homePageModelList.get(position).getBackgroundColor();
                String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
                List<WishListModel> viewAllProductList = homePageModelList.get(position).getViewAllProductList();
                List<HorizontalScrollProductModel> horizontalScrollProductModelList = homePageModelList.get(position).getHorizontalScrollProductModelList();
                ((HorizontalProductViewHolder) holder).setHorizontalProductLayout(horizontalScrollProductModelList, horizontalLayoutTitle, layoutColor, viewAllProductList);
                break;

            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridLayoutColor = homePageModelList.get(position).getBackgroundColor();
                String gridLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalScrollProductModel> gridScrollProductModelList = homePageModelList.get(position).getHorizontalScrollProductModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(gridScrollProductModelList, gridLayoutTitle, gridLayoutColor);
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager bannerSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_viewPager);


        }

        /********  BANNER SLIDER PAGE LOOPER METHOD STARTS******/

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);

            bannerSliderViewPager.setCurrentItem(currentPage);

            //pager listener

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(sliderModelList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(sliderModelList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    pageLooper(sliderModelList);
                    stopBannerSlideShow();

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderModelList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }
        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {

                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSlideShow() {
            timer.cancel();
        }

        /********  BANNER SLIDER PAGE LOOPER METHOD ENDS******/
    }

    public class StripAdSliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView stripAdImage;
        private ConstraintLayout stripAdImageContainer;

        public StripAdSliderViewHolder(@NonNull View itemView) {
            super(itemView);

            stripAdImage = itemView.findViewById(R.id.strip_ad_banner);
            stripAdImageContainer = itemView.findViewById(R.id.strip_ad_container);

        }

        private void setStripAd(String resource, String color) {
//            stripAdImage.setImageResource(resource);
            Glide.with(itemView.getContext())
                    .load(resource)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.home))
                    .into(stripAdImage);
            stripAdImageContainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout container;
        private TextView h_itemTitle;
        private Button h_viewAllBtn;
        private RecyclerView h_recyclerView;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            h_itemTitle = itemView.findViewById(R.id.horizontal_deals_of_day);
            h_viewAllBtn = itemView.findViewById(R.id.horizontal_view_all);
            h_recyclerView = itemView.findViewById(R.id.horizontal_recyclerView);
        }

        private void setHorizontalProductLayout(List<HorizontalScrollProductModel> horizontalScrollProductModelList, final String title, String color, final List<WishListModel> viewAllProductList) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            }
            h_itemTitle.setText(title);

            if (horizontalScrollProductModelList.size() > 8) {
                h_viewAllBtn.setVisibility(View.VISIBLE);
                h_viewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewAllActivity.wishListModelList = viewAllProductList;
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code",0);
                        viewAllIntent.putExtra("title", title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                h_viewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalScrollProductAdapter horizontalScrollProductAdapter = new HorizontalScrollProductAdapter(horizontalScrollProductModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            h_recyclerView.setLayoutManager(linearLayoutManager);
            h_recyclerView.setAdapter(horizontalScrollProductAdapter);
            horizontalScrollProductAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout container;
        private TextView gridLayoutTitle;
        private Button gridLayoutViewAllBtn;
//        private GridView gridView;
        private GridLayout gridProductLayout;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewAllBtn = itemView.findViewById(R.id.grid_product_layout_viewAll_btn);
            gridProductLayout = itemView.findViewById(R.id.grid_product_layout_gridView);
        }

        private void setGridProductLayout(final List<HorizontalScrollProductModel> horizontalScrollProductModelList, final String title, String color) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            }
            gridLayoutTitle.setText(title);
//            gridView.setAdapter(new GridProductLayoutAdapter(horizontalScrollProductModelList));
            gridLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewAllActivity.horizontalScrollProductModelList = horizontalScrollProductModelList;
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code",1);
                    viewAllIntent.putExtra("title", title);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });

            for (int x = 0; x < 4; x++) {
                ImageView productImage = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_productImage);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_productTitle);
                TextView productDescription = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_productDescription);
                TextView productPrice = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_productPrice);

//                productImage.setImageResource(horizontalScrollProductModelList.get(x).getProductImage());
                Glide.with(itemView.getContext()).load(horizontalScrollProductModelList.get(x).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.home)).into(productImage);
                productTitle.setText(horizontalScrollProductModelList.get(x).getProductTitle());
                productDescription.setText(horizontalScrollProductModelList.get(x).getProductDescription());
                productPrice.setText("Gh " +horizontalScrollProductModelList.get(x).getProductPrice() + "/-");
                gridProductLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));
                gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }

        }
    }

}
