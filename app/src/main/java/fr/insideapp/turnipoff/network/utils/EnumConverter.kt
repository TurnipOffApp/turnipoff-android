package fr.insideapp.turnipoff.network.utils

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

interface EnumConvertible {
    val jsonValue: String
}

object EnumAsOrdinalToStringConverter : Converter<Enum<*>, String> {
    override fun convert(value: Enum<*>): String = if(value is EnumConvertible) {
        value.jsonValue
    } else {
        value.ordinal.toString()
    }

}

class EnumAsOrdinalToStringConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? = if (type is Class<*> && type.isEnum) {
        EnumAsOrdinalToStringConverter
    } else {
        null
    }
}