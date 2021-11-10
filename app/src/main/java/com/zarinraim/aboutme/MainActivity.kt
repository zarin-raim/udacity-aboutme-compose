package com.zarinraim.aboutme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.zarinraim.aboutme.ui.theme.AboutMeTheme
import com.zarinraim.aboutme.utils.LINE_HEIGHT

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Content("Zarina Raimkulova")
                }
            }
        }
    }
}

@Composable
fun Content(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(
                    id = R.dimen.top_padding
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name
        )

        NicknameField()

        Image(
            painter = painterResource(
                id = android.R.drawable.btn_star_big_on
            ),
            contentDescription = stringResource(R.string.yellow_star),
            modifier = Modifier.fillMaxWidth()
        )

        val scroll = rememberScrollState()
        Text(
            text = stringResource(id = R.string.lorem_ipsum),
            lineHeight = LINE_HEIGHT.sp,
            modifier = Modifier
                .verticalScroll(scroll)
                .padding(dimensionResource(id = R.dimen.small_padding))
                .fillMaxWidth()
        )
    }
}

@Composable
fun NicknameField() {
    var nickname by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    if (isVisible) {
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = {
                Text(stringResource(id = R.string.what_is_your_nickname))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.small_padding),
                bottom = dimensionResource(id = R.dimen.small_padding)
            )
        )

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(stringResource(id = R.string.done))
        }
    }

    NicknameText(
        isVisible = !isVisible,
        text = nickname
    )
}

@Composable
fun NicknameText(isVisible: Boolean, text: String) {
    if (isVisible) {
        Text(
            text = text,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.small_padding)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AboutMeTheme {
        Content("Android")
    }
}
