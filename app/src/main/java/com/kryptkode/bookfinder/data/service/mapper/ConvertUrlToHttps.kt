package com.kryptkode.bookfinder.data.service.mapper

class ConvertUrlToHttps {
    fun convert(url: String): String {
        if (url.startsWith(HTTP)) {
            return url.replace(HTTP, HTTPS)
        }
        return url
    }

    companion object {
        private const val HTTP = "http"
        private const val HTTPS = "https"
    }
}