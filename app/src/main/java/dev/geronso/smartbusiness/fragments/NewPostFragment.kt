package dev.geronso.smartbusiness.fragments

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.BigPost
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.fragment_new_post.*


class NewPostFragment : Fragment() {

    val images: MutableList<Bitmap> = mutableListOf()

    private val viewModel: ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_next.setOnClickListener {


            fst_const.visibility = View.GONE
            second_const.visibility = View.VISIBLE
        }
        new_post_time_start.setOnClickListener {

            SnapTimePickerDialog.Builder().apply {
                //
            }.build().apply{
                setListener { hour, minute ->

                }
            }.show(viewModel.manager.getFragmentManager(), tag)

        }



        val spinner: Spinner = view.findViewById(R.id.role_spinner)


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }
        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        images.add(BitmapFactory.decodeResource(resources, R.drawable.city))
        btn_public.setOnClickListener {
            sendPost()
            viewModel.manager.openSearchFragment()
        }
        pinco.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blackPrimary));
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_post, container, false)
    }


    private fun sendPost() {
        val post = BigPost(
            new_post_title.text.toString(),
            new_post_tags.text.toString(),
            new_post_text.text.toString(),
            new_post_contacts.text.toString(),
            (new_post_role.text.toString() == "Заказчик"),
            new_post_time_start.text.toString() + new_post_time_end.text.toString()
        )
        val database = Firebase.database.reference
        database.child("posts").child(viewModel.manager.currentProfile?.id.toString())
            .setValue(post)
    }

}