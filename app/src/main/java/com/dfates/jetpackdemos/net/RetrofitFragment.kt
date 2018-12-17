package com.dfates.jetpackdemos.net


import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingFragment
import com.dfates.jetpackdemos.common.adapter.CommonRecycleViewAdapter
import com.dfates.jetpackdemos.databinding.FragmentRetrofitBinding
import com.dfates.jetpackdemos.glide.GlideApp
import com.dfates.jetpackdemos.net.api.ApiClient
import com.dfates.jetpackdemos.net.api.ApiErrorModel
import com.dfates.jetpackdemos.net.api.ApiResponse
import com.dfates.jetpackdemos.net.data.Repo
import com.dfates.jetpackdemos.net.service.PicService
import com.dfates.jetpackdemos.net.tools.SchedulersThread
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_retrofit.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RetrofitFragment : BaseBindingFragment<FragmentRetrofitBinding>(R.layout.fragment_retrofit)  {

    private lateinit var adapter: CommonRecycleViewAdapter<String>

    lateinit var string_array : MutableList<String>

    var data: MutableLiveData<List<String>> = MutableLiveData()

    override fun initView() {

//        Glide.with(this)
//                .load("http://www.91zhuti.com/uploads/allimg/140310/4-140310161H30-L.jpg")
//                .into(binding.image)

        // 瀑布流相关

        var recyclerViewLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = object : CommonRecycleViewAdapter<String>(context!!
                , R.layout.layout_pic_adapter
                , null
                , { holder, data, _, _ ->
                        holder.getView<TextView>(R.id.text_view)?.text = data.toString()

//            (holder.getView<ImageView>(R.id.my_image_view)!!).setImageDrawable(resources.getDrawable(R.mipmap.test_image))

//                        Glide.with(this)
//                                .load(data.toString())
//                                .into(holder.getView<ImageView>(R.id.my_image_view)!!)
                        GlideApp
                                .with(this)
                                .load(data.toString())
                                .centerCrop()
                                .placeholder(resources.getDrawable(R.mipmap.test_image))
                                .into(holder.getView<ImageView>(R.id.my_image_view)!!);

//                        holder.setText(R.id.tv_text, data.toString())
                    }) {
                        override fun getItemViewType(position: Int): Int {
                            return super.getItemViewType(position)
                        }
                    }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = recyclerViewLayoutManager


        //优化前
        binding.button.setOnClickListener{
            //这样写还是耦合度太高  决定在这个基础上再封装一层
            ApiClient.instance.retrofit
                    .create(PicService::class.java)
                    .listRepos("desfate")
                    .compose(SchedulersThread.main())  //设置主线程回调
                    .subscribe(object : ApiResponse<List<Repo>>(this!!.activity!!){
                        override fun success(data: List<Repo>) {
                            System.out.println();
                        }

                        override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                            System.out.println();
                        }

                    })
        }

        //优化后  每个请求 都在HttpReqManager里封装一下
        binding.button.setOnClickListener{
//            HttpReqManager().getPicData(object : ApiResponse<List<Repo>>(this!!.activity!!){
//                override fun success(data: List<Repo>) {
//
//                }
//
//                override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
//                }
//            })
            string_array.add("http://img.zcool.cn/community/0107da59be1078a8012075343682ec.jpg@1280w_1l_2o_100sh.jpg")
            data.postValue(string_array)
        }
    }

    override fun initData() {
        super.initData()
        string_array = mutableListOf("http://img1.imgtn.bdimg.com/it/u=2937494656,4171507231&fm=26&gp=0.jpg"
                ,"http://www.91zhuti.com/uploads/allimg/140310/4-140310161H30-L.jpg"
                ,"http://img.zcool.cn/community/01124256ea76616ac7255885d9d2e8.jpg@1280w_1l_2o_100sh.jpg"
                ,"http://img.zcool.cn/community/01a5fa588372fda801219c770ce2fe.jpg")
        adapter.update(string_array)

        // livedata 进行双向绑定
        data.observe(this, Observer<List<String>> { users ->
            adapter.update(users)
        })
    }

}
