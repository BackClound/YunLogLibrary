package com.example.yunlog.kotlin


class YunLogManager() {

    public var config: YunLogConfig? = null

    fun getInstance(): YunLogConfig? {
        return config
    }

    companion object {
        const val TAG = "YunLogManager"

        val mInstance: YunLogManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            YunLogManager()
        }

        fun setConfig(config: YunLogConfig) {
            mInstance.config = config
        }
    }

}