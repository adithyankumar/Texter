package com.techiedeveloper.texter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;



/**
 * Created by adhi on 2/1/17.
 */

public  class Texter {

    private TextView mTextView;
    private SpannableString mText;
    private int flag;
    private OnHyperLinkClickListener onHyperLinkClickListener;
    private boolean underLine;
    private float rSize=-1;
    private enum Type{
        BACKGROUND,FOREGROUND,UNDERLINE,STYLE,SUBSCRIPT,SUPERSCRIPT,STRIKEOUT,HYPERLINK,IMAGE
    }

    private Texter(TextView textView, String text) {
        mTextView=textView;
        mText=new SpannableString(text);
        flag=Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
    }

    public static Texter into(TextView textView, String text){
        return new Texter(textView,text);
    }

    public Texter setTypeFace(Typeface typeFace){
        mTextView.setTypeface(typeFace);
        return this;
    }

    public Texter setRelativeSize(float size){
        rSize=size;
        return this;
    }

    public Texter setFlag(int flag){
        this.flag = flag;
        return this;
    }
    public Texter background(@ColorRes final int color, int start, int end){
        try{
            span(new BackgroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, -1);
        }catch (Exception e){
            e.printStackTrace();
            span(new BackgroundColorSpan((int)color), start, end, -1);
        }
        return this;
    }
    public Texter background(@ColorRes int color, int start, int end,float size){
        try{
            span(new BackgroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, size);
        }catch (Exception e){
            e.printStackTrace();
            span(new BackgroundColorSpan((int)color), start, end, size);
        }
        return this;
    }
    public Texter background(@ColorRes int color,String... string){
        for (String s : string) {
            repeat(Type.BACKGROUND,color,s);
        }
        return this;
    }
    public Texter background(@ColorRes int color, float size,String... string){
        for (String str : string) {
            repeat(Type.BACKGROUND, color, str,size);
        }
        return this;
    }
    public Texter background(String color, int start, int end){
        span(new BackgroundColorSpan(Color.parseColor(color)), start, end, -1);
        return this;
    }
    public Texter background(String color, int start, int end, float size){
        span(new BackgroundColorSpan(Color.parseColor(color)), start, end, size);
        return this;
    }
    public Texter background(String color,String... string){
        for (String str : string) {
            repeat(Type.BACKGROUND,color,str);
        }
        return this;
    }
    public Texter background(String color, float size,String... string){
        for (String str : string) {
            repeat(Type.BACKGROUND,color,str,size);
        }
        return this;
    }


    public Texter foreground(@ColorRes int color, int start, int end){
        try{
            span(new ForegroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, -1);
        }catch (Exception e){
            e.printStackTrace();
            span(new ForegroundColorSpan((int)color), start, end, -1);
        }
        return this;
    }
    public Texter foreground(@ColorRes int color, int start, int end,float size){
        try{
            span(new ForegroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, size);
        }catch (Exception e){
            e.printStackTrace();
            span(new ForegroundColorSpan((int)color), start, end, size);
        }
        return this;
    }
    public Texter foreground(@ColorRes int color,String... string){
        for (String s : string) {
            repeat(Type.FOREGROUND,color,s);
        }
        return this;
    }
    public Texter foreground(@ColorRes int color, float size,String... string){
        for (String str : string) {
            repeat(Type.FOREGROUND, color, str,size);
        }
        return this;
    }
    public Texter foreground(String color, int start, int end){
        span(new ForegroundColorSpan(Color.parseColor(color)), start, end, -1);
        return this;
    }
    public Texter foreground(String color, int start, int end, float size){
        span(new ForegroundColorSpan(Color.parseColor(color)), start, end, size);
        return this;
    }
    public Texter foreground(String color,String... string){
        for (String str : string) {
            repeat(Type.FOREGROUND,color,str);
        }
        return this;
    }
    public Texter foreground(String color, float size,String... string){
        for (String str : string) {
            repeat(Type.FOREGROUND,color,str,size);
        }
        return this;
    }


