package bt.ti.coroutines

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            createFakeRequest()
        }
    }

    fun createFakeRequest() {
        CoroutineScope(IO).launch {
            var result = request1()
            result += request2()
            System.out.println(result);
        }
    }

    suspend fun request1(): String {
        delay(3000)
        return "Hello"
    }

    suspend fun request2(): String {
        delay(3000)
        return " world"
    }

}