package com.github.hosseinzafari.touristo.presentation.screens.add_location

import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.helper.getName
import com.github.hosseinzafari.touristo.base.helper.toTemporaryFile
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.TouristApplication
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.usecases.AddLocationUseCase
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.usecases.GetCategoriesUseCase
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.usecases.UploadLocationImageUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/06/2023 - 8:28 PM
 * @project Touristo
 */

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    val context: TouristApplication,
    val getCategories: GetCategoriesUseCase,
    val addLocation: AddLocationUseCase,
    val uploadLocationImage: UploadLocationImageUseCase ,
) : XViewModel<AddLocationEffect, AddLocationAction, AddLocationState>() {

    override val processor = processor(
        initialState = AddLocationState(
            selectedProvince = null,
            selectedCategory = null,
            name = "",
            description = "",
            pictureUrl = null,
            categories = listOf(),
            status = XStatus.Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )

    private fun reducer(oldState: AddLocationState, action: AddLocationAction) {
        when (action) {
            is AddLocationAction.OnChangeProvince ->
                processor.setState(oldState.copy(selectedProvince = action.newLocation))
            is AddLocationAction.OnChangeDesc ->
                processor.setState(oldState.copy(description = action.newText))
            is AddLocationAction.OnChangeName ->
                processor.setState(oldState.copy(name = action.newText))
            is AddLocationAction.OnChangePicture ->
                processor.setState(oldState.copy(pictureUrl = action.newPicture))
            is AddLocationAction.OnChangeCategory ->
                processor.setState(oldState.copy(selectedCategory = action.newCategory))

            is AddLocationAction.Submit -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                if (oldState.description.isBlank() || oldState.name.isBlank() ||
                    oldState.pictureUrl == null || oldState.selectedProvince == null || oldState.selectedCategory == null
                ) {

                    processor.setState(oldState.copy(status = XStatus.Error("لطفا تمامی فیلد ها را پر کنید")))
                    return
                }

                // TODO : handle error timeout
                viewModelScope.launch(Dispatchers.IO) {

                    // upload file first and get uri from remote storage
                    val file = getNewProfileImageFile(oldState.pictureUrl!!)
                    if (file == null) {
                        processor.setState(oldState.copy(status = XStatus.Error("مشکل در آپلود فایل")))
                        return@launch
                    }

                    val imageUri = uploadLocationImage(file)

                    oldState.apply {
                        addLocation(
                            name,
                            description,
                            "" + selectedProvince!!.name,
                            selectedCategory!!,
                            imageUri ,
                        )
                    }


                    processor.setState(
                        oldState.copy(
                            status = XStatus.Idle,
                            effects = AddLocationEffect.NavigateToHome
                        )
                    )
                }

            }

            is AddLocationAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))

                viewModelScope.launch(Dispatchers.IO) {
                    val categories = getCategories().first()
                    processor.setState(
                        oldState.copy(
                            categories = categories,
                            status = XStatus.Idle,
                        )
                    )
                }
            }
        }
    }

    private fun getNewProfileImageFile(uri : Uri): File? {
        val fileName = uri.getName(context)
        if (fileName != null) {
            Log.i("Test" , "filename $fileName")
            val file = uri.toTemporaryFile(context ,  fileName)
            return file
        }

        return null
    }

}