    public Texter hyperLink(final OnHyperLinkClickListener onHyperLinkClickListener, int start, int end, final boolean underLine){
        span(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                onHyperLinkClickListener.onClick(view);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(underLine);
            }
        },start, end, -1);
        return this;
    }
    public Texter hyperLink(final OnHyperLinkClickListener onHyperLinkClickListener, int start, int end,
                            final boolean underLine,float size){
        span(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                onHyperLinkClickListener.onClick(view);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(underLine);
            }
        },start, end, size);
        return this;
    }
    public Texter hyperLink(final OnHyperLinkClickListener onHyperLinkClickListener, final boolean underLine, String... string){
        for (String str : string) {
            this.onHyperLinkClickListener=onHyperLinkClickListener;
            this.underLine=underLine;
            repeat(Type.HYPERLINK,str);
        }
        return this;
    }
    public Texter hyperLink(final OnHyperLinkClickListener onHyperLinkClickListener, final boolean underLine,
                            float size, String... string){
        for (String str : string) {
            this.onHyperLinkClickListener=onHyperLinkClickListener;
            this.underLine=underLine;
            repeat(Type.HYPERLINK,str,size);
        }
        return this;
    }

    public Texter strikeOut(int start, int end){
        span(new StrikethroughSpan(), start, end, -1);
        return this;
    }
    public Texter strikeOut(String... string){
        for (String str : string) {
            repeat(Type.STRIKEOUT,str);
        }
        return this;
    }
    public Texter strikeOut(int start, int end, float size){
        span(new StrikethroughSpan(), start, end, size);
        return this;
    }
    public Texter strikeOut(float size,String... string){
        for (String str : string) {
            repeat(Type.STRIKEOUT,str,size);
        }
        return this;
    }


    public Texter underLine(int start, int end){
        span(new UnderlineSpan(), start, end, -1);
        return this;
    }
    public Texter underLine(int start, int end, float size){
        span(new UnderlineSpan(), start, end, size);
        return this;
    }
    public Texter underLine(String... string){
        for (String str : string) {
            repeat(Type.UNDERLINE,str);
        }
        return this;
    }
    public Texter underLine(float size,String... string){
        for (String str : string) {
            repeat(Type.UNDERLINE,str, size);
        }
        return this;
    }

    public Texter imageSpan(@DrawableRes int drawable, int start, int end){
        span(new ImageSpan(mTextView.getContext(),drawable), start, end,-1);
        return this;
    }
    public Texter imageSpan(@DrawableRes int drawable, String... string){
        for (String str : string)
            repeat(Type.IMAGE,drawable,str);
        return this;
    }
    public Texter imageSpan(@DrawableRes int drawable, int start, int end,float size){
        span(new ImageSpan(mTextView.getContext(),drawable), start, end,size);
        return this;
    }
    public Texter imageSpan(@DrawableRes int drawable,float size, String... string){
       for (String str : string)
            repeat(Type.IMAGE,drawable,str,size);
        return this;
    }
    public Texter imageSpan(Drawable drawable, int start, int end){
        span(new ImageSpan(drawable), start, end,-1);
        return this;
    }
    public Texter imageSpan(Drawable drawable, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(drawable), start, end,flag);
        }
        return this;
    }
    public Texter imageSpan(Drawable drawable, int start, int end,float size){
        mText.setSpan(new ImageSpan(drawable), start, end,flag);
        mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        return this;
    }
    public Texter imageSpan(Drawable drawable,float size, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(drawable), start, end,flag);
            mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        }
        return this;
    }
    public Texter imageSpan(Bitmap bitmap, int start, int end){
        mText.setSpan(new ImageSpan(bitmap), start, end,flag);
        return this;
    }
    public Texter imageSpan(Bitmap bitmap, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(bitmap), start, end,flag);
        }
        return this;
    }
    public Texter imageSpan(Bitmap bitmap, int start, int end,float size){
        mText.setSpan(new ImageSpan(bitmap), start, end,flag);
        mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        return this;
    }
    public Texter imageSpan(Bitmap bitmap,float size, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(bitmap), start, end,flag);
            mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        }
        return this;
    }

    public Texter superScript(int start, int end){
        span(new SuperscriptSpan(), start, end, -1);
        return this;
    }
    public Texter superScript(int start, int end, float size){
        span(new SuperscriptSpan(), start, end, size);
        return this;
    }
    public Texter superScript(String... string){
        for (String str : string) {
            repeat(Type.SUPERSCRIPT,str);
        }
        return this;
    }
    public Texter superScript(float size, String... string){
        for (String str : string) {
            repeat(Type.SUPERSCRIPT,str,size);
        }
        return this;
    }

    public Texter subScript(int start, int end){
        span(new SubscriptSpan(), start, end, -1);
        return this;
    }
    public Texter subScript(int start, int end, float size){
        span(new SubscriptSpan(), start, end, size);
        return this;
    }
    public Texter subScript(String... string){
        for (String str : string) {
            repeat(Type.SUBSCRIPT,str);
        }
        return this;
    }
    public Texter subScript(float size, String... string){
        for (String str : string) {
            repeat(Type.SUBSCRIPT,str,size);
        }
        return this;
    }


    public Texter textStyle(int style,int start, int end){
        span(new StyleSpan(style), start, end, -1);
        return this;
    }
    public Texter textStyle(int style,int start, int end, float size){
        span(new StyleSpan(style), start, end, size);
        return this;
    }
    public Texter textStyle(int style,String... string){
        for (String str : string) {
            repeat(Type.STYLE,style,str);
        }
        return this;
    }
    public Texter textStyle(int style,float size,String... string){
        for (String str : string) {
            repeat(Type.STYLE,style,str, size);
        }
        return this;
    }

    private void span(Object span, int start, int end,float size){
        checkException(start,end);
        mText.setSpan(span, start, end, flag);
        if (span instanceof ClickableSpan)
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        if (rSize !=-1)
            mText.setSpan(new RelativeSizeSpan(rSize), start, end, flag);
        if (size !=-1)
            mText.setSpan(new RelativeSizeSpan(size), start, end, flag);

    }

    private void checkException(int start, int end){
        if (start < 0 || end <= 0)
            throw new IndexOutOfBoundsException(" Texter : start < 0 or end <=0 ");
        if (start > end)
            throw new IndexOutOfBoundsException(" Texter : start > end ");
        if (end > mText.length())
            throw new IndexOutOfBoundsException(" Texter : end > text length ");
        if (start == end)
            throw new IndexOutOfBoundsException(" Texter : start == end ");
    }
    private void repeat(Type type, String string){
        repeat(type,-1,string);
    }
    private void repeat(Type type, String string, float size){
        repeat(type,-1,string, size);
    }
    private void repeat(Type type, String id,String string){
        repeat(type,Color.parseColor(id),string);
    }
    private void repeat(Type type, String id,String string,float size){
        repeat(type,Color.parseColor(id),string,size);
    }
    private void repeat(Type type, int id,String string){
        int len = string.length();
        if (len > 0) {
            int start = mText.toString().indexOf(string);
            int end;
            while (start != -1) {
                end = start+len;
                if(id==-1)
                    type(type, start, end);
                else
                    type(type,id, start, end);
                start = mText.toString().indexOf(string,end);
            }
        }
    }
    private void repeat(Type type, int id,String string, float size){
        int len = string.length();
        if (len > 0) {
            int start = mText.toString().indexOf(string);
            int end;
            while (start != -1) {
                end = start+len;
                if(id==-1)
                    type(type, start, end,size);
                else
                    type(type,id, start, end,size);
                start = mText.toString().indexOf(string,end);
            }
        }
    }


    private void type(Type type, int start, int end){
        switch (type){
            // BACKGROUND,FOREGROUND,UNDERLINE,STYLE,SUBSCRIPT,SUPERSCRIPT,STRIKEOUT
            case UNDERLINE:
                underLine(start, end);
                break;
            case SUBSCRIPT:
                subScript(start, end);
                break;
            case SUPERSCRIPT:
                superScript(start, end);
                break;
            case STRIKEOUT:
                strikeOut(start, end);
                break;
            case HYPERLINK:
                if (onHyperLinkClickListener != null)
                    hyperLink(onHyperLinkClickListener,start,end,underLine);
                break;
            default:
                break;

        }
    }
    private void type(Type type, int start, int end, float size){
        switch (type){
            // BACKGROUND,FOREGROUND,UNDERLINE,STYLE,SUBSCRIPT,SUPERSCRIPT,STRIKEOUT
            case UNDERLINE:
                underLine(start, end, size);
                break;
            case SUBSCRIPT:
                subScript(start, end, size);
                break;
            case SUPERSCRIPT:
                superScript(start, end, size);
                break;
            case STRIKEOUT:
                strikeOut(start, end, size);
                break;
            case HYPERLINK:
                if (onHyperLinkClickListener != null)
                    hyperLink(onHyperLinkClickListener,start,end,underLine);
                break;
            default:
                break;

        }
    }
    private void type(Type type,int id, int start, int end){
        switch (type){
            // BACKGROUND,FOREGROUND,UNDERLINE,STYLE,SUBSCRIPT,SUPERSCRIPT,STRIKEOUT
            case BACKGROUND:
                background(id,start, end);
                break;
            case FOREGROUND:
                foreground(id,start, end);
                break;
            case STYLE:
                textStyle(id,start, end);
                break;
            case IMAGE:
                imageSpan(id,start, end);
            default:
                break;

        }
    }
    private void type(Type type,int id, int start, int end, float size){
        switch (type){
            // BACKGROUND,FOREGROUND,UNDERLINE,STYLE,SUBSCRIPT,SUPERSCRIPT,STRIKEOUT
            case BACKGROUND:
                background(id,start, end, size);
                break;
            case FOREGROUND:
                foreground(id,start, end, size);
                break;
            case STYLE:
                textStyle(id,start, end, size);
                break;
            case IMAGE:
                imageSpan(id,start, end,size);
            default:
                break;

        }
    }

    public Texter build(){
        mTextView.setText(mText);
        return this;
    }
}
