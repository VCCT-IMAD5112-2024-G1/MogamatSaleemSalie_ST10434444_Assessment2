package tamagotchi.com

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var feedButton: Button
    private lateinit var washButton: Button
    private lateinit var playButton: Button
    private lateinit var feedStatus: TextView
    private lateinit var washStatus: TextView
    private lateinit var playStatus: TextView
    private lateinit var animalImage: ImageView

    private var feedPercentage = 0
    private var washPercentage = 0
    private var playPercentage = 0


    private val feedImages = arrayOf(R.drawable.feed_image1, R.drawable.feed_image2, R.drawable.feed_image3)
    private val washImages = arrayOf(R.drawable.wash_image1, R.drawable.wash_image2, R.drawable.wash_image3)
    private val playImages = arrayOf(R.drawable.play_image1, R.drawable.play_image2, R.drawable.play_image3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedButton = findViewById(R.id.feedButton)
        washButton = findViewById(R.id.washButton)
        playButton = findViewById(R.id.playButton)

        feedStatus = findViewById(R.id.feedStatus)
        washStatus = findViewById(R.id.washStatus)
        playStatus = findViewById(R.id.playStatus)

        animalImage = findViewById(R.id.animalImage)

        feedButton.setOnClickListener {
                feedPercentage = feedPercentage.coerceAtMost(100) + getRandomPercentage()
            if (feedPercentage > 100) {
                feedPercentage = 100
            }

            updateStatus(feedStatus, feedPercentage)
            updateAnimalImage(feedImages)
        }

        washButton.setOnClickListener {
            if (washPercentage < 100) {
                washPercentage = 100
                updateStatus(washStatus, washPercentage)
            }
            updateAnimalImage(washImages)
        }

        playButton.setOnClickListener {
            playPercentage = playPercentage.coerceAtMost(100) + getRandomPercentage()
            if (playPercentage > 100) {
                playPercentage = 100
            }
            updateStatus(playStatus, playPercentage)
            updateAnimalImage(playImages)
        }
    }

    private fun getRandomPercentage(): Int {
        return Random.nextInt(25) + 1
    }

    private fun updateStatus(textView: TextView, percentage: Int) {
        textView.text = "$percentage%"
    }

    private fun updateAnimalImage(images: Array<Int>) {
        val randomIndex = Random.nextInt(images.size)
        animalImage.setImageResource(images[randomIndex])
    }
}
