package com.dfates.jetpackdemos.net


import android.view.View
import androidx.fragment.app.Fragment
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingActivity
import com.dfates.jetpackdemos.base.BaseBindingFragment
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.databinding.FragmentRetrofitBinding
import com.dfates.jetpackdemos.net.data.Repo
import com.dfates.jetpackdemos.net.service.PicService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RetrofitFragment : BaseBindingFragment<FragmentRetrofitBinding>(R.layout.fragment_retrofit)  {

    override fun initView() {
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
    }


}
