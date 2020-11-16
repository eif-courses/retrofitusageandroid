package eif.viko.lt.myapplication

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eif.viko.lt.shop.adapters.DeviceListAdapter
import eif.viko.lt.shop.viewmodels.DeviceViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DeviceListAdapter.Interaction {
    lateinit var deviceViewModel: DeviceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deviceViewModel = ViewModelProvider(this).get(DeviceViewModel::class.java)
        val deviceListAdapter: DeviceListAdapter by lazy { DeviceListAdapter(this) }

        // populated list with items



        deviceViewModel.getDeviceList().observe(this, Observer {
            deviceListAdapter.swapData(it)
        })

        recycleView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1,  RecyclerView.VERTICAL, false)
            // set the custom adapter to the RecyclerView
            adapter = deviceListAdapter

        }



    }

    override fun onItemClicked(device: Device) {
        TODO("Not yet implemented")
    }

    override fun playSound(device: Device) {
        val url = device.audio                ///"http://........" // your URL here
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }

    }

    override fun view3DModel(device: Device) {
        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
        val intentUri: Uri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
            .appendQueryParameter(
                "file",
                device.model
            )
            .build()

        sceneViewerIntent.data = intentUri
        sceneViewerIntent.setPackage("com.google.ar.core")
        startActivity(sceneViewerIntent)

    }

    override fun addToCart(device: Device) {
        TODO("Not yet implemented")
    }
}