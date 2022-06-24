package org.d3if4078.limassegiempat.ui.bangun_ruang

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.d3if4078.limassegiempat.R
import org.d3if4078.limassegiempat.databinding.FragmentBangunRuangBinding
import org.d3if4078.limassegiempat.model.BangunRuang
import org.d3if4078.limassegiempat.network.ApiBangunRuang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BangunRuangFragment : Fragment() {

    private val viewModel: BangunRuangViewModel by lazy {
        ViewModelProvider(this).get(BangunRuangViewModel::class.java)
    }
    private lateinit var binding: FragmentBangunRuangBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: BangunRuangAdapter
    lateinit var bangunRuangList: List<BangunRuang>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBangunRuangBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerview
        recyclerAdapter = BangunRuangAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 1)


        bangunRuangList = ArrayList<BangunRuang>()
        val apiInterface = ApiBangunRuang.create().getBangunRuang()

        apiInterface.enqueue(object : Callback<List<BangunRuang>> {
            override fun onResponse(
                call: Call<List<BangunRuang>>,
                response: Response<List<BangunRuang>>
            ) {
                if(response.body()?.isEmpty() == true) {
                    binding.recyclerview.visibility = View.GONE
                    binding.emptyView.visibility = View.VISIBLE

                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.emptyView.visibility = View.GONE

                    bangunRuangList = response.body()!!
                    Log.d("TAG", "Response = $bangunRuangList")
                    recyclerAdapter.setBangunRuangList(requireContext(), bangunRuangList)
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<BangunRuang>>, t: Throwable) {
                val snackbar = Snackbar.make(view, getString(R.string.failed_load_data_api),
                    Snackbar.LENGTH_LONG).setAction("OK", null)
                val snackbarView = snackbar.view
                val textView =
                    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.BLACK)
                snackbar.show()
            }
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }
}