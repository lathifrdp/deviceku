package id.mobile.deviceku.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import id.mobile.deviceku.R
import id.mobile.deviceku.theme.DeviceKuTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(viewModel: HomeViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        }
    ) {
        DeviceKuTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                HomeScreen(viewModel)
            }
        }
    }

}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val listDevice by viewModel.listDevice.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getListDevice()
    }

    Box (modifier = Modifier.padding(16.dp)){
        Column {
            if (listDevice.isEmpty()) {
                Text(text = "Data Kosong")
            } else {
                // Display the list of data
                LazyColumn {
                    items(listDevice) { data ->
                        Spacer(modifier = Modifier.padding(8.dp))
                        (data.name ?: "-")?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        (if (data.data?.color != null) data.data.color else "-")?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        (if (data.data?.price != null) data.data.price else "-")?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.End
                            )
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        HorizontalDivider() // Add a divider between items
                    }
                }
            }
        }
    }
}