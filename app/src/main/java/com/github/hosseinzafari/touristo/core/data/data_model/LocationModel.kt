package com.github.hosseinzafari.touristo.core.data.data_model

import android.net.Uri
import com.queezo.app.assets.card_1_1
import com.queezo.app.assets.card_1_2
import com.queezo.app.assets.card_2_1
import com.queezo.app.assets.card_2_2

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:28 AM
 * @project Touristo
 */

data class LocationModel(
    var id: Int,
    var desc: String,
    var resID: Int,
    var name: String,
    var location: ProvinceModel,
    var likeCount: Int,
    var categoryModel: CategoryModel,
    var comments: MutableList<CommentModel>,
    var imageUri: Uri? = null ,
)

