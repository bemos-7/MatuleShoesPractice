package com.bemos.matuleshoes.screen.map

import android.content.Context
import android.content.res.Configuration
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.databinding.MapFragmentBinding
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint

class MapFragment : Fragment() {

    lateinit var binding: MapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MapFragmentBinding.inflate(inflater)
        val sharedPreferences = requireContext().getSharedPreferences("OSM", Context.MODE_PRIVATE)
        org.osmdroid.config.Configuration.getInstance().load(requireContext(), sharedPreferences)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.osmMap.setTileSource(TileSourceFactory.MAPNIK)
        binding.osmMap.setMultiTouchControls(true)
        binding.osmMap.controller.setZoom(10.0)
        binding.osmMap.controller.setCenter(GeoPoint(42.0, 47.0))
    }

}