package mr.sardorek.car_test

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var savollar = ArrayList<Savol>()

    private var next_answer = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savollar = Savollar.Savolberish()

        SavolniBoshlash()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun AnswerClicked(view : View){

        when(view.id){
            R.id.answer1 ->{Savolnitekshirish(answer1.text.toString()  ,answer1)}
            R.id.answer2 ->{Savolnitekshirish(answer2.text.toString() ,answer2)}
            R.id.answer3 ->{Savolnitekshirish(answer3.text.toString() ,answer3)}
            R.id.answer4 ->{Savolnitekshirish(answer4.text.toString() ,answer4)}
            R.id.answer5 ->{Savolnitekshirish(answer5.text.toString() ,answer5)}
            R.id.answer6 ->{Savolnitekshirish(answer6.text.toString() ,answer6)}
        }
        img.setBackgroundResource(savollar[next_answer].savol)

    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    fun Savolnitekshirish(javob : String , text : TextView){

        if(next_answer == savollar.size){
            next_answer = 0
        }

        else {
            if (javob == savollar[next_answer].correct_answer) {

                Toast.makeText(this,"To'g'ri", Toast.LENGTH_SHORT).show()
//                val view = mToast.view
//                view!!.setBackgroundResource(R.drawable.toas_shape)
//                val mToastText = mToast!!.view!!.findViewById<TextView>(android.R.id.message)
//                mToastText.setTextColor(Color.parseColor("#FFFFFFFF"))
//                mToast.show()

                next_answer++
                SavolniBoshlash()

            } else {
                val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibration.vibrate(100)

                text.animate().translationY(70f).setDuration(50).withEndAction {
                    text.animate().translationY(-90f).setDuration(50).withEndAction {
                        text.animate().translationY(0f).setDuration(50).withEndAction {
                            text.animate().translationX(90f).setDuration(50).withEndAction {
                                text.animate().translationX(-90f).setDuration(50).withEndAction {
                                    text.animate().translationX(0f).setDuration(40)
                                }
                            }
                        }
                    }
                }


                Toast.makeText(this, "Xato", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun SavolniBoshlash() {

        if (next_answer >= savollar.count()){
            Toast.makeText(this, "Savollar Tugadi", Toast.LENGTH_SHORT).show()
            return
        }

        savollar.shuffle()

        var savol_almash = arrayOf(
            savollar[next_answer].javob1 ,
            savollar[next_answer].javob2 ,
            savollar[next_answer].javob3 ,
            savollar[next_answer].javob4 ,
            savollar[next_answer].javob5 ,
            savollar[next_answer].javob6 ,
        )


        savol_almash.shuffle()

        answer1.text = savol_almash[0]
        answer2.text = savol_almash[1]
        answer3.text = savol_almash[2]
        answer4.text = savol_almash[3]
        answer5.text = savol_almash[4]
        answer6.text = savol_almash[5]

    }
    fun Reply(view : View){

    }


}