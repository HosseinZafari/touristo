package com.github.hosseinzafari.touristo.presentation.screens.loading

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import kotlinx.coroutines.delay

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 22/07/2023 - 4:03 PM
 * @project Touristo
 */
 
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AuthVerificationScreen(
    intent: Intent? ,
    onNavigateToSignup: (String) -> Unit ,
    onNavigateToHome: () -> Unit ,
    viewModel: AuthVerificationViewModel = hiltViewModel()
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = 0) {
        if (intent == null) {
            processor.sendAction(AuthVerificationAction.FinishSyncing)
            return@LaunchedEffect;
        }


        processor.sendAction(AuthVerificationAction.HandleIntentsData(intent))
        delay(4000)
        processor.sendAction(AuthVerificationAction.CheckSession)
    }

    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {

        } ,
        effectsBlock = {
            when(it) {
                is AuthVerificationEffect.NavigateToSignup -> {
                    Log.i("Test" , "Authverfification : NavigateToSignup")
                    onNavigateToSignup(it.msg)
                }

                is AuthVerificationEffect.NavigateToHome -> {
                    Log.i("Test" , "Authverfification : NavigateToHome")
                    onNavigateToHome()
                }
            }

            processor.setState(state.value.copy(effects = null))
        } ,
    )



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentWindowInsets = WindowInsets(16.dp , 16.dp , 16.dp, 16.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        TouristoFrame(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        ) {

            Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator( )
                Spacer(modifier = Modifier.height(38.dp))
                Text(text = "در حال بررسی صحت حساب کاربری..." , style = MaterialTheme.typography.titleMedium , fontWeight = FontWeight.ExtraLight)
            }

        }
    }

}