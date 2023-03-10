package Home

import AdapterClass.CustomAdapterRecyclerView
import ModelClass.ModelClassRecyclerView
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enjayinterviewapp.R


class HomeFragment : Fragment(), CustomAdapterRecyclerView.MyClickListner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerview = view?.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview?.layoutManager = LinearLayoutManager(requireContext())

        val data=ArrayList<ModelClassRecyclerView>()


        data.add(ModelClassRecyclerView(R.drawable.aptitudequestion,"Aptitude Question"))
        data.add(ModelClassRecyclerView(R.drawable.logicalquestion,"Logical Question"))
        data.add(ModelClassRecyclerView(R.drawable.interviewquestion,"Common Interview Question"))
        data.add(ModelClassRecyclerView(R.drawable.technicalquestion,"Technical Question"))

        val adapter= CustomAdapterRecyclerView(data,this)

        recyclerview?.adapter=adapter

        return view
    }

    override fun onClick(position: Int) {
        when (position){
            0 -> startActivity(Intent(activity, AptitudeQuestion::class.java))
            1 -> startActivity(Intent(activity, LogicalQuestion::class.java))
            2 -> startActivity(Intent(activity, CommonQuestion::class.java))
            3 -> startActivity(Intent(activity, TechnicalQuestion::class.java))

            else -> Toast.makeText(activity,"error in intent", Toast.LENGTH_SHORT).show()
        }
    }


}

