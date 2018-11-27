package com.yhg.pickup.mes;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;
import com.yhg.pickup.utils.SystemUtils;
import com.yhg.pickup.widght.MyGridView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/15.
 */

public class MesFm extends BaseFragment implements View.OnClickListener {

    private LayoutInflater mInflater;
    RecyclerView ry ;
    private MyGridView mLettersLayout;
    private ArrayList<SpringAnimation> mLetterAnims;
    private ArrayList<SpringAnimation> mLetterAnims2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.fm_mes, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {


    }

    private void initView(View view) {
        mLettersLayout = view.findViewById(R.id.link_page_gridview);
        MyAdapter myAdapter =new MyAdapter();
        mLettersLayout.setAdapter(myAdapter);
        mLettersLayout.post(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });

        }

    private void init() {

        final float screenHeight = SystemUtils.getScreenHeight(mContext);
        // letters about 'Converse'
        mLetterAnims = new ArrayList<>();
        for (int i = 0; i < mLettersLayout.getChildCount(); i++) {
            final View letterView = mLettersLayout.getChildAt(i);
//            letterView.setVisibility(View.INVISIBLE);
            letterView.setTranslationY(screenHeight);
            SpringAnimation letterAnimY = new SpringAnimation(letterView, SpringAnimation.TRANSLATION_Y, 0);
            letterAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
            letterAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
//            letterAnimY.setStartValue(screenHeight);
            letterAnimY.setStartVelocity(10);
            letterAnimY.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
//                    letterView.setVisibility(View.VISIBLE);
                }
            });
            mLetterAnims.add(letterAnimY);
        }

        mLetterAnims2 = new ArrayList<>();
        for (int i = mLettersLayout.getChildCount()-1; i >= 0; i--) {
            final View letterView = mLettersLayout.getChildAt(i);
            SpringAnimation letterAnimY = new SpringAnimation(letterView, SpringAnimation.TRANSLATION_Y, screenHeight);
            letterAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            letterAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
            letterAnimY.setStartVelocity(0);
            letterAnimY.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    SpringAnimation letterAnimY = new SpringAnimation(letterView, SpringAnimation.TRANSLATION_Y, screenHeight);
                    letterAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                    letterAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
                    letterAnimY.setStartVelocity(0);
                    letterAnimY.start();
                }
            });
            mLetterAnims2.add(letterAnimY);
        }
        startAnim();
//        view.findViewById(R.id.sign_up_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startAnim2();
//            }
//        });

//        // text about 'Native messaging'
//        mDescTitleTextView = (TextView) view.findViewById(R.id.desc_title_textview);
//        mDescTitleTextView.setTranslationY(500f);
//        mDescTitleTextView.setAlpha(0f);
//        final SpringAnimation descTitleAnimY = new SpringAnimation(mDescTitleTextView, DynamicAnimation.TRANSLATION_Y, 0);
//        descTitleAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//        descTitleAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
//
//        final ValueAnimator descTitleAlphaAnim = ObjectAnimator.ofFloat(0f, 1f);
//        descTitleAlphaAnim.setDuration(300);
//        descTitleAlphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                mDescTitleTextView.setAlpha((Float) valueAnimator.getAnimatedValue());
//            }
//        });

//        // the button of sign up
//        mSignUpBtn = (Button) view.findViewById(R.id.sign_up_btn);
//        mSignUpBtn.setTranslationY(500f);
//        final SpringAnimation signUpBtnAnimY = new SpringAnimation(mSignUpBtn, SpringAnimation.TRANSLATION_Y, 0);
//        signUpBtnAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//        signUpBtnAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
//
//        // the bottom text about 'Have an account? sign in'
//        mSignInLayout = (LinearLayout) view.findViewById(R.id.signin_layout);
//        mSignInLayout.setTranslationY(500f);
//        final SpringAnimation signInLayoutAnimY = new SpringAnimation(mSignInLayout, SpringAnimation.TRANSLATION_Y, 0);
//        signInLayoutAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//        signInLayoutAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);

//
//        // top logo by left
//        mLeftLogoImg = (ImageView) view.findViewById(R.id.left_logo_imageview);
//        mLeftLogoImg.setTranslationY(400f);
//        mLeftLogoImg.setAlpha(0f);
//        final SpringAnimation leftLogoAnimY = new SpringAnimation(mLeftLogoImg, SpringAnimation.TRANSLATION_Y, 0);
//        leftLogoAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//        leftLogoAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
//        leftLogoAnimY.setStartVelocity(-2000);
//
//        // top logo by right
//        mRightLogoImg = (ImageView) view.findViewById(R.id.right_logo_imageview);
//        mRightLogoImg.setTranslationY(400f);
//        mRightLogoImg.setAlpha(0f);
//        final SpringAnimation rightLogoAnimY = new SpringAnimation(mRightLogoImg, SpringAnimation.TRANSLATION_Y, 0);
//        rightLogoAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//        rightLogoAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
//        rightLogoAnimY.setStartVelocity(-2000);

//        final ValueAnimator logoAlphaAnim = ObjectAnimator.ofFloat(0f, 1f);
//        logoAlphaAnim.setDuration(600);
//        logoAlphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                mLeftLogoImg.setAlpha((Float) valueAnimator.getAnimatedValue());
//                mRightLogoImg.setAlpha((Float) valueAnimator.getAnimatedValue());
//            }
//        });

//        mRightLogoImg.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                leftLogoAnimY.start();
//                mRightLogoImg.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        rightLogoAnimY.start();
//                    }
//                }, 150);
//
//                mDescTitleTextView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        descTitleAlphaAnim.setStartDelay(100);
//                        descTitleAlphaAnim.start();
//
//                        descTitleAnimY.start();
//                        signUpBtnAnimY.start();
//                        signInLayoutAnimY.start();
//                    }
//                }, 300);
//
//                for (final SpringAnimation letterAnim : mLetterAnims) {
//                    mLettersLayout.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            letterAnim.start();
//                        }
//                    }, 12 * mLetterAnims.indexOf(letterAnim));
//                }
//
////                logoAlphaAnim.start();
//            }
//        }, 1000);
    }

    private void startAnim2() {
        for (final SpringAnimation letterAnim : mLetterAnims2) {
            mLettersLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    letterAnim.start();
                }
            }, 50 * mLetterAnims2.indexOf(letterAnim));
        }
    }

    private void startAnim() {
        for (final SpringAnimation letterAnim : mLetterAnims) {
            mLettersLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    letterAnim.start();
                }
            }, 50 * mLetterAnims.indexOf(letterAnim));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.channel_mannager:

                break;
                default:
                    break;
        }
    }

    private class MyAdapter  extends BaseAdapter{

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView= LayoutInflater.from(mContext).inflate(R.layout.link_gridview_item,null,false);
            }
            return convertView;
        }
    }
}
