package kynv1.fsoft.compositionlocaldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kynv1.fsoft.compositionlocaldemo.ui.theme.CompositionLocalDemoTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    /**
     * step 1: define compositionLocal
     * step 2: bind composable
     * step 3: used it
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalAppColor provides AppColor(bodyTextColor = Color.Black)) {
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
fun MainScreen() {
    Logger.lod("MainScreen ....")
    var bodyTextColor by remember {
        mutableStateOf(Color.Black)
    }

    Column {
        Header(title = "Jetpack compose")
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Composition Local",
            style = TextStyle(color = LocalAppColor.current.bodyTextColor),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Body(content = "Changeable text color", bodyTextColor = bodyTextColor)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { bodyTextColor = getColor() }) {
            Text(text = "Change text Color")
        }
    }
}

@Composable
fun Header(title: String) {
    Logger.lod("Header...")
    Text(text = title, style = TextStyle(color = Color.Black, fontSize = 24.sp))
}

@Composable
fun Body(content: String, bodyTextColor: Color) {
    Logger.lod("Body...")
    CompositionLocalProvider(LocalAppColor provides LocalAppColor.current.copy(bodyTextColor = bodyTextColor)) {
        Column {
            Text(text = content, style = TextStyle(color = LocalAppColor.current.bodyTextColor))
            Spacer(modifier = Modifier.height(12.dp))
            ImageFeature()
        }
    }
}

@Composable
fun ImageFeature() {
    Logger.lod("ImageFeature....")
    Row {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
        Spacer(modifier = Modifier.height(12.dp))
        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
    }
}

data class AppColor(val bodyTextColor: Color)

val LocalAppColor = compositionLocalOf {
    AppColor(bodyTextColor = Color.Black)
}

fun getColor(): Color {
    val listColors = listOf(
        Color.Blue,
        Color.Red,
        Color.Yellow,
        Color.Magenta,
        Color.Green
    )
    val index = Random.nextInt(0, 4)
    return listColors[index]
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompositionLocalDemoTheme {
        MainScreen()
    }
}