package com.example.makeyourselfapp.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.makeyourselfapp.R
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constants {
   val supabase = createSupabaseClient(
       supabaseUrl = "https://moewagojykcyqqwzqbrm.supabase.co",
       supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1vZXdhZ29qeWtjeXFxd3pxYnJtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjkxNjE1MjUsImV4cCI6MjA0NDczNzUyNX0.gs77FbNJKezPyfnY0aMOL-AzbaH5xcnQ_g-L9tHFTKw"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}
