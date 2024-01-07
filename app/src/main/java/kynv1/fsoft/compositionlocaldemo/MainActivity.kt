package kynv1.fsoft.compositionlocaldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kynv1.fsoft.compositionlocaldemo.ui.theme.CompositionLocalDemoTheme

class MainActivity : ComponentActivity() {
    /**
     * step 1: define compositionLocal
     * step 2: bind composable
     * step 3: used it
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider( LocalAppColor provides AppColor(bodyTextColor = Color.Black)) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    Logger.lod("MainScreen ....")
}
data class AppColor(val bodyTextColor:Color)

val LocalAppColor = compositionLocalOf {
    AppColor(bodyTextColor = Color.Black)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompositionLocalDemoTheme {
        MainScreen()
    }
}