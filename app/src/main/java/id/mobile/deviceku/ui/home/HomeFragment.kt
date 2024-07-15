package id.mobile.deviceku.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import id.mobile.deviceku.R
import id.mobile.deviceku.databinding.FragmentHomeBinding
import id.mobile.deviceku.theme.DeviceKuTheme

class HomeFragment : Fragment() {

    // private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    // private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

//        root.composeView.apply {
//            // Dispose the Composition when viewLifecycleOwner is destroyed
//            setViewCompositionStrategy(
//                DisposeOnLifecycleDestroyed(viewLifecycleOwner)
//            )
//            setContent {
//                // In Compose world
//                MaterialTheme {
//                    Text("Hello Compose!")
//                }
//            }
//        }
//
//        return root

        return ComposeView(requireContext()).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )

            setContent {
                HomePage(homeViewModel){
                    navController.navigate(R.id.action_home_to_form)
                }
            }
        }
    }
}