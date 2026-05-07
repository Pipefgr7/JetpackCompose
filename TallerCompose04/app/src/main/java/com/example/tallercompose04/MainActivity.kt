package com.example.tallercompose04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tallercompose04.presentation.FinanzasScreen
import com.example.tallercompose04.ui.theme.TallerCompose04Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerCompose04Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FinanzasScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}