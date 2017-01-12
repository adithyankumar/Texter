package com.techiedeveloper.texter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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

    /**
     * @param textView target textview
     * @param text string to be displayed
     * @return
     */
    public static Texter into(TextView textView, String text){
        return new Texter(textView,text);
    }

    /**
     * @param typeFace set typeface for the whole text
     * @return
     */
    public Texter setTypeFace(Typeface typeFace){
        mTextView.setTypeface(typeFace);
        return this;
    }

    /**
     * @param size set size of text
     * @return
     */
    public Texter setRelativeSize(float size){
        rSize=size;
        return this;
    }

    /**
     * @param flag
     * @return
     */
    public Texter setFlag(int flag){
        this.flag = flag;
        return this;
    }

    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param start starting position of text
     * @param end ending position of text
     * @return set background color for specified position of text
     */
    public Texter background( int color, int start, int end){
        try{
            span(new BackgroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, -1);
        }catch (Exception e){
            e.printStackTrace();
            span(new BackgroundColorSpan(color), start, end, -1);
        }
        return this;
    }


    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param start starting position of text
     * @param end ending position of text
     * @param size set the size of text
     * @return set background color for specified position of text
     */
    public Texter background(int color, int start, int end,float size){
        try{
            span(new BackgroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, size);
        }catch (Exception e){
            e.printStackTrace();
            span(new BackgroundColorSpan(color), start, end, size);
        }
        return this;
    }

    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param string pass the specific strings to apply above color
     * @return set background color for specified text
     */
    public Texter background(int color,String... string){
        for (String s : string) {
            repeat(Type.BACKGROUND,color,s);
        }
        return this;
    }

    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param size set size of text
     * @param string pass the specific strings to apply above color
     * @return set background color for specified text
     */

    public Texter background(int color, float size,String... string){
        for (String str : string) {
            repeat(Type.BACKGROUND, color, str,size);
        }
        return this;
    }

    /**
     * @param color pass hexa color code
     * @param start starting position of text
     * @param end ending position of text
     * @return set background color for specified position of text
     */
    public Texter background(String color, int start, int end){
        span(new BackgroundColorSpan(Color.parseColor(color)), start, end, -1);
        return this;
    }
    /**
     * @param color pass hexa color code
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size of text
     * @return set background color for specified position of text
     */
    public Texter background(String color, int start, int end, float size){
        span(new BackgroundColorSpan(Color.parseColor(color)), start, end, size);
        return this;
    }

    /**
     * @param color pass hexa color code
     * @param string pass the specific strings to apply above color
     * @return set background color for specified text
     */
    public Texter background(String color,String... string){
        for (String str : string) {
            repeat(Type.BACKGROUND,color,str);
        }
        return this;
    }

    /**
     * @param color pass hexa color code
     * @param size set size of text
     * @param string pass the specific strings to apply above color
     * @return set background color for specified text
     */

    public Texter background(String color, float size,String... string){
        for (String str : string) {
            repeat(Type.BACKGROUND,color,str,size);
        }
        return this;
    }

    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param start starting position of text
     * @param end ending position of text
     * @return set foreground color for specified position of text
     */
    public Texter foreground(int color, int start, int end){
        try{
            span(new ForegroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, -1);
        }catch (Exception e){
            e.printStackTrace();
            span(new ForegroundColorSpan(color), start, end, -1);
        }
        return this;
    }

    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param start starting position of text
     * @param end ending position of text
     * @param size set the size of text
     * @return set foreground color for specified position of text
     */

    public Texter foreground(int color, int start, int end,float size){
        try{
            span(new ForegroundColorSpan(ContextCompat.getColor(mTextView.getContext(),color)), start, end, size);
        }catch (Exception e){
            e.printStackTrace();
            span(new ForegroundColorSpan(color), start, end, size);
        }
        return this;
    }

    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param string pass the specific strings to apply above color
     * @return set foreground color for specified text
     */

    public Texter foreground(int color,String... string){
        for (String s : string) {
            repeat(Type.FOREGROUND,color,s);
        }
        return this;
    }


    /**
     * @param color pass color code stored in resource / Color.{name}
     * @param size set size of text
     * @param string pass the specific strings to apply above color
     * @return set foreground color for specified text
     */
    public Texter foreground(int color, float size,String... string){
        for (String str : string) {
            repeat(Type.FOREGROUND, color, str,size);
        }
        return this;
    }
    /**
     * @param color pass hexa color code
     * @param start starting position of text
     * @param end ending position of text
     * @return set foreground color for specified position of text
     */

    public Texter foreground(String color, int start, int end){
        span(new ForegroundColorSpan(Color.parseColor(color)), start, end, -1);
        return this;
    }

    /**
     * @param color pass hexa color code
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size of text
     * @return set foreground color for specified position of text
     */

    public Texter foreground(String color, int start, int end, float size){
        span(new ForegroundColorSpan(Color.parseColor(color)), start, end, size);
        return this;
    }

    /**
     * @param color pass hexa color code
     * @param string pass the specific strings to apply above color
     * @return set foreground color for specified text
     */

    public Texter foreground(String color,String... string){
        for (String str : string) {
            repeat(Type.FOREGROUND,color,str);
        }
        return this;
    }

    /**
     * @param color pass hexa color code
     * @param size set size of text
     * @param string pass the specific strings to apply above color
     * @return set foreground color for specified text
     */
    public Texter foreground(String color, float size,String... string){
        for (String str : string) {
            repeat(Type.FOREGROUND,color,str,size);
        }
        return this;
    }

    /**
     * @param onHyperLinkClickListener perform action on clicking the text
     * @param start starting position of text
     * @param end ending position of text
     * @param underLine set true show underline, else hide
     * @return set hyperlink for specified position of text
     */
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

    /**
     * @param onHyperLinkClickListener perform action on clicking the text
     * @param start starting position of text
     * @param end ending position of text
     * @param underLine set true show underline, else hide
     * @param size set size of text
     * @return set hyperlink for specified position of text
     */
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

    /**
     * @param onHyperLinkClickListener perform action on clicking the text
     * @param underLine set true show underline, else hide
     * @param string pass the specific strings to set hyperlink
     * @return set hyperlink for specified text
     */
    public Texter hyperLink(final OnHyperLinkClickListener onHyperLinkClickListener, final boolean underLine, String... string){
        for (String str : string) {
            this.onHyperLinkClickListener=onHyperLinkClickListener;
            this.underLine=underLine;
            repeat(Type.HYPERLINK,str);
        }
        return this;
    }
    /**
     * @param onHyperLinkClickListener perform action on clicking the text
     * @param underLine set true show underline, else hide
     * @param string pass the specific strings to set hyperlink
     * @param size set size of text
     * @return set hyperlink for specified text
     */
    public Texter hyperLink(final OnHyperLinkClickListener onHyperLinkClickListener, final boolean underLine,
                            float size, String... string){
        for (String str : string) {
            this.onHyperLinkClickListener=onHyperLinkClickListener;
            this.underLine=underLine;
            repeat(Type.HYPERLINK,str,size);
        }
        return this;
    }


    /**
     * @param start starting position of text
     * @param end ending position of text
     * @return strike out for specified position of text
     */

    public Texter strikeOut(int start, int end){
        span(new StrikethroughSpan(), start, end, -1);
        return this;
    }
    /**
     * @param string  pass the specific strings to strikeout
     * @return strike out for specified text
     */
    public Texter strikeOut(String... string){
        for (String str : string) {
            repeat(Type.STRIKEOUT,str);
        }
        return this;
    }

    /**
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size for text
     * @return strike out for specified position of text
     */

    public Texter strikeOut(int start, int end, float size){
        span(new StrikethroughSpan(), start, end, size);
        return this;
    }

    /**
     * @param string  pass the specific strings to strikeout
     * @param size set size for text
     * @return strike out for specified text
     */
    public Texter strikeOut(float size,String... string){
        for (String str : string) {
            repeat(Type.STRIKEOUT,str,size);
        }
        return this;
    }

    /**
     * @param start starting position of text
     * @param end ending position of text
     * @return underline for specified position of text
     */

    public Texter underLine(int start, int end){
        span(new UnderlineSpan(), start, end, -1);
        return this;
    }

    /**
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size for text
     * @return underline for specified position of text
     */

    public Texter underLine(int start, int end, float size){
        span(new UnderlineSpan(), start, end, size);
        return this;
    }

    /**
     * @param string  pass the specific strings to underline
     * @return underline for specified text
     */

    public Texter underLine(String... string){
        for (String str : string) {
            repeat(Type.UNDERLINE,str);
        }
        return this;
    }

    /**
     * @param string  pass the specific strings to underline
     * @param size set size for text
     * @return underline for specified text
     */

    public Texter underLine(float size,String... string){
        for (String str : string) {
            repeat(Type.UNDERLINE,str, size);
        }
        return this;
    }


    /**
     * @param drawable drawable image from resource
     * @param start starting position of text
     * @param end ending position of text
     * @return insert image at specified positions of text
     */


    public Texter imageSpan(@DrawableRes int drawable, int start, int end){
        span(new ImageSpan(mTextView.getContext(),drawable), start, end,-1);
        return this;
    }


    /**
     * @param drawable drawable image from resource
     * @param string pass the string where you want to insert image
     * @return insert image at specified text
     */


    public Texter imageSpan(@DrawableRes int drawable, String... string){
        for (String str : string)
            repeat(Type.IMAGE,drawable,str);
        return this;
    }

    /**
     * @param drawable drawable image from resource
     * @param start starting position of text
     * @param end ending position of text
     * @param size  set size for text
     * @return insert image at specified positions of text
     */


    public Texter imageSpan(@DrawableRes int drawable, int start, int end,float size){
        span(new ImageSpan(mTextView.getContext(),drawable), start, end,size);
        return this;
    }


    /**
     * @param drawable drawable image from resource
     * @param size set size of text
     * @param string pass the string where you want to insert image
     * @return insert image at specified text
     */


    public Texter imageSpan(@DrawableRes int drawable,float size, String... string){
       for (String str : string)
            repeat(Type.IMAGE,drawable,str,size);
        return this;
    }



    /**
     * @param drawable drawable image
     * @param start starting position of text
     * @param end ending position of text
     * @return insert image at specified positions of text
     */
    public Texter imageSpan(Drawable drawable, int start, int end){
        span(new ImageSpan(drawable), start, end,-1);
        return this;
    }


    /**
     * @param drawable drawable image
     * @param string pass the string where you want to insert image
     * @return insert image at specified text
     */
    public Texter imageSpan(Drawable drawable, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(drawable), start, end,flag);
        }
        return this;
    }


    /**
     * @param drawable drawable image
     * @param start starting position of text
     * @param end ending position of text
     * @param size  set size for text
     * @return insert image at specified positions of text
     */

    public Texter imageSpan(Drawable drawable, int start, int end,float size){
        mText.setSpan(new ImageSpan(drawable), start, end,flag);
        mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        return this;
    }

    /**
     * @param drawable drawable image
     * @param size set size of text
     * @param string pass the string where you want to insert image
     * @return insert image at specified text
     */

    public Texter imageSpan(Drawable drawable,float size, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(drawable), start, end,flag);
            mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        }
        return this;
    }

    /**
     * @param bitmap bitmap image
     * @param start starting position of text
     * @param end ending position of text
     * @return insert image at specified positions of text
     */
    public Texter imageSpan(Bitmap bitmap, int start, int end){
        mText.setSpan(new ImageSpan(bitmap), start, end,flag);
        return this;
    }

    /**
     * @param bitmap bitmap image
     * @param string pass the string where you want to insert image
     * @return insert image at specified text
     */

    public Texter imageSpan(Bitmap bitmap, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(bitmap), start, end,flag);
        }
        return this;
    }

    /**
     * @param bitmap bitmap image
     * @param start starting position of text
     * @param end ending position of text
     * @param size  set size for text
     * @return insert image at specified positions of text
     */
    public Texter imageSpan(Bitmap bitmap, int start, int end,float size){
        mText.setSpan(new ImageSpan(bitmap), start, end,flag);
        mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        return this;
    }

    /**
     * @param bitmap bitmap image
     * @param size set size of text
     * @param string pass the string where you want to insert image
     * @return insert image at specified text
     */
    public Texter imageSpan(Bitmap bitmap,float size, String... string){
        for (String str : string) {
            int start=mText.toString().indexOf(str);
            int end=start+str.length();
            mText.setSpan(new ImageSpan(bitmap), start, end,flag);
            mText.setSpan(new RelativeSizeSpan(size),start,end,flag);
        }
        return this;
    }


    /**
     * @param start starting position of text
     * @param end ending position of text
     * @return set superscript for specified position of text
     */

    public Texter superScript(int start, int end){
        span(new SuperscriptSpan(), start, end, -1);
        return this;
    }

    /**
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size of text
     * @return set superscript for specified position of text
     */

    public Texter superScript(int start, int end, float size){
        span(new SuperscriptSpan(), start, end, size);
        return this;
    }

    /**
     * @param string pass the string to apply superscript
     * @return set superscript for specified text
     */

    public Texter superScript(String... string){
        for (String str : string) {
            repeat(Type.SUPERSCRIPT,str);
        }
        return this;
    }

    /**
     * @param size set size of text
     * @param string pass the string to apply superscript
     * @return set superscript for specified text
     */

    public Texter superScript(float size, String... string){
        for (String str : string) {
            repeat(Type.SUPERSCRIPT,str,size);
        }
        return this;
    }


    /**
     * @param start starting position of text
     * @param end ending position of text
     * @return set subscript for specified position of text
     */

    public Texter subScript(int start, int end){
        span(new SubscriptSpan(), start, end, -1);
        return this;
    }

    /**
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size of text
     * @return set subscript for specified position of text
     */

    public Texter subScript(int start, int end, float size){
        span(new SubscriptSpan(), start, end, size);
        return this;
    }

    /**
     * @param string pass the string to apply subscript
     * @return set subscript for specified text
     */
    public Texter subScript(String... string){
        for (String str : string) {
            repeat(Type.SUBSCRIPT,str);
        }
        return this;
    }
    /**
     * @param size set size of text
     * @param string pass the string to apply subscript
     * @return set subscript for specified text
     */

    public Texter subScript(float size, String... string){
        for (String str : string) {
            repeat(Type.SUBSCRIPT,str,size);
        }
        return this;
    }

    /**
     * @param style style {Typeface.BOLD ,Typeface.BOLD_ITALIC, Typeface.ITALIC}
     * @param start starting position of text
     * @param end ending position of text
     * @return set style for specified position of text
     */
    public Texter textStyle(int style,int start, int end){
        span(new StyleSpan(style), start, end, -1);
        return this;
    }

    /**
     * @param style style {Typeface.BOLD ,Typeface.BOLD_ITALIC, Typeface.ITALIC}
     * @param start starting position of text
     * @param end ending position of text
     * @param size set size of text
     * @return set style for specified position of text
     */
    public Texter textStyle(int style,int start, int end, float size){
        span(new StyleSpan(style), start, end, size);
        return this;
    }

    /**
     * @param style style {Typeface.BOLD ,Typeface.BOLD_ITALIC, Typeface.ITALIC}
     * @param string pass the string to apply style
     * @return set style for specified text
     */

    public Texter textStyle(int style,String... string){
        for (String str : string) {
            repeat(Type.STYLE,style,str);
        }
        return this;
    }


    /**
     * @param style style {Typeface.BOLD ,Typeface.BOLD_ITALIC, Typeface.ITALIC}
     * @param size set size of text
     * @param string pass the string to apply style
     * @return set style for specified text
     */
    public Texter textStyle(int style,float size,String... string){
        for (String str : string) {
            repeat(Type.STYLE,style,str, size);
        }
        return this;
    }

    /**
     * @param span type of span
     * @param start starting position of text
     * @param end ending position of text
     * @param size size for the specified position of text
     * @return set span for specified position of text
     */
    private void span(Object span, int start, int end,float size){
        checkException(start,end);
        mText.setSpan(span, start, end, flag);
        if (span instanceof ClickableSpan)
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        if (rSize !=- 1.0f)
            mText.setSpan(new RelativeSizeSpan(rSize), start, end, flag);
        if (size !=-1.0f)
            mText.setSpan(new RelativeSizeSpan(size), start, end, flag);

    }

    /**
     * @param start starting position of text
     * @param end ending position of text
     */
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

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     * @param string pass the string to apply specific action
     */
    private void repeat(Type type, String string){
        repeat(type,-1,string);
    }


    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
        *@param size set size of text
     * @param string pass the string to apply specific action
     */
    private void repeat(Type type, String string, float size){
        repeat(type,-1,string, size);
    }


    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     *@param id id can be background color / foreground color / style
     * @param string pass the string to apply specific action
     */
    private void repeat(Type type, String id,String string){
        repeat(type,Color.parseColor(id),string);
    }

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     *@param id id can be background color / foreground color / style
     * @param string pass the string to apply specific action
     * @param size set text size
     */
    private void repeat(Type type, String id,String string,float size){
        repeat(type,Color.parseColor(id),string,size);
    }

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     *@param id id can be background color / foreground color / style
     * @param string pass the string to apply specific action
     */
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

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     *@param id id can be background color / foreground color / style
     * @param string pass the string to apply specific action
     * @param size set text size
     */


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

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     * @param start starting position of text
     * @param end ending position of text
     */

    private void type(Type type, int start, int end){
        switch (type){
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

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     * @param size set text size
     * @param start starting position of text
     * @param end ending position of text
     */

    private void type(Type type, int start, int end, float size){
        switch (type){
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

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     *@param id id can be background color / foreground color / style
     * @param start starting position of text
     * @param end ending position of text
     */

    private void type(Type type,int id, int start, int end){
        switch (type){
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

    /**
     * @param type type of action { Type.BACKGROUND,Type.FOREGROUND,Type.UNDERLINE,Type.STYLE,
     *                              Type.SUBSCRIPT,Type.SUPERSCRIPT,Type.STRIKEOUT,
     *                              Type.HYPERLINK,Type.IMAGE}
     *@param id id can be background color / foreground color / style
     * @param size set text size
     * @param start starting position of text
     * @param end ending position of text
     */
    private void type(Type type,int id, int start, int end, float size){
        switch (type){
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

    /**
     * @return apply all the properties to specified textview
     */
    public Texter build(){
        mTextView.setText(mText);
        return this;
    }
}
