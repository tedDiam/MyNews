package com.theo.mynews

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*

class SearchActivity : AppCompatActivity() {


    var calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var search : EditText? = null
    var begin_date: TextView? = null
    var end_date: TextView? = null
    var select_start: ImageButton? = null
    var select_end: ImageButton? = null

    var art : CheckBox?= null
    var business: CheckBox?= null
    var entrepreneurs: CheckBox?= null
    var sports: CheckBox?= null
    var politics: CheckBox?= null
    var travel: CheckBox?=null
    var submit: Button? = null

    var beginDate:String =""
    var endDate:String = ""

    var checkedValue: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search = findViewById(R.id.search_term_txt)
        begin_date = findViewById(R.id.begin_date_txt)
        end_date =  findViewById(R.id.end_date_txt)
        select_start = findViewById(R.id.select_start_date)
        select_end = findViewById(R.id.select_end_date)


        art = findViewById(R.id.art_check)
        business = findViewById(R.id.business_check)
        entrepreneurs = findViewById(R.id.entrepreneur_check)
        sports = findViewById(R.id.sports_check)
        politics = findViewById(R.id.politics_check)
        travel = findViewById(R.id.travel_check)

        submit = findViewById(R.id.submit_btn)

        select_start?.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->


                begin_date?.setText("" + dayOfMonth + "/" + month + "/" + year)
                beginDate = ""+year+""+month+""+dayOfMonth

            }, year, month, day)
            dpd.show()
        }
        select_end?.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                //if (monthOfYear<10) "0" + monthOfYear
                //if (dayOfMonth<10) "0" + dayOfMonth

                end_date?.setText("" + dayOfMonth + "/" + month + "/" + year)
                endDate = ""+year+""+month+""+dayOfMonth
            }, year, month, day)
            dpd.show()
        }

        art?.setOnCheckedChangeListener {
                buttonView , isChecked ->  onCheckBoxClicked(buttonView)
        }
        business?.setOnCheckedChangeListener { buttonView, isChecked ->  onCheckBoxClicked(buttonView)}
        entrepreneurs?.setOnCheckedChangeListener { buttonView, isChecked ->  onCheckBoxClicked(buttonView)}
        sports?.setOnCheckedChangeListener { buttonView, isChecked ->  onCheckBoxClicked(buttonView)}
        politics?.setOnCheckedChangeListener { buttonView, isChecked ->  onCheckBoxClicked(buttonView)}
        travel?.setOnCheckedChangeListener { buttonView, isChecked ->  onCheckBoxClicked(buttonView)}

        submit?.setOnClickListener {
            onSubmit()

        }

    }

    fun onCheckBoxClicked(view:View) {

        var checked = view as CheckBox


        when(checked){
            art -> {
                if (art!!.isChecked){
                    message( " Checked ")
                    checkedValue="art"
                    //art?.isChecked = false
                    business?.isChecked= false
                    entrepreneurs?.isChecked= false
                    sports?.isChecked = false
                    politics?.isChecked = false
                    travel?.isChecked = false

                } else {
                    message( " UnChecked ")

                }

            }
            business -> {
                if (business!!.isChecked){
                    message( " Checked ")
                    checkedValue="business"
                    art?.isChecked = false
                    //business?.isChecked= false
                    entrepreneurs?.isChecked= false
                    sports?.isChecked = false
                    politics?.isChecked = false
                    travel?.isChecked = false

                } else {
                    message( " UnChecked ")

                }
            }
            entrepreneurs ->{
                if (entrepreneurs!!.isChecked){
                    message( " Checked ")
                    checkedValue = "entrepreneurs"
                    art?.isChecked = false
                    business?.isChecked= false
                    //entrepreneurs?.isChecked= false
                    sports?.isChecked = false
                    politics?.isChecked = false
                    travel?.isChecked = false

                } else {
                    message( " UnChecked ")

                }
            }
            sports -> {
                if (sports!!.isChecked){
                    message( " Checked ")
                    checkedValue = "sports"
                    art?.isChecked = false
                    business?.isChecked= false
                    entrepreneurs?.isChecked= false
                    //sports?.isChecked = false
                    politics?.isChecked = false
                    travel?.isChecked = false

                } else {
                    message( " UnChecked ")

                }
            }
            politics -> {
                if (politics!!.isChecked){
                    message( " Checked ")
                    checkedValue = "politics"
                    art?.isChecked = false
                    business?.isChecked= false
                    entrepreneurs?.isChecked= false
                    sports?.isChecked = false
                    //politics?.isChecked = false
                    travel?.isChecked = false

                } else {
                    message( " UnChecked ")

                }
            }
            travel ->{
                if (travel!!.isChecked){
                    message( " Checked ")
                    checkedValue = "travel"
                    art?.isChecked = false
                    business?.isChecked= false
                    entrepreneurs?.isChecked= false
                    sports?.isChecked = false
                    politics?.isChecked = false
                    //travel?.isChecked = false

                } else {
                    message( " UnChecked ")

                }
            }
        }


    }

    fun onSubmit() {
        if(search?.text!!.isNotEmpty()){
            Log.d("TERM", search?.text.toString())
            val intent =Intent(this,  ResultsActivity::class.java)
            intent.putExtra("search_term", search?.text.toString())
            intent.putExtra("field", checkedValue)
            if(begin_date?.text!!.isNotEmpty()){

                intent.putExtra("begin_date", beginDate)
            }else {
                intent.putExtra("begin_date", "")
            }

            if (end_date?.text!!.isNotEmpty()) {
                intent.putExtra("end_date", endDate)
            } else {
                intent.putExtra("end_date", "")

            }

            startActivity(intent)

        } else {
            message("Veuillez remplir le champ recherche")
        }
    }

    fun message(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

}
