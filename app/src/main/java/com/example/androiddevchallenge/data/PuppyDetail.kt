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

import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

data class PuppyDetail(val puppy: Puppy, @StringRes val desc: Int) {
    companion object {
        val puppyDetails = listOf(
            PuppyDetail(Puppy.puppies[0], R.string.amy_desc),
            PuppyDetail(Puppy.puppies[1], R.string.becky_desc),
            PuppyDetail(Puppy.puppies[2], R.string.ceci_desc),
            PuppyDetail(Puppy.puppies[3], R.string.elenne_desc),
            PuppyDetail(Puppy.puppies[4], R.string.florance_desc),
            PuppyDetail(Puppy.puppies[5], R.string.gigi_desc),
        )
    }
}
