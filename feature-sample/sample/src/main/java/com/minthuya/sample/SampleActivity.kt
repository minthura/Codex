package com.minthuya.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minthuya.component.parent
import com.minthuya.networkkit.UiResult
import com.minthuya.sample.di.DaggerSampleComponent
import com.minthuya.sample.ui.SampleViewModel
import javax.inject.Inject

class SampleActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSampleComponent.factory().create(this, baseContext.parent()).inject(this)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text("Welcome")
                        }
                    )
                }
            ) { innerPadding ->
                val uiState = viewModel.uiState.collectAsState()
                Column(
                    modifier = Modifier
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = when (uiState.value) {
                            is UiResult.Error -> "Fetch error"
                            is UiResult.Loading -> "Loading.."
                            is UiResult.Success -> uiState.value.body?.weather?.firstOrNull()?.main.orEmpty()
                        }
                    )
                }
            }
        }
        viewModel.fetchWeather()
    }

    companion object {
        fun start(context: Context) = with(context) {
            startActivity(Intent(this, SampleActivity::class.java))
        }
    }
}