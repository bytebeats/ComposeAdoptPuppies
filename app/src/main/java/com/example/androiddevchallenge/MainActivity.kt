/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(Puppy.puppies) {
                PuppyItemView(puppy = it)
            }
        }
    }
}

@Composable
private fun PuppyItemView(puppy: Puppy) {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(all = 5.dp)
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.White)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Image(
                painter = painterResource(id = puppy.avator),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                contentScale = ContentScale.FillWidth
            )
            val textColor = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
            Row(
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(puppy.nickName), color = textColor)
                Image(
                    painter = painterResource(id = if (puppy.gender == 0) R.drawable.female else R.drawable.male),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
                Text(text = "${puppy.age} days", color = textColor)
            }
            Button(
                onClick = {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(
                            "index",
                            Puppy.puppies.indexOf(puppy)
                        )
                        context.startActivity(this)
                    }
                }
            ) {
                Text(text = "Lean more about ${if (puppy.gender == 0) "Her" else "Him"}")
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
