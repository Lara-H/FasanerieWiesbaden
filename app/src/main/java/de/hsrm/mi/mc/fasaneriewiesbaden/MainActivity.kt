package de.hsrm.mi.mc.fasaneriewiesbaden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.RootNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.FasanerieWiesbadenTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FasanerieWiesbadenTheme {
                RootNavGraph(navController = rememberNavController(), data = Data())
            }
        }
    }

}