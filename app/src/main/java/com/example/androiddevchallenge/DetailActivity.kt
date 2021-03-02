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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.PuppyDetail
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailActivity : AppCompatActivity() {

    val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val puppyDetail = PuppyDetail.puppyDetails[
            intent.getIntExtra(
                "index",
                0
            )
        ]
        detailViewModel.puppyDetail.observe(this) {
            setContent {
                MyTheme {
                    ShowPuppyDetails(detail = it)
                }
            }
        }
        detailViewModel.observe(puppyDetail)
    }
}

class DetailViewModel : ViewModel() {
    val puppyDetail = MutableLiveData<PuppyDetail>()

    fun observe(detail: PuppyDetail) {
        puppyDetail.value = detail
    }
}

@Composable
fun ShowPuppyDetails(detail: PuppyDetail) {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
    val puppy = detail.puppy
    val adopted = remember { mutableStateOf(puppy.adopted) }
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Image(
                painter = painterResource(id = puppy.avator),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
            )
            Text(text = stringResource(puppy.nickName), style = MaterialTheme.typography.h6)
            Image(
                painter = painterResource(id = if (puppy.gender == 0) R.drawable.female else R.drawable.male),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
            Text(text = "${puppy.age} days", color = textColor)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
            )
            Text(
                text = stringResource(id = detail.desc),
                color = textColor,
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 15.dp, bottom = 15.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                style = MaterialTheme.typography.body1
            )
            Button(
                onClick = {
                    adopted.value = !adopted.value
                },
                enabled = !adopted.value,
                colors = ButtonDefaults.buttonColors(backgroundColor = if (!adopted.value) MaterialTheme.colors.primary else Color.Gray)
            ) {
                Text(text = if (adopted.value) "Thank you!" else "Adopt ${if (puppy.gender == 0) "her" else "him"}")
            }
        }
    }
}
