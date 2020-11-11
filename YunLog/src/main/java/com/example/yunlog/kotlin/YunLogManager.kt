package com.example.yunlog.kotlin


class YunLogManager(val config: YunLogConfig) {
    companion object {
        const val TAG = "YunLogManager"
        lateinit var mInstance: YunLogManager

        fun newInstance(config: YunLogConfig) {
            mInstance = YunLogManager(config)
        }
    }

}