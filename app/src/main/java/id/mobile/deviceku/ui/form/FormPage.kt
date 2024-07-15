package id.mobile.deviceku.ui.form

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
import id.mobile.deviceku.theme.DeviceKuTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FormPage(viewModel: FormViewModel, onFormValidated: () -> Unit) {
    DeviceKuTheme {
        Scaffold(
            content = { paddingValues ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .navigationBarsPadding(), color = MaterialTheme.colorScheme.background
                ) {
                    FormScreen(viewModel, onFormValidated)
                }
            },
        )
    }
}

@Composable
fun FormScreen(viewModel: FormViewModel, onFormValidated: () -> Unit) {
    val context = LocalContext.current
    var name by rememberSaveable { mutableStateOf("") }
    var year by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var capacity by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }

    val uiState = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.padding(16.dp, 16.dp)) {
        when (val state = uiState.value) {
            is PostDeviceUiState.PostDeviceInitial -> {}

            is PostDeviceUiState.PostDeviceLoading -> {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator()
                }
            }

            is PostDeviceUiState.PostDeviceSuccess -> {
                Toast.makeText(
                    context, state.message, Toast.LENGTH_LONG
                ).show()
                onFormValidated()
            }

            is PostDeviceUiState.PostDeviceError -> {
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
                text = "Name", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Enter name") },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Year", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = year,
                onValueChange = { year = it },
                placeholder = { Text(text = "Enter year") },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Price", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = price,
                onValueChange = { price = it },
                placeholder = { Text(text = "Enter price") },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Capacity", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = capacity,
                onValueChange = { capacity = it },
                placeholder = { Text(text = "Enter capacity") },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Color", style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = color,
                onValueChange = { color = it },
                placeholder = { Text(text = "Enter color") },
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally), onClick = {
                    validateForm(name = name,
                        year = year,
                        price = price,
                        capacity = capacity,
                        color = color,
                        onInvalidate = { errorMessage ->
                            Toast.makeText(
                                context, errorMessage, Toast.LENGTH_LONG
                            ).show()
                        },
                        onValidate = {
                            val data = DeviceParameter()
                            data.data?.price = price
                            data.data?.year = year.toLong()
                            data.data?.capacity = capacity
                            data.data?.color = color
                            data.name = name
                            viewModel.postDevice(data)
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
    name: String,
    year: String,
    price: String,
    capacity: String,
    color: String,
    onInvalidate: (String) -> Unit,
    onValidate: () -> Unit,
) {
    if (name.isEmpty()) {
        onInvalidate("Name required")
        return
    }

    if (year.isEmpty()) {
        onInvalidate("Year required")
        return
    }

    if (price.isEmpty()) {
        onInvalidate("Price required")
        return
    }

    if (capacity.isEmpty()) {
        onInvalidate("Capacity required")
        return
    }

    if (color.isEmpty()) {
        onInvalidate("Color required")
        return
    }

    onValidate()
}