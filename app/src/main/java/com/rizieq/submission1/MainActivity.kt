package com.rizieq.submission1

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {


    private var data: MutableList<Data> = mutableListOf()
    private lateinit var rvMain: RecyclerView


    companion object{
        const val INTENT_TO_DETAIL = "detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        rvMain = recyclerView {
            padding = dip(16)

        }

        initData()

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = AdapterLiga(this,data){
            startActivity<DetailActivity>(INTENT_TO_DETAIL to it)
        }



    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val detail = resources.getStringArray(R.array.club_detail)
        val image = resources.obtainTypedArray(R.array.club_image)

        data.clear()

        for (i in name.indices){
            data.add(
                Data(
                    name[i],
                    detail[i],
                    image.getResourceId(i,0)

                )
            )
        }
        image.recycle()
    }


    class ListItemUI : AnkoComponent<View>{

        lateinit var imgHome : ImageView
        lateinit var tvHome : TextView

        companion object{
            const val idImage = 1
            const val idName = 2
        }
        override fun createView(ui: AnkoContext<View>) = with(ui) {
            relativeLayout {
                imgHome = imageView {
                    adjustViewBounds = true
                    id = idImage

                }.lparams(dip(105), dip(150)) {
                    bottomMargin = dip(8)

                }

                tvHome = textView {
                    id = idName
                    gravity = Gravity.CENTER
                    setTypeface(Typeface.DEFAULT_BOLD)
                    textSize = 18f
                    right = 20


                }.lparams(dip(105), dip(150)) {
                    rightOf(imgHome)


                }
            }
        }

    }
}
