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
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

data class Puppy(
    val id: Int,
    @StringRes val nickName: Int,
    @IntRange(from = 0, to = 1) val gender: Int,
    val age: Int,
    @DrawableRes val avator: Int,
    var adopted: Boolean = false
) {
    companion object {
        val puppies = listOf(
            Puppy(0, R.string.amy, 0, 10, R.drawable.amy),
            Puppy(1, R.string.becky, 1, 20, R.drawable.becky),
            Puppy(2, R.string.ceci, 0, 15, R.drawable.ceci),
            Puppy(3, R.string.elenne, 0, 13, R.drawable.elenne),
            Puppy(4, R.string.florance, 0, 25, R.drawable.florance),
            Puppy(5, R.string.gigi, 0, 19, R.drawable.gigi),
        )
    }
}
