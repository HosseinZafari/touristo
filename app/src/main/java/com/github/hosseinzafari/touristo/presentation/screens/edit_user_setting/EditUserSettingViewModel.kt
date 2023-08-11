package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.helper.getName
import com.github.hosseinzafari.touristo.base.helper.toTemporaryFile
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.TouristApplication
import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.usecases.GetUserUseCase
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.usecases.UpdateUserUseCase
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.usecases.UploadImageProfileUseCase
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
 * @created 07/08/2023 - 4:26 PM
 * @project Touristo
 */

@HiltViewModel
class EditUserSettingViewModel @Inject constructor(
    val context: TouristApplication ,
    val getUser: GetUserUseCase,
    val updateUser: UpdateUserUseCase,
    val uploadImageProfile: UploadImageProfileUseCase,
): XViewModel<EditUserSettingEffect ,  EditUserSettingAction , EditUserSettingState>() {

    override val processor = processor(
        initialState = EditUserSettingState(
            lastUser = null ,
            firstName = "" ,
            lastName = "" ,
            desc = "" ,
            imageUri = null ,
            needToSave = false ,
            status = XStatus.Idle ,
            effects = null ,
        ) ,
        actionReducer = ::reducer
    )

    private fun getNewProfileImageFile(uriStr: String): File? {
        val uri = Uri.parse(uriStr)
        val fileName = uri.getName(context)
        if (fileName != null) {
            Log.i("Test" , "filename $fileName")
            val file = uri.toTemporaryFile(context ,  fileName)
            return file
        }

        return null
    }

    private fun reducer(state: EditUserSettingState , action: EditUserSettingAction) {
        when (action) {
            is EditUserSettingAction.Submit -> {
                processor.setState(state.copy(status = XStatus.Loading))
                // TODO : Handle error timeout
                viewModelScope.launch(Dispatchers.IO) {
                    var newImageUri = state.imageUri
                    if (state.imageUri != null && state.needUploadImage) {
                        getNewProfileImageFile(state.imageUri)?.let {
                            newImageUri = uploadImageProfile(it)
                        }
                    }

                    updateUser(User(
                        id = null ,
                        email = null ,
                        password = null ,
                        name = state.firstName ,
                        family = state.lastName ,
                        desc = state.desc ,
                        profileUrl = newImageUri,
                    ))


                    val user = getUser().first()
                    processor.setState(state.copy(
                        lastUser = user ,
                        firstName = "" + user.name ,
                        lastName = "" + user.family ,
                        desc = "" + user.desc ,
                        status = XStatus.Idle ,
                        needUploadImage = false,
                        needToSave = false ,
                    ))
                }
            }
            is EditUserSettingAction.OnChangeDesc ->  {
                processor.setState(state.copy(desc = action.text , needToSave = true))
            }
            is EditUserSettingAction.OnChangeFirstName ->  {
                processor.setState(state.copy(firstName = action.text , needToSave = true))
            }
            is EditUserSettingAction.OnChangeImageUri ->  {
                Log.i("Test" , "onchage image uri " + action.text)
                processor.setState(state.copy(needUploadImage = true, imageUri = action.text, needToSave = true))
            }
            is EditUserSettingAction.OnChangeLastName ->  {
                processor.setState(state.copy(lastName = action.text , needToSave = true))
            }
            is EditUserSettingAction.GetData ->  {
                processor.setState(state.copy(status = XStatus.Loading))
                viewModelScope.launch(Dispatchers.IO) {
                    val user = getUser().first()
                    processor.setState(state.copy(
                        lastUser = user ,
                        firstName = "" + user.name ,
                        lastName = "" + user.family ,
                        imageUri = user.profileUrl ,
                        desc = "" + user.desc ,
                        status = XStatus.Idle ,
                    ))
                }
            }

            is EditUserSettingAction.ClickOnBack ->  {
               processor.setState(state.copy(effects = EditUserSettingEffect.NavigateToBack))
            }
        }
    }
}