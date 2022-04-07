package com.mina.neugelb.data.model

data class ConfigResponse(
    val change_keys: List<String>,
    val images: Images
)

data class Images(
    val backdrop_sizes: List<String>,
    val base_url: String,
    val logo_sizes: List<String>,
    val poster_sizes: List<String>,
    val profile_sizes: List<String>,
    val secure_base_url: String,
    val still_sizes: List<String>
)

data class ConfigData(
    val imgBaseUrlOrigien: String,
    val imgBaseUrlHQ: String,
    val imgBaseUrlMedium: String,
)

fun ConfigResponse.toImageConfigurationModel(): ConfigData {
    return images.let {
        val reversedSizesArr = it.logo_sizes.reversed();
        ConfigData(
            imgBaseUrlOrigien = it.secure_base_url + reversedSizesArr[0] ,
            imgBaseUrlHQ = it.secure_base_url + reversedSizesArr[1] ,
            imgBaseUrlMedium = it.secure_base_url + reversedSizesArr[2] ,
        )
    }
}