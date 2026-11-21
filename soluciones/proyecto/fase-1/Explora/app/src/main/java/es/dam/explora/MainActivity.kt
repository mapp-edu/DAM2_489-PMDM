package es.dam.explora

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.explora.ui.PantallaLugares
import es.dam.explora.ui.theme.ExploraTheme

private const val TAG = "Explora"

/**
 * Única Activity de la app: aloja toda la interfaz de Compose.
 * Registra el ciclo de vida en Logcat (filtro: package:mine tag:Explora).
 * Al girar la pantalla se ve: onPause → onStop → onDestroy → onCreate → onStart → onResume.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate (¿estado guardado?: ${savedInstanceState != null})")
        enableEdgeToEdge()
        setContent {
            ExploraTheme {
                PantallaLugares()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
