package com.example.test.ui.ar

import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.test.R
import com.example.test.models.Product
import com.example.test.payment.PaymrntFragment
import com.example.test.utils.CategorySelection
import java.io.File
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ARFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ARFragment : Fragment() {

    private var imageUpperBody: ImageView? = null
    private var imageLowerBody: ImageView? = null
    private var imageUpperCompleteBody: ImageView? = null

    private var lowerProduct: Product? = null
    private var upperProduct: Product? = null

    private var buttonCheckout:Button? = null

    private val CAMERA_REQUEST = 100
    private val STORAGE_REQUEST = 200
    private val IMAGEPICK_GALLERY_REQUEST = 300
    private val IMAGE_PICKCAMERA_REQUEST = 400
    //lateinit var cameraPermission: Array<String>
    //lateinit var storagePermission: Array<String>
    var imageuri: Uri? = null

    val CAMERA_CAPTURE = 1
    val PIC_CROP = 2
    val GALLERY_FETCH = 3
    var currentLowerBodyProductIndex = 0

    //captured picture uri
    private var picUri: Uri? = null


    var intermediateName = "1.jpg"
    var resultName = "2.jpg"
    var intermediateProvider: Uri? = null
    var resultProvider: Uri? = null
    var cropActivityResultLauncher: ActivityResultLauncher<Intent>? = null

    var results:Bitmap? = null
    var maskBitmap:Bitmap? = null
    var photo : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_a_r, container, false)
        //val textView: TextView = view.findViewById(R.id.text_ar)
        //textView.text = "Work in progress"

        buttonCheckout = view.findViewById(R.id.buttonCheckout)
        imageUpperBody = view.findViewById(R.id.upper_body)
        imageLowerBody = view.findViewById(R.id.lower_body)
        imageUpperCompleteBody = view.findViewById(R.id.upper_complete_body)

        upperProduct = CategorySelection.cartProducts.stream()
            .filter { customer -> CategorySelection.UPPER == customer.productType }
            .findAny()
            .orElse(null)

        val products: List<Product> = CategorySelection.cartProducts.filter { product -> product.productType == CategorySelection.LOWER }

        if (products.isNotEmpty()) {
            lowerProduct = products[0]
        }

        if (upperProduct == null && lowerProduct == null) {
            Toast.makeText(context, "Product not added.", Toast.LENGTH_LONG).show()
            buttonCheckout?.visibility = View.GONE
        }
        else {
            buttonCheckout?.visibility = View.VISIBLE
       //     imageUpperBody?.setImageResource(upperProduct?.productThumbnailImage!!)
         //   imageLowerBody?.setImageResource(lowerProduct?.productThumbnailImage!!)
        }

        if (upperProduct != null && lowerProduct == null) {
            //imageUpperBody?.setImageResource(upperProduct?.productThumbnailImage!!)
            //Toast.makeText(context, "Lower body product not added.", Toast.LENGTH_LONG).show()
        }
        if (lowerProduct != null && upperProduct == null) {
            imageLowerBody?.setImageResource(lowerProduct?.productThumbnailImage!!)
            //Toast.makeText(context, "Upper body product not added.", Toast.LENGTH_LONG).show()
        }
        else if (lowerProduct != null && upperProduct != null) {
            //imageUpperBody?.setImageResource(upperProduct?.productThumbnailImage!!)
            imageLowerBody?.scaleType = ImageView.ScaleType.FIT_START
            imageLowerBody?.setImageResource(lowerProduct?.productThumbnailImage!!)
        }

        imageLowerBody?.setOnClickListener { view ->
            if (currentLowerBodyProductIndex < products.size) {
                lowerProduct = products[currentLowerBodyProductIndex]
                imageLowerBody?.setImageResource(lowerProduct?.productThumbnailImage!!)
                currentLowerBodyProductIndex ++
            }
            else if (currentLowerBodyProductIndex == products.size) {
                currentLowerBodyProductIndex = 0
                lowerProduct = products[currentLowerBodyProductIndex]
                imageLowerBody?.setImageResource(lowerProduct?.productThumbnailImage!!)
            }

        }
        imageUpperCompleteBody?.setOnClickListener { view ->
            showImagePicDialog()
        }
        buttonCheckout?.setOnClickListener {
            //Toast.makeText(context, "Will proceed to checkout", Toast.LENGTH_LONG).show()
            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            ft.replace(R.id.nav_host_fragment_content_gender_selection , PaymrntFragment(), "NewFragmentTag")
            ft.addToBackStack(null)
            ft.commit()
        }

        /*Picasso.with(this.requireContext())
            .load("http://pixlab.xyz/images/eye_mask.png")
            .into(imageUpperBody);*/

        //imageUpperCompleteBody?.setImageBitmap(maskingProcess())

       // Picasso.get().load(R.drawable.test_image).placeholder(R.drawable.upper_body_cropped_2).error(R.drawable.male_icon).into(imageUpperCompleteBody)
        return view
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val localUri = Uri.parse(
            MediaStore.Images.Media.insertImage(
                inContext!!.contentResolver, inImage, null, null
            )
        )
        if (localUri != null) {
            val cv = ContentValues()
            cv.put(
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                java.lang.Long.valueOf(System.currentTimeMillis())
            )
            cv.put(
                MediaStore.Images.ImageColumns.DATE_ADDED,
                java.lang.Long.valueOf(System.currentTimeMillis())
            )
            inContext!!.contentResolver.update(
                localUri, cv, null,
                null
            ) //w  ww.j  av a  2  s  .  c  om
        }
        inImage.recycle()
        return localUri
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_CAPTURE){
            if (data != null) {
                val extras: Bundle = data.extras!!
                val photo : Bitmap? = extras.getParcelable<Bitmap>("data")
                intermediateProvider = getImageUri(this.requireContext(), photo!!)
                performCrop()
            };
        }
        else if(requestCode == PIC_CROP){
             photo = loadFromUri(resultProvider)

            imageUpperCompleteBody?.setImageBitmap(maskingProcess(photo!!))

        }
        else if (requestCode == GALLERY_FETCH) {
            intermediateProvider = data!!.data
            val photo : Bitmap? = loadFromUri(intermediateProvider)
            intermediateProvider = getImageUri(this.requireContext(), photo!!)
            performCrop()


            //val photoFile: File = getPhotoFileUri(resultName)
            //resultProvider = Uri.fromFile(photoFile)
            //Picasso.get().load(R.drawable.test_image).placeholder(R.drawable.upper_body_latest_cropped).error(R.drawable.upper_body_latest_cropped).into(imageUpperCompleteBody)


        }
    }

    fun maskingProcess(original: Bitmap): Bitmap? {
        //photo = BitmapFactory.decodeResource(resources, R.drawable.test_image)
        var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.upper_body_latest_cropped)
        val width = photo!!.width
        val height = photo!!.height
        results = Bitmap.createBitmap(width+50,height, Bitmap.Config.ARGB_8888)
        maskBitmap = Bitmap.createScaledBitmap(mask, width+50,height, true)
        val canvas = Canvas(results!!)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        photo = photo!!.copy(Bitmap.Config.ARGB_8888, true);
        mask = mask.copy(Bitmap.Config.ARGB_8888, true);
        canvas.drawBitmap(photo!!, 20.0f, 0.0f, null)
        canvas.drawBitmap(maskBitmap!!, 0.0f, 0.0f, paint)
        paint.xfermode = null
        paint.style = Paint.Style.STROKE

        return results
    }

    fun getPhotoFileUri(fileName: String): File {
        val mediaStorageDir: File = File(this.requireContext().getExternalFilesDir(""), "Rakesh")
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d("Rakesh", "failed to create directory")
        }
        return File(mediaStorageDir.path + File.separator + fileName)
    }

    private fun showImagePicDialog() {
        val options = arrayOf("Camera", "Gallery")
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this.context)
        builder.setTitle("Pick Image From")
        builder.setItems(options) { dialog, which ->
            if (which == 0) {
                //Open Camera
                openCamera()
            } else if (which == 1) {
                // Open gallery
                openGallery()
            }
        }
        builder.create().show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_FETCH);
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(this.requireContext().packageManager) != null) {
            startActivityForResult(intent, CAMERA_CAPTURE);
        }
    }

    private fun performCrop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.requireContext().grantUriPermission(
                "com.android.camera",
                intermediateProvider,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(intermediateProvider, "image/*")
            val list: List<ResolveInfo> = this.requireContext().packageManager.queryIntentActivities(intent, 0)
            var size = 0
            if (list != null) {
                this.requireContext().grantUriPermission(
                    list[0].activityInfo.packageName,
                    intermediateProvider,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                size = list.size
            }
            if (size == 0) {
                Toast.makeText(this.context, "Error, wasn't taken image!", Toast.LENGTH_SHORT).show()
            } else {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                intent.putExtra("crop", "true")
                intent.putExtra("scale", true)
                val photoFile: File = getPhotoFileUri(resultName)
                // wrap File object into a content provider
                // required for API >= 24
                // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
                resultProvider = FileProvider.getUriForFile(
                    this.requireContext(),
                    "com.example.test.fileprovider",
                    photoFile
                )
                intent.putExtra("return-data", false)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, resultProvider)
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
                val cropIntent = Intent(intent)
                val res = list!![0]
                cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                this.requireContext().grantUriPermission(
                    res.activityInfo.packageName,
                    resultProvider,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                cropIntent.component =
                    ComponentName(res.activityInfo.packageName, res.activityInfo.name)
                startActivityForResult(cropIntent, PIC_CROP)
            }
        } else {
            val photoFile: File = getPhotoFileUri(resultName)
            resultProvider = Uri.fromFile(photoFile)
            val intentCrop = Intent("com.android.camera.action.CROP")
            intentCrop.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            intentCrop.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            intentCrop.setDataAndType(intermediateProvider, "image/*")
            intentCrop.putExtra("crop", "true")
            intentCrop.putExtra("scale", true)
            intentCrop.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            intentCrop.putExtra("noFaceDetection", true)
            intentCrop.putExtra("return-data", false)
            intentCrop.putExtra(MediaStore.EXTRA_OUTPUT, resultProvider)
            startActivityForResult(intentCrop, PIC_CROP)
        }
    }

    fun loadFromUri(photoUri: Uri?): Bitmap? {
        var image: Bitmap? = null
        try {
            image = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
                // on newer versions of Android, use the new decodeBitmap method
                val source = ImageDecoder.createSource(
                    this.requireContext().contentResolver,
                    photoUri!!
                )
                ImageDecoder.decodeBitmap(source)
            } else {
                // support older versions of Android by using getBitmap
                MediaStore.Images.Media.getBitmap(this.requireContext().contentResolver , photoUri)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }


    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ARFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ARFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}