package com.hookedroid.androidarchitecture.episode

import com.hookedroid.androidarchitecture.BaseFragment

class EpisodesFragment : BaseFragment<EpisodesViewModel>() {

//    private lateinit var mBinding: FragmentCharactersBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        FragmentCharactersBinding.inflate(inflater, container, false).apply {
//            mBinding = this
//            setLifecycleOwner(this@EpisodesFragment)
//            return root
//        }
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        val adapter = CharacterPagedListAdapter(requireContext())
//
//        mViewModel.characters.observe(this, Observer { adapter.submitList(it) })
//    }
}