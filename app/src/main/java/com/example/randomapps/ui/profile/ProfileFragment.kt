package com.example.randomapps.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomapps.R
import com.example.randomapps.profile.ProfileData
import com.example.randomapps.profile.ProfileDataAdapter
import com.example.randomapps.ui.ARG_PARAM1
import com.example.randomapps.ui.ARG_PARAM2
class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val dataNames = resources.getStringArray(R.array.data_name)
        val dataNims = resources.getStringArray(R.array.data_nim)
        val dataPhotos = resources.obtainTypedArray(R.array.data_photo)

        val dataList = mutableListOf<ProfileData>()

        for (i in 0 until dataNames.size) {
            dataList.add(ProfileData(dataNames[i], dataNims[i], dataPhotos.getResourceId(i, 0)))
        }

        dataPhotos.recycle() // Penting untuk melepaskan sumber daya

        val adapter = ProfileDataAdapter(requireContext(), dataList)
        recyclerView.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}