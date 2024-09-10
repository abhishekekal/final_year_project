package com.example.test.ui.ar;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.animation.Transformation;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O_MR1)
public class MaskTransformation extends Transformation {

        private static Paint mMaskingPaint = new Paint();

        static {
            mMaskingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }

        private Drawable maskId;

        /**
         * @param maskId If you change the mask file, please also rename the mask file, or Glide will get
         *               the cache with the old mask. Because getId() return the same values if using the
         *               same make file name. If you have a good idea please tell us, thanks.
         */
        public MaskTransformation(Drawable maskId) {
            this.maskId = maskId;
        }


        public Bitmap transform(Bitmap source) {
            int width = source.getWidth();
            int height = source.getHeight();

            Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            Drawable mask = this.maskId;

            Canvas canvas = new Canvas(result);
            mask.setBounds(0, 0, width, height);
            mask.draw(canvas);
            canvas.drawBitmap(source, 0, 0, mMaskingPaint);

            source.recycle();

            return result;
        }

        public String key() {
            return "MaskTransformation(maskId=" + maskId
                    + ")";
        }
    }