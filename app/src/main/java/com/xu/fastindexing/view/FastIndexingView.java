package com.xu.fastindexing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xu.fastindexing.MainActivity;
import com.xu.fastindexing.R;

/**
 * 创建者     伍碧林
 * 创建时间   2017/3/19 22:19
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class FastIndexingView extends View {
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };


    private Paint mPaint;
    private Paint mCirclePaint;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private int mAverageHeight;
    private int mColor;
    private float mDimension;

    public FastIndexingView(Context context) {
        this(context,null);
    }

    public FastIndexingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FastIndexingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FastIndexingView);
        if (typedArray != null) {
            mColor = typedArray.getColor(R.styleable.FastIndexingView_fastindex_textColor, Color.YELLOW);
            mDimension = typedArray.getDimension(R.styleable.FastIndexingView_fastindex_textSize, 25);
            typedArray.recycle();
        }
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setColor(mColor);
        mPaint.setTextSize(mDimension);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);//字体加粗.
        mPaint.setTextAlign(Paint.Align.CENTER);//居中对齐

        //圆形画笔
        mCirclePaint = new Paint();
        mCirclePaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mCirclePaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < LETTERS.length; i++) {
            Rect rect = new Rect();
            mPaint.getTextBounds(LETTERS[i],0,1,rect);//获取画出文字的一些属性保存到Rect中

           int x=mMeasuredWidth/2;
           int y=mAverageHeight/2 + rect.height()+mAverageHeight*i;//rect.height()其实就是buttom-top

            canvas.drawText(LETTERS[i],x,y,mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        mAverageHeight = mMeasuredHeight / 26;
    }
    int currentIndext = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getCurrentIndex(event);

                break;
            case MotionEvent.ACTION_MOVE:
                getCurrentIndex(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


    private void getCurrentIndex(MotionEvent event) {
        int index;
        index = (int) (event.getY() / mAverageHeight);

        if (index>=0&&index<LETTERS.length){//这里的判断是防止越界,导致异常

            if (currentIndext!=index){  //只有跟上一次不同才能进来,其实就是一个提高性能的判断
                if (mOnUpdateListener!=null)
                    mOnUpdateListener.OnUpdate(LETTERS[index]);
                currentIndext = index;//记录当前索引

            }

        }
    }

    OnUpdateListener mOnUpdateListener;

    public interface OnUpdateListener{
       void OnUpdate(String str);
    }
    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        mOnUpdateListener=onUpdateListener;
    }

    MainActivity mMainActivity;
    public void setOnCurrentListener(MainActivity mainActivity) {
        mMainActivity=mainActivity;
    }
}
