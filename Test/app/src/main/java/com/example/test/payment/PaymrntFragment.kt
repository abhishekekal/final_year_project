package com.example.test.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.test.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaymrntFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymrntFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var progressBar: ProgressBar? = null
    private var loadingTextLayout: ConstraintLayout? = null
    private var successLayout: ConstraintLayout? = null
    private var i = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val root = inflater.inflate(R.layout.fragment_paymrnt, container, false)
        progressBar = root.findViewById(R.id.progress_Bar)
        loadingTextLayout = root.findViewById(R.id.loadingText)
        successLayout = root.findViewById(R.id.successText)

        progressBar?.visibility = View.VISIBLE
        loadingTextLayout?.visibility = View.VISIBLE
        successLayout?.visibility = View.INVISIBLE
        i = progressBar!!.progress
        Thread {
            // this loop will run until the value of i becomes 99
            while (i < 100) {
                i += 1
                // Update the progress bar and display the current value
                handler.post {
                    progressBar!!.progress = i
                    // setting current progress to the textview
                }
                try {
                    Thread.sleep(40)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

           this@PaymrntFragment.activity!!.runOnUiThread(Runnable {
                progressBar!!.visibility = View.INVISIBLE
                loadingTextLayout!!.visibility = View.INVISIBLE
                successLayout!!.visibility = View.VISIBLE
            })
            // setting the visibility of the progressbar to invisible
            // or you can use View.GONE instead of invisible
            // View.GONE will remove the progressbar

        }.start()

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaymrntFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                PaymrntFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}