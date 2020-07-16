package com.cryptech.demoapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.ProductDetailsAdapter;
import com.cryptech.demoapp.adapter.ProductImagesAdapter;
import com.cryptech.demoapp.fragments.ProductDescriptionFragment;
import com.cryptech.demoapp.fragments.ProductSpecificationFragment;
import com.cryptech.demoapp.model.ProductSpecificationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImageViewPager, productDetailsViewpager;
    private TextView productTitle, averageRatingMiniView, totalRatingsMiniView, productPrice, reducedPrice, tvCodIndicator, rewardTitle, rewardBody;
    private ImageView codIndicatorImageView;
    private TabLayout viewPagerIndicator, productDetailsTablayout;
    private FloatingActionButton addToWishListBtn;
    private Button couponRedemptionBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private ConstraintLayout productDetailsOnlyContainer;
    private ConstraintLayout productDetailsTabsContainter;

    private TextView productOnlyDescriptionBody;

    private static String productDescription;
    private static String productOtherDetails;
//    public static int tabPosition = -1;
    private static List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();


    /********  RATE LAYOUT STARTS ******/
    private LinearLayout rateNowContainer;
    private TextView totalRatings;
    private LinearLayout ratingsNoContainer;
    private TextView totalRatingsFigure;
    private LinearLayout ratingProgressBarContainer;
    private TextView averageRating;
    /********  RATE LAYOUT ENDS ******/

    /********  COUPON DIALOGUE STARTS ******/
    public static TextView couponTitle, couponExpiryDate, couponBody;
    private static RecyclerView couponRecyclerView;
    private static LinearLayout selectedCoupon;
    /********  COUPON DIALOGUE ENDS ******/

    private Button buyNowBtn;

    private FirebaseFirestore firebaseFirestore;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImageViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishListBtn = findViewById(R.id.add_to_wishlist_btn);

        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTablayout = findViewById(R.id.product_details_tablayout);

        rateNowContainer = findViewById(R.id.rate_now_container);
        buyNowBtn = findViewById(R.id.buy_now_btn);
        couponRedemptionBtn = findViewById(R.id.coupon_redemption_btn);
        productTitle = findViewById(R.id.product_title);
        averageRatingMiniView = findViewById(R.id.tv_product_rating_miniview);
        totalRatingsMiniView = findViewById(R.id.total_ratings_miniview);
        productPrice = findViewById(R.id.product_price);
        reducedPrice = findViewById(R.id.reduced_price);
        codIndicatorImageView = findViewById(R.id.cod_indicator_imageview);
        tvCodIndicator = findViewById(R.id.tv_cod_indicator);
        rewardTitle = findViewById(R.id.reward_title);
        rewardBody = findViewById(R.id.reward_body);
        productDetailsOnlyContainer = findViewById(R.id.product_details_containter);
        productDetailsTabsContainter = findViewById(R.id.product_details_tab_container);
        productOnlyDescriptionBody = findViewById(R.id.product_details_body);
        totalRatings = findViewById(R.id.total_ratings);
        ratingsNoContainer = findViewById(R.id.ratings_numbers_container);
        totalRatingsFigure = findViewById(R.id.total_ratings_figure);
        ratingProgressBarContainer = findViewById(R.id.ratings_progressbar_container);
        averageRating = findViewById(R.id.average_rating);

        firebaseFirestore = FirebaseFirestore.getInstance();

        final List<String> productImages = new ArrayList<>();

        firebaseFirestore.collection("PRODUCT").document("LT24c597v0NzV1FW4jPo").get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot documentSnapshot = task.getResult();

                            for (long x = 1; x < (long) documentSnapshot.get("no_of_product_images")+1; x++) {

                                productImages.add(documentSnapshot.get("product_image_" + x).toString());
                            }
                            ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                            productImageViewPager.setAdapter(productImagesAdapter);

                            productTitle.setText(documentSnapshot.get("product_title").toString());
                            averageRatingMiniView.setText(documentSnapshot.get("product_average_rating").toString());
                            totalRatingsMiniView.setText(" (" + (long)documentSnapshot.get("product_total_rating")+ ")rating");
                            productPrice.setText("Ghc "+ documentSnapshot.get("product_price").toString() + "/-");
                            reducedPrice.setText("Ghc "+ documentSnapshot.get("product_reduced_price").toString() + "/-");

                            if ((boolean) documentSnapshot.get("COD")) {
                                codIndicatorImageView.setVisibility(View.VISIBLE);
                                tvCodIndicator.setVisibility(View.VISIBLE);
                            } else {
                                codIndicatorImageView.setVisibility(View.INVISIBLE);
                                tvCodIndicator.setVisibility(View.INVISIBLE);
                            }
                            rewardTitle.setText((long)documentSnapshot.get("product_free_coupon")+ documentSnapshot.get("product_free_coupon_title").toString());
                            rewardBody.setText(documentSnapshot.get("producct_free_coupon_body").toString());

                            if ((boolean) documentSnapshot.get("use_tab_layout")) {
                                productDetailsOnlyContainer.setVisibility(View.VISIBLE);
                                productDetailsTabsContainter.setVisibility(View.GONE);
                                productDescription = documentSnapshot.get("product_description").toString();
//                                ProductSpecificationFragment.productSpecificationModelList = new ArrayList<>();
                                productOtherDetails = documentSnapshot.get("product_other_details").toString();

                                for (long x = 1; x < (long) documentSnapshot.get("product_total_spec_titles") + 1; x++) {
                                    productSpecificationModelList.add(new ProductSpecificationModel(0,documentSnapshot.get("product_spec_title_"+x).toString()));
                                    for (long y = 1; y < (long) documentSnapshot.get("product_spec_title_" + x + "_total_fields") + 1; y++) {

                                        productSpecificationModelList.add(new ProductSpecificationModel(1,documentSnapshot.get("product_spec_title_"+x+"_field_"+y+"_name").toString(),
                                                documentSnapshot.get("product_spec_title_"+x+"_field_"+y+"_value").toString() ));

                                    }
                                }
                            } else {
                                productDetailsOnlyContainer.setVisibility(View.GONE);
                                productDetailsTabsContainter.setVisibility(View.VISIBLE);
                                productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());
                            }

                            totalRatings.setText((long)documentSnapshot.get("product_total_rating") + "ratings");

                            for (int x = 0; x < 5; x++) {
                                TextView rating = (TextView) ratingsNoContainer.getChildAt(x);
                                rating.setText(String.valueOf((long)documentSnapshot.get((5 - x) + "_star")));

                                ProgressBar progressBar = (ProgressBar) ratingProgressBarContainer.getChildAt(x);
                                int maxProgress = Integer.parseInt(String.valueOf((long)documentSnapshot.get("product_total_rating")));
                                progressBar.setMax(maxProgress);
                                progressBar.setProgress(Integer.parseInt(String.valueOf((long)documentSnapshot.get((5 - x) + "_star"))));
                            }
                            totalRatingsFigure.setText(String.valueOf((long)documentSnapshot.get("product_total_rating")));
                            averageRating.setText(documentSnapshot.get("product_average_rating").toString());
                            productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTablayout.getTabCount(), productDescription, productOtherDetails, productSpecificationModelList));

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(ProductDetailsActivity.this,error,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );



        viewPagerIndicator.setupWithViewPager(productImageViewPager,true);
        addToWishList();
        productDetailSetAdapter();
        rateNow();


    }


    private void addToWishList() {
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }

            }
        });
    }


    /********  RATING NOW STARTS ******/

    private void rateNow() {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {

            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRating(starPosition);
                }
            });

        }
        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deliveryIntent = new Intent(ProductDetailsActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });
    }

    /********  RATING NOW ENDS ******/

    private void setRating(int starPosition) {

        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {

            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if (x <= starPosition) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }

    }

    private void productDetailSetAdapter() {
        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTablayout));
        productDetailsTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                tabPosition = tab.getPosition();
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        } else if (id == R.id.main_cart_icon) {
            //todo: cart
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
