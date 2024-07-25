package id.mobile.deviceku.ui.form_movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.mobile.deviceku.model.DeviceParameter
import id.mobile.deviceku.model.MovieParameter
import id.mobile.deviceku.theme.DeviceKuTheme
import id.mobile.deviceku.ui.form.FormViewModel
import id.mobile.deviceku.ui.form.PostDeviceUiState
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FormMoviePage(viewModel: FormMovieViewModel, onFormValidated: () -> Unit) {
    DeviceKuTheme {
        Scaffold(
            content = { paddingValues ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .navigationBarsPadding(), color = MaterialTheme.colorScheme.background
                ) {
                    FormMovieScreen(viewModel, onFormValidated)
                }
            },
        )
    }
}

@Composable
fun FormMovieScreen(viewModel: FormMovieViewModel, onFormValidated: () -> Unit) {
    val context = LocalContext.current
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val uiState = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.padding(16.dp, 16.dp)) {
        when (val state = uiState.value) {
            is PostMovieUiState.PostMovieInitial -> {}

            is PostMovieUiState.PostMovieLoading -> {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator()
                }
            }

            is PostMovieUiState.PostMovieSuccess -> {
                Toast.makeText(
                    context, state.message, Toast.LENGTH_LONG
                ).show()
                onFormValidated()
            }

            is PostMovieUiState.PostMovieError -> {
                Toast.makeText(
                    context, state.message, Toast.LENGTH_LONG
                ).show()
            }
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Title", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(text = "Enter title") },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Description", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                placeholder = { Text(text = "Enter description") },
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally), onClick = {
                    validateForm(title = title,
                        description = description,
                        onInvalidate = { errorMessage ->
                            Toast.makeText(
                                context, errorMessage, Toast.LENGTH_LONG
                            ).show()
                        },
                        onValidate = {
                            val titleBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
                            val descriptionBody =
                                description.toRequestBody("text/plain".toMediaTypeOrNull())

                            val data = MovieParameter()
                            data.title = titleBody
                            data.description = descriptionBody
                            viewModel.postMovie(data)
                        })
                }, shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(
                    text = "Submit", style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

}

private fun validateForm(
    title: String,
    description: String,
    onInvalidate: (String) -> Unit,
    onValidate: () -> Unit,
) {
    if (title.isEmpty()) {
        onInvalidate("Title required")
        return
    }

    if (description.isEmpty()) {
        onInvalidate("Description required")
        return
    }

    onValidate()
}