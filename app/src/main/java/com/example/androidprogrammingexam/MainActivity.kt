package com.example.androidprogrammingexam

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var imageButton: Button
    private lateinit var sendButton: Button
    private var imageData: ByteArray? = null
    private val postURL: String = "http://api-edu.gtl.ai/api/v1/imagesearch/upload"
    // remember to use your own api

    val imageList = ArrayList<ImageApi>()
    private lateinit var imagesRV: RecyclerView
    private var requestQueue: RequestQueue? = null



    companion object {
        private const val IMAGE_PICK_CODE = 999
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesRV = findViewById(R.id.recyclerView)
        imagesRV.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)



        imageView = findViewById(R.id.imageView)

        imageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            launchGallery()
        }
        sendButton = findViewById(R.id.sendButton)
        sendButton.setOnClickListener {
            var image = dogNameText.text.toString()

            uploadImage()
            searchImages(image)
        }
    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun uploadImage() {
        imageData?: return
        val request = object : VolleyFileUploadRequest(
            Request.Method.POST,
            postURL,
            Response.Listener {

                Toast.makeText(this, "Success: $it", Toast.LENGTH_LONG).show()
                //Log.i("cat","response is: $it")
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error: $it", Toast.LENGTH_LONG).show()

                //println("error is: $it")
            }
        ) {
            override fun getByteData(): MutableMap<String, FileDataPart> {
                var params = HashMap<String, FileDataPart>()
                params["imageFile"] = FileDataPart("image", imageData!!, "jpeg")
                return params
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    @Throws(IOException::class)
    private fun createImageData(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }

private fun searchImages(image: String) {
    imageList.clear()

    val url = "http://api-edu.gtl.ai/api/v1/imagesearch/bing"

    val request = JsonObjectRequest(Request.Method.GET, url, null, {
            response ->try {
        val jsonArray = response.getJSONArray("images")

        for(i in image.indices){
            val list = image[i]
            imageList.add(
                ImageApi(list.toString())
            )
        }
        imagesRV.adapter = ImageAdapter(this@MainActivity, imageList)
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    }, { error -> error.printStackTrace() })
    requestQueue?.add(request)
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            if (uri != null) {
                imageView.setImageURI(uri)
                createImageData(uri)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}